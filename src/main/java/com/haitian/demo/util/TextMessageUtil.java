package com.haitian.demo.util;

import com.haitian.demo.Model.MessageText;
import com.thoughtworks.xstream.XStream;

import java.util.Date;

/**
 * @Author: haitian
 * @Date: 2018/5/17 上午12:15
 * @Description:
 */
public class TextMessageUtil implements BaseMessageUtil<MessageText>{

    /**
     * 将发送消息封装成对应的xml格式
     */
    public  String messageToxml(MessageText message) {
        XStream xstream  = new XStream();
        xstream.alias("xml", message.getClass());
        return xstream.toXML(message);
    }
    /**
     * 封装发送消息对象,封装时，需要将调换发送者和接收者的关系
     * @param FromUserName
     * @param ToUserName
     */
    public  String initMessage(String FromUserName, String ToUserName, String content) {
        MessageText text = new MessageText();
        text.setToUserName(FromUserName);
        text.setFromUserName(ToUserName);
        text.setContent(content);
        text.setCreateTime(new Date().getTime());
        text.setMsgType("text");
        return messageToxml(text);
    }

    @Override
    public String message2xml(MessageText messageText) {
        return null;
    }

    @Override
    public String initMessage(String fromUserName, String toUserName) {
        return null;
    }
}
