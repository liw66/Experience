package com.li.experience.modules.sys.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.li.experience.common.core.Page;
import com.li.experience.common.core.Query;
import com.li.experience.common.core.Result;
import com.li.experience.modules.sys.domain.*;
import com.li.experience.modules.sys.service.DictService;
import com.li.experience.modules.sys.service.RoleService;
import com.li.experience.modules.sys.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.li.experience.common.core.Result.error;
import static com.li.experience.common.core.Result.ok;

/**
 * 用户信息表
 *
 * @author Liwei
 * @email liw66@163.com
 * @date 2019-05-09 16:09:52
 */

@Controller
@RequestMapping("/sys/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DictService dictService;
    /**
     * 入口
     */
    @GetMapping()
    //@RequiresPermissions("sys:user:user")
    public String User() {
        return "/modules/sys/user/index";
    }

    /**
     * 列表
     */
    @ResponseBody
    @GetMapping("/list")
    //@RequiresPermissions("sys:user:user")
    public Page list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<UserDO> userList = userService.list(query);
        int total = userService.count(params);
        Page page = new Page(userList, total);
        return page;
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{userid}")
    //@RequiresPermissions("sys:user:edit")
    String edit(@PathVariable("userid") Long userid, Model model) {
        UserVO user = new UserVO();
        if (userid > 0) {
            user = userService.get(userid);
        }
        List<RoleDO> roles = roleService.list(new HashMap<>());
        Map map = new HashMap();
        map.put("type","sex");
        List<DictDO> sex = dictService.list(map);
        model.addAttribute("sex",sex);
        model.addAttribute("roles",roles);
        model.addAttribute("user", user);
        return "/modules/sys/user/form";
    }

    /**
     * 用户名是否存在
     */
    @ResponseBody
    @PostMapping("/isExist")
    public boolean isExist(@RequestParam Map<String,Object> param){
        UserDO userDO = userService.getByName(param.get("username").toString());
        if (userDO != null){
            return false;//存在不通过
        }
        return true;
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    //@RequiresPermissions("sys:user:add")
    public Result save(UserVO user) {
        int val;
        if (user.getUserid() != null) {
            user.setUpdatedat(new Date());
            user.setUpdatedby(SecurityUtils.getSubject().getPrincipal().toString());
            val = userService.update(user);
        } else {
            String pwd = DigestUtils.md5Hex(user.getUsername()+user.getPassword());
            user.setPassword(pwd);
            user.setCreatedat(new Date());
            user.setCreatedby(SecurityUtils.getSubject().getPrincipal().toString());
            val = userService.save(user);
        }
        if (val > 0) {
            return ok();
        }
        return error();

    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    //@RequiresPermissions("sys:user:remove")
    public Result remove(Long userid) {
        if (userService.remove(userid) > 0) {
            return ok();
        }
        return error();
    }

    /**
     * 批量删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    //@RequiresPermissions("sys:user:batchRemove")
    public Result remove(@RequestParam("ids[]") Long[] userids) {
        if (userService.batchRemove(userids) > 0) {
            return ok();
        }
        return error();
    }

    /**
     * 导出
     */
    @GetMapping("/export")
    public void export(@RequestParam Map<String, Object> params,HttpServletResponse response) {
        List<UserAO> userList = userService.export(params);
        ExportParams exportParams = new ExportParams("用户表", "user");
        exportParams.setType(ExcelType.XSSF);
        Workbook workbook = ExcelExportUtil.exportBigExcel(exportParams,UserAO.class, userList);
        ExcelExportUtil.closeExportBigExcel();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String dateTime = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);

        String fileName = "user"+dateTime+".xlsx";
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }
    }

    private void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改
     */
    @GetMapping("/resetPwd/{userid}")
    //@RequiresPermissions("sys:user:edit")
    public String resetPwd(@PathVariable("userid") Long userid, Model model) {
        UserVO user = new UserVO();
        if (userid > 0) {
            user = userService.get(userid);
        }
        model.addAttribute("user", user);
        return "/modules/sys/user/reset";
    }

    @PostMapping("/savePwd")
    @ResponseBody
    public Result savePwd(UserDO userDO){
        userDO.setUpdatedat(new Date());
        userDO.setUpdatedby(SecurityUtils.getSubject().getPrincipal().toString());
        String pwd = DigestUtils.md5Hex(userDO.getUsername()+userDO.getPassword());
        userDO.setPassword(pwd);
        int val = userService.savePwd(userDO);
        if (val > 0){
            return ok();
        }else{
            return error();
        }
    }
}
