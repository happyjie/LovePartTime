package com.work.happyjie.parttime.bean;

/**
 * Created by asus on 2018-03-24 .
 */

public class AutoLinkKeyValueInfoBean extends KeyValueItemBean {

    private boolean enableAutoLink = false;

    public AutoLinkKeyValueInfoBean(String key, String value) {
        super(key, value);
        enableAutoLink = false;
    }

    public AutoLinkKeyValueInfoBean(String key, String value, boolean enableAutoLink) {
        super(key, value);
        this.enableAutoLink = enableAutoLink;
    }

    public boolean isEnableAutoLink() {
        return enableAutoLink;
    }

    public void setEnableAutoLink(boolean enableAutoLink) {
        this.enableAutoLink = enableAutoLink;
    }
}
