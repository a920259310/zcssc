package com.springboot.zcssc.zcssc.service.impl;

import com.springboot.zcssc.session.bean.RuYiBean;
import com.springboot.zcssc.session.dao.RuYiHttpDao;
import com.springboot.zcssc.utils.CaiJiBeanToDbBeanUtil;
import com.springboot.zcssc.utils.DateUtil;
import com.springboot.zcssc.zcssc.bean.db.CpOpenData;
import com.springboot.zcssc.zcssc.bean.db.CpOpenDataComparator;
import com.springboot.zcssc.zcssc.mapper.CpOpenDataMapper;
import com.springboot.zcssc.zcssc.service.CpOpenDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CpOpenDataServiceImpl extends BaseServiceImpl<CpOpenData> implements CpOpenDataService {
    @Autowired
    RuYiHttpDao ruYiHttpDao;

    @Autowired
    CpOpenDataMapper cpOpenDataMapper;

    @Override
    public void insertDataByDate(Date date) throws IOException, ParseException {
        List<RuYiBean> dataByDate = ruYiHttpDao.getDataByDate(date);
        insertBatchRuYiBeanToDb(dataByDate);

    }

    public void insertBatchRuYiBeanToDb(List<RuYiBean> ruYiBeanList) throws ParseException {
        List<CpOpenData> cpOpenDatas = CaiJiBeanToDbBeanUtil.ruYiToDbModel(ruYiBeanList);
        insertBatchDbBeans(cpOpenDatas);
    }

    public void insertRuYiBeanToDb(RuYiBean ruYiBean) throws ParseException {
        List<CpOpenData> cpOpenDatas = CaiJiBeanToDbBeanUtil.ruYiToDbModel(ruYiBean);
        insertBatchDbBeans(cpOpenDatas);
    }


    public void insertBatchDbBeans(List<CpOpenData> cpOpenDatas) {
        if (CollectionUtils.isEmpty(cpOpenDatas)) return;

        for(CpOpenData cpOpenData : cpOpenDatas){

            insert(cpOpenData);

        }
    }

    @Override
    public int insert(CpOpenData cpOpenData) {

        CpOpenData cpOpenDataCondition = new CpOpenData();
        cpOpenDataCondition.setOpenTrunkNumber(cpOpenData.getOpenTrunkNumber());
        cpOpenDataCondition.setOpenIndex(cpOpenData.getOpenIndex());

        if(cpOpenDataMapper.selectOne(cpOpenDataCondition) == null){
            return cpOpenDataMapper.insert(cpOpenData);
        }

        return 0;
    }

    @Override
    public void insertCruData() throws IOException, ParseException {
        insertDataByDate(new Date());
    }

    @Override
    public void insertDataByDateAndTrunkNumber(Date date, String trunkNumber) throws IOException, ParseException {
        RuYiBean dataByDateAndTunkNum = ruYiHttpDao.getDataByDateAndTunkNum(date, trunkNumber);
        insertRuYiBeanToDb(dataByDateAndTunkNum);
    }

    @Override
    public void insertDataByDateAndTrunkNumber(String trunkNumber) throws IOException, ParseException {
        RuYiBean dataByTunkNum = ruYiHttpDao.getDataByTunkNum(trunkNumber);
        insertRuYiBeanToDb(dataByTunkNum);
    }

    @Override
    public Boolean insertToDayLastNewData(Date date) throws IOException, ParseException {

        CpOpenData cpOpenData = this.getCpOpenDataByDateAndOpenIndex(date,"0");

        if(cpOpenData == null){//今天的日期在数据库中没有数据,获取今天所有的数据
            this.insertDataByDate(date);
        }

        if(cpOpenData != null){//今天的日期在数据库中没有数据,获取今天最新的数据
            List<RuYiBean> dataByDate = ruYiHttpDao.getDataByDate(date);
            List<RuYiBean> collect = dataByDate.stream().filter(x -> {
                if (x.getTurnNum().compareTo(cpOpenData.getOpenTrunkNumber()) > 0) return true;
                return false;
            }).collect(Collectors.toList());
            logger.info("获取"+collect.size()+"条新数据........");
            this.insertBatchRuYiBeanToDb(collect);
        }

        return validateOpenData(date,5);

    }

    @Override
    public CpOpenData getCpOpenDataByDateAndOpenIndex(Date date,String openIndex) {

        List<CpOpenData> cpOpenDataByDate = this.getCpOpenDataByDate(date);

        if(CollectionUtils.isEmpty(cpOpenDataByDate)){
            return null;
        }

        return cpOpenDataByDate.get(cpOpenDataByDate.size()-1);
    }

    @Override
    public List<CpOpenData> getCpOpenDataByDate(Date date) {
        CpOpenData cpOpenData = new CpOpenData();
        cpOpenData.setOpenDate(DateUtil.formatDate(date,DateUtil.PATTERN_DATE));


        List<CpOpenData> select = cpOpenDataMapper.select(cpOpenData);
        if(CollectionUtils.isEmpty(select)){
            return null;
        }
        select.sort(new CpOpenDataComparator());

        CpOpenData cpOpenDataLast = select.get( select.size() - 1 );

        List<CpOpenData> collect = select.stream().filter(x -> {
            return x.getOpenTrunkNumber().equals(cpOpenDataLast.getOpenTrunkNumber());
        }).collect(Collectors.toList());


        return collect;
    }

    @Override
    public Boolean validateOpenData(Date date,int mimute){
        CpOpenData cpOpenDataByDate = this.getCpOpenDataByDateAndOpenIndex(date,"0");
        if(cpOpenDataByDate == null || DateUtil.dateDiff(cpOpenDataByDate.getOpenTime(),date,DateUtil.DATE_DIFF_TYPE_THREE) > mimute) return Boolean.FALSE;
        return Boolean.TRUE;
    }


    @Scheduled(cron="40 2/5 0-23 * * ? ")
    public void schedule() throws IOException, ParseException {
        Date date = new Date();
        logger.info("调度方法开始运行......");

        for(int i = 0; i < 4 ; i++){

            Boolean aBoolean = insertToDayLastNewData(date);
            if (aBoolean) {
                logger.info("当前数据是最新的......");
            }else {
                logger.info("当前数据不是最新的......");
            }

        }

        List<CpOpenData> cpOpenDataByDate = this.getCpOpenDataByDate(date);
        logger.error(cpOpenDataByDate.toString());
    }

    /**
     * 连续多少期内,开过的数字不超过多少种
     * @param trunkNumbers 连续期数
     * @param upNumbers    不同数字
     */
    public List<CpOpenData> anlyziByEndCpOpenData(CpOpenData cpOpenData,int trunkNumbers,int upNumbers){
        Integer lastTrunkNumber = Integer.valueOf(cpOpenData.getOpenTrunkNumber());

        List<CpOpenData> list = new ArrayList<>();
        while (true){
            CpOpenData cpOpenDataSelect = selectByCpTrunkNumberAndIndex(lastTrunkNumber + "", cpOpenData.getOpenIndex());
            list.add(cpOpenDataSelect);

            Set<String> collect = list.stream().map(x -> {
                return x.getOpenNumber();
            }).collect(Collectors.toSet());
            if(collect.size() > upNumbers){
                break;
            }
            lastTrunkNumber--;
        }

        CpOpenData remove = list.remove(list.size()-1);

        if (list.size() >= trunkNumbers) return list;

        return null;
    }

    public CpOpenData selectByCpTrunkNumberAndIndex(String trunkNumber,String index){
        CpOpenData cpOpenData = new CpOpenData();
        cpOpenData.setOpenTrunkNumber(trunkNumber);
        cpOpenData.setOpenIndex(index);
        return cpOpenDataMapper.selectOne(cpOpenData);
    }

}
