package com.springboot.zcssc.zcssc.mapper;

import com.springboot.zcssc.zcssc.bean.db.CpOpenData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CpOpenDataMapperTest {

    @Autowired
    CpOpenDataMapper cpOpenDataMapper;

    @Test
    public void select(){
        CpOpenData cpOpenData = cpOpenDataMapper.selectByPrimaryKey(1);
        System.out.println(cpOpenData);
    }
}
