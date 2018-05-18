package com.haitian.demo.util;

import com.haitian.demo.model.MessageImage;
import com.thoughtworks.xstream.XStream;

import java.util.Date;

/**
 * @Author: haitian
 * @Date: 2018/5/17 上午12:53
 * @Description:
 */
public class ImageMessageUtil {

    /**
     * 将发送消息封装成对应的xml格式
     */
    public  String messageToxml(MessageImage message) {
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
        MessageImage image = new MessageImage();
        image.setToUserName(FromUserName);
        image.setFromUserName(ToUserName);
        image.setCreateTime(new Date().getTime());
        image.setMsgType("image");
        image.setImage(new MessageImage.Image(mediaId));
        return messageToxml(image);
    }
}
