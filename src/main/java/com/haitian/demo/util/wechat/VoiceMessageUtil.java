package com.haitian.demo.util.wechat;

import com.haitian.demo.model.message.MessageImage;
import com.haitian.demo.model.message.MessageVoice;
import com.thoughtworks.xstream.XStream;

import java.util.Date;

/**
 * @Author: haitian
 * @Date: 2018/5/19 下午10:20
 * @Description:
 */
public class VoiceMessageUtil {

    /**
     * 将发送消息封装成对应的xml格式
     */
    public  String messageToxml(MessageVoice message) {
        XStream xstream  = new XStream();
        xstream.alias("xml", message.getClass());
        return xstream.toXML(message);
    }
    /**
     * 封装发送消息对象,封装时，需要将调换发送者和接收者的关系
     * @param FromUserName
     * @param ToUserName
     */
    public  String initMessage(String FromUserName, String ToUserName, String mediaId) {
        MessageVoice voice = new MessageVoice();
        voice.setToUserName(FromUserName);
        voice.setFromUserName(ToUserName);
        voice.setCreateTime(new Date().getTime());
        voice.setMsgType("voice");
        voice.setVoice(new MessageVoice.Voice(mediaId));
        return messageToxml(voice);
    }

}
