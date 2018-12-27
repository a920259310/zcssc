package com.springboot.zcssc.session.dao.impl;

import com.springboot.zcssc.session.bean.RuYiBean;
import com.springboot.zcssc.session.dao.RuYiHttpDao;
import com.springboot.zcssc.utils.DateUtil;
import com.springboot.zcssc.utils.JSONUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class RuYiHttpDaoImpl implements RuYiHttpDao {

    HttpClient httpClient = new HttpClient();

    String href = null;

    @PostConstruct
    public void init() throws IOException {
        httpClient.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
        /**
         * 获取最有地址
         */
        GetMethod getMethod = new GetMethod("https://www-ry999.com/");
        int executeMethod = httpClient.executeMethod(getMethod);
        href = Jsoup.parse(getMethod.getResponseBodyAsString()).selectFirst("body > div.xl_bo > div.xl_cs > ul > li:nth-child(1) > a").attr("href");

    }

    @Override
    public List<RuYiBean> getDataByDate(Date date) throws IOException {
        //https://www-ry222.com//static//data/2018122750HistoryLottery.json?_=1545896382357
        String url = href + "/static//data/" + DateUtil.formatDate(date, DateUtil.PATTERN_DATE_NOT) + "50HistoryLottery.json?_=" + new Date().getTime();
        GetMethod homeGet = new GetMethod(url);

        httpClient.executeMethod(homeGet);
        String responseBodyAsString = homeGet.getResponseBodyAsString();

        List<RuYiBean> ruYiBeans = JSONUtils.toList(responseBodyAsString, RuYiBean.class);

        return ruYiBeans;
    }

    @Override
    public List<RuYiBean> getDataByDate() throws IOException {
        return getDataByDate(new Date());
    }

    @Override
    public RuYiBean getDataByDateAndTunkNum(Date date, String tunkNum) throws IOException {
        List<RuYiBean> dataByDate = getDataByDate(date);
        List<RuYiBean> collect = dataByDate.stream().filter(x -> {
            return x.getTurnNum().equals(tunkNum);
        }).collect(Collectors.toList());
        return CollectionUtils.isEmpty(collect) ? null : collect.get(0);
    }

    @Override
    public RuYiBean getDataByTunkNum(String tunkNum) throws IOException, ParseException {
        Date cruDate = new Date();
        List<RuYiBean> dataByDate = getDataByDate(cruDate);
        if (CollectionUtils.isEmpty(dataByDate)) return null;

        Collections.sort(dataByDate, new Comparator<RuYiBean>() {
            @Override
            public int compare(RuYiBean o1, RuYiBean o2) {
                return o1.getTurnNum().compareTo(o2.getTurnNum());
            }
        });

        if (tunkNum.compareTo(dataByDate.get(dataByDate.size() -1 ).getTurnNum()) > 0){
            //大于今日最大期号
            return null;
        }

        if(tunkNum.compareTo(dataByDate.get(0).getTurnNum()) >= 0 && tunkNum.compareTo(dataByDate.get(dataByDate.size() -1 ).getTurnNum()) <= 0){
            //在今日期号范围
            List<RuYiBean> collect = dataByDate.stream().filter(x -> {
                return x.getTurnNum().equals(tunkNum);
            }).collect(Collectors.toList());
            return CollectionUtils.isEmpty(collect) ? null : collect.get(0);
        }

        if (tunkNum.compareTo(dataByDate.get(0).getTurnNum()) < 0){
            //小于今日最小期号

            Long targetTunNum = Long.valueOf(tunkNum);

            Long cruLastTunNum = Long.valueOf(dataByDate.get(0).getTurnNum());

            long days = (cruLastTunNum - targetTunNum) / 179;

            Date add = DateUtil.add(cruDate, Calendar.DAY_OF_MONTH, (int) -days - 1 );

            return getDataByDateAndTunkNum(add,tunkNum);
        }

        return null;
    }

}
