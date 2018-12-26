package com.springboot.zcssc.zcssc.controller;

import com.springboot.zcssc.session.service.RuYiWebDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebDriverController {
    @Autowired
    RuYiWebDriverService ruYiWebDriverService;

    @RequestMapping("/web/session/ruyi")
    @ResponseBody
    public String web(){
        System.out.println(ruYiWebDriverService);
        return "11111";
    }
}
