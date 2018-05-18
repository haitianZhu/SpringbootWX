package com.haitian.demo.controller;

import com.haitian.demo.model.netbean.GetTokenRequest;
import com.haitian.demo.model.netbean.GetTokenResponse;
import com.haitian.demo.service.ApiNetWX;
import com.haitian.demo.service.CoreService;
import com.haitian.demo.util.ImageMessageUtil;
import com.haitian.demo.util.MessageUtil;
import com.haitian.demo.util.SignUtil;
import com.haitian.demo.util.TextMessageUtil;
import com.haitian.demo.util.URLContract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author: haitian
 * @Date: 2018/5/16 下午10:52
 * @Description:
 */
@RestController
@RequestMapping("")
public class CoreController {

    @Autowired
    private CoreService coreService;

    //增加日志
    private static Logger log = LoggerFactory.getLogger(CoreController.class);

    //验证是否来自微信服务器的消息
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String checkSignature(@RequestParam(name = "signature" ,required = false) String signature  ,
                                 @RequestParam(name = "nonce",required = false) String  nonce ,
                                 @RequestParam(name = "timestamp",required = false) String  timestamp ,
                                 @RequestParam(name = "echostr",required = false) String  echostr){
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            log.info("接入成功");
            return echostr;
        }
        log.error("接入失败");
        return "";
    }

    /**
     * 调用核心业务类接收消息、处理消息、推送消息
     * @param request
     * @return
     */
    @PostMapping(value = "")
    public void post(HttpServletRequest request, HttpServletResponse response) {

        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        //将微信请求xml转为map格式，获取所需的参数
        Map<String,String> map = MessageUtil.xmlToMap(request);
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
        String msgType = map.get("MsgType");

        String message = null;
        // 处理不同类型的消息
        System.out.println(msgType+"");
        if ("text".equals(msgType)) {

            String content = map.get("Content");

            GetTokenResponse getTokenResponse = ApiNetWX.getToken(URLContract.BASE_URL, new
                    GetTokenRequest(URLContract.TEST_APP_ID, URLContract.TEST_APP_SECRET));
            content = "token:" + getTokenResponse.getAccess_token() + "expires_in:" +
                    getTokenResponse.getExpires_in();

            TextMessageUtil textMessage = new TextMessageUtil();
            String responseContent = "你输入的内容是:" + content;
            message = textMessage.initMessage(fromUserName, toUserName, responseContent);
        } else if ("image".equals(msgType)){

            String mediaId = map.get("MediaId");

            ImageMessageUtil imageMessageUtil = new ImageMessageUtil();
            message = imageMessageUtil.initMessage(fromUserName, toUserName, mediaId);
        }


        try {
            out = response.getWriter();
            out.write(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        out.close();
    }
}
