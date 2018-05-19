package com.haitian.demo.model.message;

/**
 * @Author: haitian
 * @Date: 2018/5/19 下午10:09
 * @Description:
 */
public class MessageVoice extends BaseMessage{

    private MessageVoice.Voice Voice;

    public MessageVoice() {

    }

    public MessageVoice(String toUserName, String fromUserName,
                        long createTime, String msgType, String mediaId, MessageVoice.Voice voice) {
        super();
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        Voice = voice;
    }

    public MessageVoice.Voice getVoice() {
        return Voice;
    }

    public void setVoice(MessageVoice.Voice voice) {
        Voice = voice;
    }

    public static class Voice {

        private String MediaId;

        public Voice(String mediaId) {
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
