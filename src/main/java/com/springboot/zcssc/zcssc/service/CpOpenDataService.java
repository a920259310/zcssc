package com.springboot.zcssc.zcssc.service;

import com.springboot.zcssc.zcssc.bean.db.CpOpenData;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface CpOpenDataService extends BaseService<CpOpenData> {
    /**
     * 插入指定日期数据
     * @param date
     */
    public void insertDataByDate(Date date) throws IOException, ParseException;
    /**
     * 插入今日数据
     */
    public void insertCruData() throws IOException, ParseException;

    /**
     * 插入指定日期的指定期号数据
     * @param date
     * @param trunkNumber
     */
    public void insertDataByDateAndTrunkNumber(Date date,String trunkNumber) throws IOException, ParseException;

    /**
     * 插入指定期号数据
     * @param trunkNumber
     */
    public void insertDataByDateAndTrunkNumber(String trunkNumber) throws IOException, ParseException;

    /**
     * 更新指定日期新开的数据
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public Boolean insertToDayLastNewData(Date date) throws IOException, ParseException;

    /**
     * 获取指定日期,指定索引数据库中最后一条记录
     * @param date
     * @param openIndex
     * @return
     */
    public CpOpenData getCpOpenDataByDateAndOpenIndex(Date date,String openIndex);

    /**
     * 获取指定日期,数据库中最后一组记录
     * @param date
     * @return
     */
    public List<CpOpenData> getCpOpenDataByDate(Date date);

    /**
     * 验证是否更新到了指定日期的最新数据
     * @param date  指定日期
     * @param mimute 新数据产生时间
     * @return
     */
    public Boolean validateOpenData(Date date,int mimute);


    /**
     * 分析预警号码详情
     * @param trunkNumbers 预警期数
     * @param upNumbers    集合上线个数
     */
    public List<CpOpenData> anlyziByEndCpOpenData(CpOpenData cpOpenData,int trunkNumbers,int upNumbers);

}
