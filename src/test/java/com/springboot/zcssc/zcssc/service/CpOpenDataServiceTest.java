package com.springboot.zcssc.zcssc.service;

import com.springboot.zcssc.zcssc.bean.db.CpOpenData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CpOpenDataServiceTest {
    @Autowired
    CpOpenDataService cpOpenDataService;


    @Test
    public void get(){
        CpOpenData cpOpenData = cpOpenDataService.queryById(1);
        System.out.println(cpOpenData);
    }

    @Test
    public void insertDataByDate() throws IOException, ParseException {
//        cpOpenDataService.insertDataByDate(new Date());

        cpOpenDataService.insertToDayLastNewData(new Date());
    }

    @Test
    public void anlyziByEndCpOpenData(){
        CpOpenData cpOpenData = new CpOpenData();
        cpOpenData.setOpenTrunkNumber("722702");
        cpOpenData.setOpenIndex("0");

        List<CpOpenData> cpOpenData1 = cpOpenDataService.anlyziByEndCpOpenData(cpOpenData, 6, 5);
        System.out.println(cpOpenData1);
    }

}
