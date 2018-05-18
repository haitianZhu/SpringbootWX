package com.haitian.demo.model.netbean;

/**
 * @Author: haitian
 * @Date: 2018/5/19 上午12:17
 * @Description:
 */
public class GetTokenRequest {

    private String grant_type;

    private String appid;

    private String secret;

    public GetTokenRequest(String appid, String secret) {
        this.grant_type = "client_credential";
        this.appid = appid;
        this.secret = secret;
    }

    public GetTokenRequest(String grant_type, String appid, String secret) {
        this.grant_type = grant_type;
        this.appid = appid;
        this.secret = secret;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

}
