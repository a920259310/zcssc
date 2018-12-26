package com.springboot.zcssc.session.service;

import com.springboot.zcssc.session.config.RuYiConfig;
import com.springboot.zcssc.session.factory.RuYiSessionFactory;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@EnableConfigurationProperties(value = {RuYiConfig.class})
public class RuYiWebDriverService implements WebDriverService {

    WebDriver webDriver;

    @Autowired
    RuYiSessionFactory ruYiSessionFactory;

    @Autowired
    RuYiConfig ruYiConfig;

    @PostConstruct
    @Override
    public void init() throws InterruptedException {
        if (webDriver == null){
            webDriver = ruYiSessionFactory.createSession(ruYiConfig.getUrl(),ruYiConfig.getUserName(),ruYiConfig.getPassWord());
        }
    }


}
