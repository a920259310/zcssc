package com.springboot.zcssc.session.factory;

import org.openqa.selenium.WebDriver;

public interface SessionFactory {
    /**
     * 创建一个网站爬虫会话
     * @param url    网站链接
     * @return
     */
    public WebDriver createSession(String url);

    /**
     * 创建一个网站爬虫会话
     * @param url     网站链接
     * @param userName 用户名称
     * @param passWord 用户密码
     * @return
     */
    public WebDriver createSession(String url,String userName,String passWord) throws InterruptedException;
}
