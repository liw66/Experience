package com.li.experience.modules.sys.controller;

import com.li.experience.common.core.Page;
import com.li.experience.common.core.Query;
import com.li.experience.common.core.Result;
import com.li.experience.modules.sys.domain.TableDO;
import com.li.experience.modules.sys.service.CodeService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import static com.li.experience.common.core.Result.ok;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-07-19 13:56
 **/
@Controller
@RequestMapping("/sys/code")
public class CodeController {

    @Autowired
    private CodeService codeService;

    @GetMapping("")
    public String index(){
        return "/modules/sys/code/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public Page list(){
        List<TableDO> list = codeService.list();
        Page page = new Page(list,list.size());
        return page;
    }

    @RequestMapping("/code/{tableName}")
    public void code(HttpServletResponse response,@PathVariable("tableName") String tableName) throws IOException {
        String[] tableNames = new String[]{tableName};
        byte[] data = codeService.code(tableNames);
        response.reset();
        response.setHeader("Content-Disposition","attachment;filename=Experience.zip");
        response.addHeader("Content-Length",""+data.length);
        response.setContentType("application/octet-stream;charset=UTF-8");
        IOUtils.write(data,response.getOutputStream());
    }

    @RequestMapping("/batchCode/{tableNames}")
    public void batchCode(HttpServletResponse response,@PathVariable("tableNames") String[] tableNames) throws IOException{
        byte[] data = codeService.code(tableNames);
        response.reset();
        response.setHeader("Content-Disposition","attachment;filename=Experience.zip");
        response.setHeader("Content-Length",""+data.length);
        response.setContentType("application/octet-stream;charset=UTF-8");
        IOUtils.write(data,response.getOutputStream());
    }
}
