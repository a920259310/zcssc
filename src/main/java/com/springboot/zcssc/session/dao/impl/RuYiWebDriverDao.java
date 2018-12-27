package com.springboot.zcssc.session.dao.impl;

import com.springboot.zcssc.session.config.RuYiConfig;
import com.springboot.zcssc.session.dao.WebDriverDao;
import com.springboot.zcssc.session.factory.RuYiSessionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(value = {RuYiConfig.class})
public class RuYiWebDriverDao implements WebDriverDao {

    ChromeDriver webDriver;

    @Autowired
    RuYiSessionFactory ruYiSessionFactory;

    @Autowired
    RuYiConfig ruYiConfig;

//    @PostConstruct
    @Override
    public void init() throws InterruptedException {
        if (webDriver == null) {
            webDriver = (ChromeDriver) ruYiSessionFactory.createSession(ruYiConfig.getUrl(), ruYiConfig.getUserName(), ruYiConfig.getPassWord());
        }
        gotoResultPage();

    }


    @Override
    public void gotoResultPage() throws InterruptedException {
        WebElement resultPage = webDriver.findElement(By.cssSelector("body > div.main-body.skin_blue > div.header > div.header-top.clearfix > div.menu2 > span:nth-child(3) > a"));
        resultPage.click();

        WebElement framePage = webDriver.findElementByName("framePage");
        webDriver.switchTo().frame(framePage);
        WebElement historyQueryParamOpenTime = webDriver.findElement(By.id("historyQueryParamOpenTime"));

        Thread.sleep(1000);
        historyQueryParamOpenTime.clear();

        historyQueryParamOpenTime.sendKeys("2018-12-20");
        historyQueryParamOpenTime.click();
    }


}
