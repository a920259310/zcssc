package com.springboot.zcssc.session.dao;

import com.springboot.zcssc.session.bean.RuYiBean;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface RuYiHttpDao {

    /**
     * 获取指定日期的数据
     * @param date
     * @return
     */
    public List<RuYiBean> getDataByDate(Date date) throws IOException;

    /**
     * 获取当天的数据
     * @return
     */
    public List<RuYiBean> getDataByDate() throws IOException;


    /**
     * 获取指定日期指定期号的数据
     * @param date
     * @param tunkNum
     * @return
     */
    public RuYiBean getDataByDateAndTunkNum(Date date,String tunkNum) throws IOException;


    /**
     * 获取指定期号的数据
     * @param tunkNum
     * @return
     */
    public RuYiBean getDataByTunkNum(String tunkNum) throws IOException, ParseException;

}
