package com.li.experience.modules.sys.controller;

import com.li.experience.common.core.Page;
import com.li.experience.common.core.Query;
import com.li.experience.common.core.Result;
import com.li.experience.modules.sys.domain.DictDO;
import com.li.experience.modules.sys.service.DictService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 字典信息表
 *
 * @author Liwei
 * @email liw66@163.com
 * @date 2019-08-16 11:15:38
 */

@Controller
@RequestMapping("/sys/dict")
public class DictController {
    @Autowired
    private DictService dictService;

    /**
     * 入口
     */
    @GetMapping()
    //@RequiresPermissions("sys:dict:dict")
    String Dict() {
        return "/modules/sys/dict/index";
    }

    /**
     * 列表
     */
    @ResponseBody
    @GetMapping("/list")
    //@RequiresPermissions("sys:dict:dict")
    public Page list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<DictDO> dictList = dictService.list(query);
        int total = dictService.count(params);
        Page page = new Page(dictList, total);
        return page;
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{dictid}")
    //@RequiresPermissions("sys:dict:edit")
    String edit(@PathVariable("dictid") Long dictid, Model model) {
        DictDO dict = new DictDO();
        if (dictid > 0) {
            dict = dictService.get(dictid);
        }
        model.addAttribute("dict", dict);
        return "/modules/sys/dict/form";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    //@RequiresPermissions("sys:dict:add")
    public Result save(DictDO dict) {
        int val;
        if (dict.getDictid() != null) {
            dict.setUpdatedat(new Date());
            dict.setUpdatedby(SecurityUtils.getSubject().getPrincipal().toString());
            val = dictService.update(dict);
        } else {
            dict.setCreatedat(new Date());
            dict.setCreatedby(SecurityUtils.getSubject().getPrincipal().toString());
            val = dictService.save(dict);
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
    //@RequiresPermissions("sys:dict:remove")
    public Result remove(Long dictid) {
        if (dictService.remove(dictid) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 批量删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    //@RequiresPermissions("sys:dict:batchRemove")
    public Result remove(@RequestParam("ids[]") Long[] dictids) {
        if (dictService.batchRemove(dictids) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

}
