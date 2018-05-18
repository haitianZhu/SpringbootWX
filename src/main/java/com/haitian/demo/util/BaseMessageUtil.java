package com.haitian.demo.util;

/**
 * @Author: haitian
 * @Date: 2018/5/17 上午12:10
 * @Description: 发送消息的xml封装类
 */
public interface BaseMessageUtil<T> {

    /**
     * 将回复的信息转化为xml给微信
     * @param t
     * @return
     */
    String message2xml(T t);

    /**
     * 回复的消息封装
     * @param fromUserName
     * @param toUserName
     * @return
     */
    String initMessage(String fromUserName, String toUserName);
}
