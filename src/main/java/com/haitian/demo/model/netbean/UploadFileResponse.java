package com.haitian.demo.model.netbean;

/**
 * @Author: haitian
 * @Date: 2018/5/19 下午10:32
 * @Description:
 */
public class UploadFileResponse {

    private String type;

    private String media_id;

    private long created_at;

    private long errcode;

    private String errmsg;

    public UploadFileResponse(String type, String media_id, long created_at, long errcode, String
            errmsg) {
        this.type = type;
        this.media_id = media_id;
        this.created_at = created_at;
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

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
        return "UploadFileResponse{" +
                "type='" + type + '\'' +
                ", media_id='" + media_id + '\'' +
                ", created_at=" + created_at +
                ", errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
