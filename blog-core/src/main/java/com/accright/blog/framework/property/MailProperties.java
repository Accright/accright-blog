package com.accright.blog.framework.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "wechat")
public class MailProperties {
    private Map<String,Map<String,String>> mails = new HashMap<>();


    public Map<String, Map<String, String>> getMails() {
        return mails;
    }

    public void setMails(Map<String, Map<String, String>> mails) {
        this.mails = mails;
    }
}
