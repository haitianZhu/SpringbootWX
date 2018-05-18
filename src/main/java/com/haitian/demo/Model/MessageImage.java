package com.haitian.demo.Model;

/**
 * @Author: haitian
 * @Date: 2018/5/17 上午12:54
 * @Description:
 */
public class MessageImage extends BaseMessage{

    private Image Image;

    public MessageImage() {

    }

    public MessageImage(String toUserName, String fromUserName,
                       long createTime, String msgType, String mediaId, Image image) {
        super();
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        Image = image;
    }

    public MessageImage.Image getImage() {
        return Image;
    }

    public void setImage(MessageImage.Image image) {
        Image = image;
    }

    public static class Image {

        private String MediaId;

        public Image(String mediaId) {
            MediaId = mediaId;
        }

        public String getMediaId() {
            return MediaId;
        }

        public void setMediaId(String mediaId) {
            MediaId = mediaId;
        }
    }
}
