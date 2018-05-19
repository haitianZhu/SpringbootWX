package com.haitian.demo.util.wechat;

import com.haitian.demo.controller.CoreController;
import com.haitian.demo.model.netbean.GetTokenRequest;
import com.haitian.demo.model.netbean.GetTokenResponse;
import com.haitian.demo.service.ApiNetWX;
import com.haitian.demo.util.RedisUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

/**
 * @Author: haitian
 * @Date: 2018/5/18 下午11:42
 * @Description:
 */
public class WeChatUtil {

    /**
     * url
     */
    public static final String BASE_URL = "https://api.weixin.qq.com/cgi-bin/";

    public static final String TEST_APP_ID = "wx436d7e721814cd14";

    public static final String TEST_APP_SECRET = "ef4eabc672ab13f34d7f0a7d6057ff41";

    public static final String GET_ACCESS_TOKEN = "token";

    /**
     * constant value
     */
    private static final String REDIS_TOKEN_KEY = "access_token";

    //增加日志
    private static Logger log = LoggerFactory.getLogger(WeChatUtil.class);

    /**
     * 使用redis存储access_token,设置超时时间为 2*60*60s
     * 若redis服务器没有开启，则每次获取token都从微信服务器更新，待优化
     * @return
     */
    public static String getToken() {

        String token = null;

        Jedis jedis = RedisUtil.getJedis();
        if (jedis == null) {

            log.warn("redis server is not open!");
            String responseToken = getAccessToken();
            if (responseToken != null && !responseToken.isEmpty()) {

                token = responseToken;
            } else {
                log.error("getAccessTokenError:", responseToken);
            }
            return token;
        }

        token = jedis.get(WeChatUtil.REDIS_TOKEN_KEY);

        if (token == null) {

            String responseToken = getAccessToken();
            if (responseToken != null && !responseToken.isEmpty()) {

                token = responseToken;

                jedis.set(WeChatUtil.REDIS_TOKEN_KEY, token);
                jedis.expire("access_token", 60*60*2);
            } else {
                log.error("getAccessTokenError:", responseToken);
            }
        }

        RedisUtil.returnResource(jedis);

        return token;
    }

    private static String getAccessToken() {

        GetTokenResponse getTokenResponse = ApiNetWX.getToken(WeChatUtil.BASE_URL, new
                GetTokenRequest(WeChatUtil.TEST_APP_ID, WeChatUtil.TEST_APP_SECRET));
        return getTokenResponse.getAccess_token();
    }

}
