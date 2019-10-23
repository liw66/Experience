package com.li.experience.modules.sys.controller;

import com.li.experience.common.core.Page;
import com.li.experience.common.core.Query;
import com.li.experience.common.core.Result;
import com.li.experience.common.core.Tree;
import com.li.experience.modules.sys.domain.RoleDO;
import com.li.experience.modules.sys.domain.RoleVO;
import com.li.experience.modules.sys.service.MenuService;
import com.li.experience.modules.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 角色信息表
 *
 * @author Liwei
 * @email liw66@163.com
 * @date 2019-05-09 16:09:52
 */

@Controller
@RequestMapping("/sys/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 入口
     */
    @GetMapping()
        //@RequiresPermissions("sys:user:user")
    String Role() {
        return "/modules/sys/role/index";
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
        List<RoleDO> userList = roleService.list(query);
        int total = roleService.count(params);
        Page page = new Page(userList, total);
        return page;
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{roleid}")
        //@RequiresPermissions("sys:user:edit")
    String edit(@PathVariable("roleid") Long roleid, Model model) {
        RoleVO role = new RoleVO();
        if (roleid > 0) {
            role = roleService.get(roleid);
        }
        model.addAttribute("role", role);
        return "/modules/sys/role/form";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    //@RequiresPermissions("sys:user:add")
    public Result save(RoleVO role) {
        int val;
        if (role.getRoleid() != null) {
            role.setUpdatedat(new Date());
            val = roleService.update(role);
        } else {
            role.setCreatedat(new Date());
            val = roleService.save(role);
        }
        if (val > 0) {
            return Result.ok();
        }
        return Result.error();

    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    //@RequiresPermissions("sys:user:remove")
    public Result remove(Long roleid) {
        if (roleService.remove(roleid) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 批量删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    //@RequiresPermissions("sys:user:batchRemove")
    public Result remove(@RequestParam("ids[]") Long[] roleids) {
        if (roleService.batchRemove(roleids) > 0) {
            return Result.ok();
        }
        return Result.error();
    }


    /**
     * 获取菜单树
     */
    @GetMapping("/getRoleMenu")
    @ResponseBody
    public Result getRoleMenu(Long roleid){
        Tree menus = roleService.getRoleMenu(roleid);
        return new Result(menus);
    }
}
