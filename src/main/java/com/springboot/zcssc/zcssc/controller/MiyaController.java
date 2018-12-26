package com.springboot.zcssc.zcssc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MiyaController {
    @Value("${my.name}")
    private String name;
    @Value("${my.age}")
    private Integer age;

    @RequestMapping("/d2/miya")
    public String getMessage(){

        return "第二篇的自定义配置属性   name=" + name + "    age="+age;
    }
}
