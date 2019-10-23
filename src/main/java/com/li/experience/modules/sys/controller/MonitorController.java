package com.li.experience.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-04-18 11:59
 **/
@Controller
@RequestMapping("/sys/monitor")
public class MonitorController {

    @GetMapping("/jvminfo")
    public String JvmInfo(){
        return "/modules/sys/monitor/jvminfo";
    }

    @GetMapping("/systeminfo")
    public String SystemInfo(){ return "/modules/sys/monitor/systeminfo"; }

    @GetMapping("/tomcatinfo")
    public String TomcatInfo(){ return "/modules/sys/monitor/tomcatinfo"; }

    @GetMapping("/httptrace")
    public String HttpTrace(){ return "/modules/sys/monitor/httptrace"; }

}
