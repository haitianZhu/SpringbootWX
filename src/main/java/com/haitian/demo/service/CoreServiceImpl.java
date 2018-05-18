package com.haitian.demo.service;

import com.haitian.demo.controller.CoreController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: haitian
 * @Date: 2018/5/16 下午11:03
 * @Description:
 */
@Service("coreService")
public class CoreServiceImpl implements CoreService {

    private static Logger log = LoggerFactory.getLogger(CoreServiceImpl.class);

    /**
     * 从微信发来的请求，包括事件的推送
     * @param request
     * @return
     */
    @Override
    public String processRequest(HttpServletRequest request) {

        // 暂时对消息不做处理
        return "";
    }
}
