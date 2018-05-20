package com.haitian.demo.model.netbean;

/**
 * @Author: haitian
 * @Date: 2018/5/20 下午11:51
 * @Description:
 */
public class MenuCreateResponse {

    private long errcode;

    private String errmsg;

    public long getErrcode() {
        return errcode;
    }

    public void setErrcode(long errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "MenuCreateResponse{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
