package com.springboot.zcssc.utils;

import com.springboot.zcssc.session.bean.RuYiBean;
import com.springboot.zcssc.zcssc.bean.db.CpOpenData;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaiJiBeanToDbBeanUtil {

    /**
     * 如采集的数据转换成数据库的实体
     * @param ruYiBean
     * @return
     * @throws ParseException
     */
    public static List<CpOpenData> ruYiToDbModel(RuYiBean ruYiBean) throws ParseException {

        if (ruYiBean == null) return null;

        List<CpOpenData> list = new ArrayList<>();

        String openNum = ruYiBean.getOpenNum();
        String[] split = openNum.split(",");

        for(int i = 0;i < split.length ; i++){

            CpOpenData cpOpenData = new CpOpenData();

            cpOpenData.setInsertTime(new Date());
            cpOpenData.setOpenIndex(""+i);
            cpOpenData.setOpenNumber(split[i]);
            cpOpenData.setOpenTime(DateUtil.parseDate(ruYiBean.getOpenTime(),DateUtil.PATTERN_DATE_TIME));
            cpOpenData.setOpenDate(DateUtil.formatDate(DateUtil.parseDate(ruYiBean.getOpenTime(),DateUtil.PATTERN_DATE_TIME),DateUtil.PATTERN_DATE));
            cpOpenData.setOpenTrunkNumber(ruYiBean.getTurnNum());

            list.add(cpOpenData);
        }


        return list;
    }

    /**
     * 如采集的数据集合转换成数据库的实体
     * @param ruYiBeans
     * @return
     * @throws ParseException
     */
    public static List<CpOpenData> ruYiToDbModel(List<RuYiBean> ruYiBeans) throws ParseException {

        if (CollectionUtils.isEmpty(ruYiBeans)) return null;
        List<CpOpenData> list = new ArrayList<>();
        for (RuYiBean ruYiBean : ruYiBeans){
            List<CpOpenData> cpOpenData = ruYiToDbModel(ruYiBean);
            list.addAll(cpOpenData);
        }

        return list;
    }


}
