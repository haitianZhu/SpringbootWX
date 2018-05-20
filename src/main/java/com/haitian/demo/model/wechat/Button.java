package com.haitian.demo.model.wechat;

/**
 * @Author: haitian
 * @Date: 2018/5/20 下午11:31
 * @Description: Wechat Button bean
 */
public class Button {

    /**
     * 菜单标题
     */
    private String name;

    /**
     * 菜单的响应动作类型
     */
    private String type;

    private Button[] sub_button;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
