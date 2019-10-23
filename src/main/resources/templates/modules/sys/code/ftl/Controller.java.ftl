package com.li.experience.modules.app.controller;

import com.li.experience.common.core.Page;
import com.li.experience.common.core.Query;
import com.li.experience.common.core.Result;
import com.li.experience.modules.app.domain.${className}DO;
import com.li.experience.modules.app.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ${tableComment}
 *
 * @author Liwei
 * @email liw66@163.com
 * @date ${createTime}
 */

@Controller
@RequestMapping("/app/${lowerName}")
public class ${className}Controller {
    @Autowired
    private ${className}Service ${lowerName}Service;

    /**
     * 入口
     */
    @GetMapping()
    //@RequiresPermissions("app:${lowerName}:${lowerName}")
    String ${className}() {
        return "/modules/app/${lowerName}/index";
    }

    /**
     * 列表
     */
    @ResponseBody
    @GetMapping("/list")
    //@RequiresPermissions("app:${lowerName}:${lowerName}")
    public Page list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<${className}DO> ${lowerName}List = ${lowerName}Service.list(query);
        int total = ${lowerName}Service.count(params);
        Page page = new Page(${lowerName}List, total);
        return page;
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{${pk}}")
    //@RequiresPermissions("app:${lowerName}:edit")
    String edit(@PathVariable("${pk}") Long ${pk}, Model model) {
        ${className}DO ${lowerName} = new ${className}DO();
        if (${pk} > 0) {
            ${lowerName} = ${lowerName}Service.get(${pk});
        }
        model.addAttribute("${lowerName}", ${lowerName});
        return "/modules/app/${lowerName}/form";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    //@RequiresPermissions("app:${lowerName}:add")
    public Result save(${className}DO ${lowerName}) {
        int val;
        if (${lowerName}.get${className}id() != null) {
            ${lowerName}.setUpdatedat(new Date());
            ${lowerName}.setUpdatedby(SecurityUtils.getSubject().getPrincipal().toString());
            val = ${lowerName}Service.update(${lowerName});
        } else {
            ${lowerName}.setCreatedat(new Date());
            ${lowerName}.setCreatedby(SecurityUtils.getSubject().getPrincipal().toString());
            val = ${lowerName}Service.save(${lowerName});
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
    //@RequiresPermissions("app:${lowerName}:remove")
    public Result remove(Long ${pk}) {
        if (${lowerName}Service.remove(${pk}) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 批量删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    //@RequiresPermissions("app:${lowerName}:batchRemove")
    public Result remove(@RequestParam("ids[]") Long[] ${pk}s) {
        if (${lowerName}Service.batchRemove(${pk}s) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

}
