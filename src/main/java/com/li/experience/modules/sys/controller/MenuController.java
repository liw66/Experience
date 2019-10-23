package com.li.experience.modules.sys.controller;

import com.li.experience.common.core.Result;
import com.li.experience.common.core.Tree;
import com.li.experience.modules.sys.domain.DeptDO;
import com.li.experience.modules.sys.domain.MenuDO;
import com.li.experience.modules.sys.service.DeptService;
import com.li.experience.modules.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单表
 *
 * @author Liwei
 * @email liw66@163.com
 * @date 2019-06-25 16:09:52
 */

@Controller
@RequestMapping("/sys/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 入口
     */
    @GetMapping()
    //@RequiresPermissions("sys:dept:dept")
    public String Menu() {
        return "/modules/sys/menu/index";
    }

    /**
     * 列表
     */
    @ResponseBody
    @GetMapping("/list")
    //@RequiresPermissions("sys:dept:dept")
    public List list() {
        //查询列表数据
        List<MenuDO> menuList = menuService.list(new HashMap<>());
        //这里不能用分页 不然显示不出treegrid
        return menuList;
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{menuid}")
    //@RequiresPermissions("sys:dept:edit")
    String edit(@PathVariable("menuid") String menuid, Model model) {
        MenuDO menu;
        String parentName = "";
        if ("$1".equals(menuid)) {
            menu = new MenuDO();
            parentName = "无";
        } else if (menuid.endsWith("$2")) {
            menu = new MenuDO();
            long parentId = Long.parseLong((menuid.replace("$2", "")));
            parentName = menuService.get(parentId).getMenuname();
            menu.setParentid(parentId);
        } else {
            menu = menuService.get(Long.parseLong(menuid));
            if (menu.getParentid() == null) {
                parentName = "无";
            } else {
                parentName = menuService.get(menu.getParentid()).getMenuname();
            }
        }
        model.addAttribute("menu", menu);
        model.addAttribute("parentName", parentName);
        return "/modules/sys/menu/form";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    //@RequiresPermissions("sys:dept:add")
    public Result save(MenuDO menu) {
        int val;
        if (menu.getMenuid() == null) {
            menu.setCreatedat(new Date());
            val = menuService.save(menu);
        } else {
            menu.setUpdatedat(new Date());
            val = menuService.update(menu);
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
    //@RequiresPermissions("sys:dept:remove")
    public Result remove(Long menuid) {
        Map map = new HashMap();
        map.put("parentid",menuid);
        List<DeptDO> list = menuService.list(map);
        if (list.size() > 0){
            return new Result(1,"该部门下面有子部门,请先删除下级部门");
        }
        if (menuService.remove(menuid) > 0) {
            return Result.ok();
        }else{
            return Result.error();
        }
    }

}
