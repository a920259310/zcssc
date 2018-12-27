package com.springboot.zcssc.session.dao.impl;

import com.springboot.zcssc.session.bean.RuYiBean;
import com.springboot.zcssc.session.dao.RuYiHttpDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuYiHttpDaoImplTest {

    @Autowired
    public RuYiHttpDao ruYiHttpDao;

    @Test
    public void getDataByTunkNum() throws IOException, ParseException {
        RuYiBean dataByTunkNum = ruYiHttpDao.getDataByTunkNum("719904");
        System.out.println(dataByTunkNum);
    }
}
