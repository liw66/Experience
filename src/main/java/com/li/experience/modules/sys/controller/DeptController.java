package com.li.experience.modules.sys.controller;

import com.li.experience.common.core.Result;
import com.li.experience.common.core.Tree;
import com.li.experience.modules.sys.domain.DeptDO;
import com.li.experience.modules.sys.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门信息表
 *
 * @author Liwei
 * @email test@163.com
 * @date 2019-05-09 16:09:52
 */

@Controller
@RequestMapping("/sys/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * 入口
     */
    @GetMapping()
    //@RequiresPermissions("sys:dept:dept")
    public String Dept() {
        return "/modules/sys/dept/index";
    }

    /**
     * 列表
     */
    @ResponseBody
    @GetMapping("/list")
    //@RequiresPermissions("sys:dept:dept")
    public List list() {
        //查询列表数据
        List<DeptDO> deptList = deptService.list(new HashMap<>());
        //这里不能用分页 不然显示不出treegrid
        return deptList;
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{deptid}")
        //@RequiresPermissions("sys:dept:edit")
    String edit(@PathVariable("deptid") String deptid, Model model) {
        DeptDO dept;
        String parentName = "";
        if ("$1".equals(deptid)) {
            dept = new DeptDO();
            parentName = "无";
        } else if (deptid.endsWith("$2")) {
            dept = new DeptDO();
            long parentId = Long.parseLong((deptid.replace("$2", "")));
            parentName = deptService.get(parentId).getDeptname();
            dept.setParentid(parentId);
        } else {
            dept = deptService.get(Long.parseLong(deptid));
            if (dept.getParentid() == null) {
                parentName = "无";
            } else {
                parentName = deptService.get(dept.getParentid()).getDeptname();
            }
        }
        model.addAttribute("dept", dept);
        model.addAttribute("parentName", parentName);
        return "/modules/sys/dept/form";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    //@RequiresPermissions("sys:dept:add")
    public Result save(DeptDO dept) {
        int val;
        if (dept.getDeptid() == null) {
            dept.setCreatedat(new Date());
            val = deptService.save(dept);
        } else {
            dept.setUpdatedat(new Date());
            val = deptService.update(dept);
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
    public Result remove(Long deptid) {
        Map map = new HashMap();
        map.put("parentid",deptid);
        List<DeptDO> list = deptService.list(map);
        if (list.size() > 0){
            return new Result(1,"该部门下面有子部门,请先删除下级部门");
        }
        if (deptService.remove(deptid) > 0) {
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    /**
     * 显示部门树
     */
    @GetMapping("/tree")
    public String tree(){
        return "/modules/sys/dept/tree";
    }

    /**
     * 获取部门树
     */
    @GetMapping("/getTree")
    @ResponseBody
    public Result getTree(){
        List<Tree> tree = deptService.getTree();
        return new Result(tree);
    }
}
