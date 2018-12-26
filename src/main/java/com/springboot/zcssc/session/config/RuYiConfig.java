package com.springboot.zcssc.session.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "zcssc.config.session.ruyi")
@Component
public class RuYiConfig {

    private String driverKey;//驱动类在环境中的名称

    private String driverValueFile;//驱动类在环境中的位置

    private String url; // 平台地址

    private String userName;//平台的账号

    private String passWord;//平台的密码


    public String getDriverKey() {
        return driverKey;
    }

    public void setDriverKey(String driverKey) {
        this.driverKey = driverKey;
    }

    public String getDriverValueFile() {
        return driverValueFile;
    }

    public void setDriverValueFile(String driverValueFile) {
        this.driverValueFile = driverValueFile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RuYiConfig{" +
                "driverKey='" + driverKey + '\'' +
                ", driverValueFile='" + driverValueFile + '\'' +
                ", url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
