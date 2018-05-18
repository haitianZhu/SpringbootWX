package com.haitian.demo.model.netbean;

/**
 * @Author: haitian
 * @Date: 2018/5/19 上午12:02
 * @Description:
 */
public class GetTokenResponse {

    private String access_token;

    private String expires_in;

    private int errcode = 0;

    private String errmsg;

    public GetTokenResponse(String access_token, String expires_in, int errcode, String errmsg) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
