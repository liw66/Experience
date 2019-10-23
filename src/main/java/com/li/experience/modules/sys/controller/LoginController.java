package com.li.experience.modules.sys.controller;

import com.li.experience.common.aop.Log;
import com.li.experience.common.core.Result;
import com.li.experience.common.core.Tree;
import com.li.experience.modules.sys.domain.UserDO;
import com.li.experience.modules.sys.service.MenuService;
import com.li.experience.modules.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.li.experience.common.core.Result.error;
import static com.li.experience.common.core.Result.ok;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-05-07 17:16
 **/
@Controller
public class LoginController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "/modules/sys/login/login";
    }

    @Log("登录")
    @PostMapping("/login")
    @ResponseBody
    public Result login(HttpServletRequest request) {
        String val = "";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return ok();
        }catch (UnknownAccountException e){
            val="账号不存在";
            return error(val);
        }catch (IncorrectCredentialsException e){
            val="密码不正确";
            return error(val);
        }catch (AuthenticationException e){
            val="验证失败";
            return error(val);
        }
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<Tree> menus = menuService.getMenu();
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        UserDO userDO = userService.getByName(username);
        model.addAttribute("user",userDO);
        model.addAttribute("menus",menus);
        return "/modules/sys/login/index";
    }

    @GetMapping("/logout")
    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
