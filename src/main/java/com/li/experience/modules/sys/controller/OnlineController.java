package com.li.experience.modules.sys.controller;

import com.li.experience.common.core.Page;
import com.li.experience.common.core.Query;
import com.li.experience.common.core.Result;
import com.li.experience.modules.sys.domain.OnlineVO;
import com.li.experience.modules.sys.domain.RoleDO;
import com.li.experience.modules.sys.domain.UserVO;
import com.li.experience.modules.sys.service.OnlineService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.li.experience.common.core.Result.ok;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-07-12 15:45
 **/
@RequestMapping("/sys/online")
@Controller
public class OnlineController {

    @Autowired
    private OnlineService onlineService;
    @GetMapping("")
    public String index(){
        return "/modules/sys/online/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public Page list() {
        //查询列表数据
        List<OnlineVO> list = onlineService.list();
        int total = list.size();
        Page page = new Page(list, total);
        return page;
    }

    @PostMapping("/remove")
    @ResponseBody
    public Result remove(String sessionId){
        onlineService.remove(sessionId);
        return ok();
    }
}
