package com.springboot.zcssc.zcssc.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeTest {
    public static ChromeDriver driver ;

    public static void main(String[] args) throws InterruptedException {
        login("https://www-ry999.com/","a920259310","a19931006");

    }

    /**
     * 登陆
     */
    public static void login(String url,String uname,String paword) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/main/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);

        //   广告弹框
        WebElement gg = ((ChromeDriver) driver).findElement(By.cssSelector("#new_user > span"));
        gg.click();

        //选择线路
        //body > div.xl_bo > div.xl_cs > ul > li:nth-child(1) > a
        WebElement going = ((ChromeDriver) driver).findElement(By.cssSelector("body > div.xl_bo > div.xl_cs > ul > li:nth-child(1) > a"));
        going.click();

        // 用户名
        //*[@id="userName"]
        WebElement username=((ChromeDriver) driver).findElementById("userName");
        username.sendKeys(uname);

        // 密码
        WebElement password=((ChromeDriver) driver).findElementById("userPwd");
        password.sendKeys(paword);

        //验证码获取
        WebElement numberCode=((ChromeDriver) driver).findElement(By.cssSelector("#numberCode"));
        String text = numberCode.getText();
        //验证码输入
        WebElement loginVcode=((ChromeDriver) driver).findElementById("loginVcode");
        loginVcode.sendKeys(text);

        // 提交登陆
        WebElement submit =((ChromeDriver) driver).findElement(By.cssSelector("body > div.wrap-login > div.info-panel.clearfix > div.login-info-area > form > input"));
        submit.click();

        Thread.sleep(3000);
        //同意用户协议
        WebElement yesBtn=((ChromeDriver) driver).findElement(By.cssSelector("#yesBtn"));
        yesBtn.click();

        Thread.sleep(5000);
        WebElement closeLoginGg=((ChromeDriver) driver).findElement(By.cssSelector("body > div.main-body.skin_blue > div.notice-wrap > div.bg.lay-important > div.close-btn > a"));
        closeLoginGg.click();
        closeLoginGg.click();
        closeLoginGg.click();
        closeLoginGg.click();

//        https://www-ry222.com/home/
        WebElement webChat=((ChromeDriver) driver).findElement(By.cssSelector("body > div.main-body.skin_blue > div.chatbar > div > div > div.controls.chat-header > span > a:nth-child(4) > i"));
        webChat.click();
    }
}
