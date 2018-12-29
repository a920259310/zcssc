package com.springboot.zcssc.zcssc.webdriver;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Scanner;

public class HaoCai {

    static String HOST_URL = "https://www.6hc6.com/";
    static HttpClient httpClient = new HttpClient();

    static {
        httpClient.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
    }

    public static void main(String[] args) throws IOException {
        init();//初始化登陆

        PostMethod postMethodLogin = new PostMethod(HOST_URL + "member/bet");
        postMethodLogin.setRequestBody("{\"lottery\":\"BJPK10\",\"drawNumber\":\"722839\",\"bets\":[{\"game\":\"B1\",\"contents\":\"1\",\"amount\":1,\"odds\":9.95},{\"game\":\"B1\",\"contents\":\"2\",\"amount\":1,\"odds\":9.95},{\"game\":\"B1\",\"contents\":\"3\",\"amount\":1,\"odds\":9.95},{\"game\":\"B1\",\"contents\":\"4\",\"amount\":1,\"odds\":9.95},{\"game\":\"B1\",\"contents\":\"5\",\"amount\":1,\"odds\":9.95},{\"game\":\"B2\",\"contents\":\"1\",\"amount\":1,\"odds\":9.95},{\"game\":\"B3\",\"contents\":\"1\",\"amount\":1,\"odds\":9.95},{\"game\":\"B4\",\"contents\":\"1\",\"amount\":1,\"odds\":9.95},{\"game\":\"B5\",\"contents\":\"1\",\"amount\":1,\"odds\":9.95}],\"uniqueId\":0}");
        int executeMethod = httpClient.executeMethod(postMethodLogin);
        String responseBodyAsString = postMethodLogin.getResponseBodyAsString();
        System.out.println(responseBodyAsString);
    }


    /**
     * 初始化登陆
     * @throws IOException
     */
    public static void init() throws IOException {
        /**
         * 获取验证码
         */
        GetMethod getMethod = new GetMethod(HOST_URL);
        int executeMethod = httpClient.executeMethod(getMethod);
        Document parse = Jsoup.parse(getMethod.getResponseBodyAsString());
        Elements select = parse.select("#codeimg");
        String src = select.attr("src");

        GetMethod getMethodSrc = new GetMethod(HOST_URL + src);
        httpClient.executeMethod(getMethodSrc);
        InputStream responseBodyAsStream = getMethodSrc.getResponseBodyAsStream();
        IOUtils.copy(responseBodyAsStream,new FileOutputStream("D:\\haocai\\" + new Date().getTime() + ".png"));

        System.err.println("请输入验证码:.....");
        Scanner lll = new Scanner(System.in);
        String code = lll.next();

        /**
         * 登陆
         */
        PostMethod postMethodLogin = new PostMethod(HOST_URL + "cashlogin");
        NameValuePair[] postData = {
                new NameValuePair("account", "a920259310"),
                new NameValuePair("password", "678b557478a23722cba4e963d48bd620"),
//                new NameValuePair("pwdtext", "a19931006"),
                new NameValuePair("code", code)
        };
        postMethodLogin.setRequestBody(postData);
        int i = httpClient.executeMethod(postMethodLogin);
        String responseBodyAsString = postMethodLogin.getResponseBodyAsString();
        System.out.println(responseBodyAsString);
    }

}
