package com.springboot.zcssc.zcssc.controller;

import com.springboot.zcssc.session.dao.impl.RuYiWebDriverDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebDriverController {
    @Autowired
    RuYiWebDriverDao ruYiWebDriverDao;

    @RequestMapping("/web/session/ruyi")
    @ResponseBody
    public String web(){
        System.out.println(ruYiWebDriverDao);
        return "11111";
    }
}
