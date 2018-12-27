package com.springboot.zcssc.zcssc.webdriver;

import com.springboot.zcssc.session.bean.RuYiBean;
import com.springboot.zcssc.utils.JSONUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;

public class ImitateLogin {

    public static void main(String[] args) throws IOException {

        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");

        /**
         * 获取最有地址
         */
        GetMethod getMethod = new GetMethod("https://www-ry999.com/");
        int executeMethod = httpClient.executeMethod(getMethod);
        String href = Jsoup.parse(getMethod.getResponseBodyAsString()).selectFirst("body > div.xl_bo > div.xl_cs > ul > li:nth-child(1) > a").attr("href");



        /**
         * 登陆
         */
        /*PostMethod postMethodLogin = new PostMethod(href);
        postMethodLogin.setPath("/api/login.do");
        NameValuePair[] postData = {
                new NameValuePair("account", "a920259310"),
                new NameValuePair("password", "600cac7a0725244d5c8e7a06c7366eca"),
                new NameValuePair("pwdtext", "a19931006"),
                new NameValuePair("loginSrc", "0")
        };
        postMethodLogin.setRequestBody(postData);
        int i = httpClient.executeMethod(postMethodLogin);
        String responseBodyAsString = postMethodLogin.getResponseBodyAsString();
        System.out.println(responseBodyAsString);
*/
        /**
         * 下单地址
         */
        //https://www-ry222.com/bet/bet.do?t=1545898316830&gameId=1&totalNums=1&totalMoney=-30&betSrc=0&turnNum=20181227062&betBean%5B0%5D.playId=1210&betBean%5B0%5D.money=-30

        /**
         * 数据抓取
         */
        //抓取数据
        GetMethod homeGet = new GetMethod("https://www-ry222.com//static//data/2018122750HistoryLottery.json?_=1545896382357");
        httpClient.executeMethod(homeGet);
        String responseBodyAsString1 = homeGet.getResponseBodyAsString();
        List<RuYiBean> ruYiBeans = JSONUtils.toList(responseBodyAsString1, RuYiBean.class);

        System.out.println(ruYiBeans);
    }

//    private static void HttpClientLogin(String userName, String password,
//                                        String loginUrl, String dataUrl) {
//        HttpClient httpClient = new HttpClient();
//        httpClient.getParams().setParameter(
//                HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
//        PostMethod postMethod = new PostMethod(loginUrl);
//
//        NameValuePair[] postData = { new NameValuePair("tbUserName", userName),
//                new NameValuePair("tbPassword", password) };
//        postMethod.setRequestBody(postData);
//
//        try {
//
//            httpClient.getParams().setCookiePolicy(
//                    CookiePolicy.BROWSER_COMPATIBILITY);
//            httpClient.executeMethod(postMethod);
//            Cookie[] cookies = httpClient.getState().getCookies();
//            StringBuffer stringBuffer = new StringBuffer();
//            for (Cookie c : cookies) {
//                stringBuffer.append(c.toString() + ";");
//            }
//
//            GetMethod getMethod = new GetMethod(dataUrl);
//            getMethod.setRequestHeader("Cookie", stringBuffer.toString());
//            postMethod.setRequestHeader("Host", "passport.cnblogs.com");
//            postMethod.setRequestHeader("Referer", "http://home.cnblogs.com/");
//            postMethod.setRequestHeader("User-Agent", "AndroidCnblogs");
//            httpClient.executeMethod(getMethod);
//
//            String result = getMethod.getResponseBodyAsString();
//            System.out.println(result);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
