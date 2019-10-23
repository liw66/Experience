package com.li.experience.common.utils;

import com.li.experience.modules.sys.domain.TableDO;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-07-23 16:45
 **/
public class CodeUtils {

    public static List<String> getVmTemplates() {
        List<String> templates = new ArrayList<>();
        templates.add("templates/modules/sys/code/vm/Mapper.xml.vm");
        templates.add("templates/modules/sys/code/vm/index.html.vm");
        templates.add("templates/modules/sys/code/vm/form.html.vm");
        templates.add("templates/modules/sys/code/vm/index.js.vm");
        templates.add("templates/modules/sys/code/vm/form.js.vm");
        templates.add("templates/modules/sys/code/vm/Controller.java.vm");
        templates.add("templates/modules/sys/code/vm/Dao.java.vm");
        templates.add("templates/modules/sys/code/vm/DO.java.vm");
        templates.add("templates/modules/sys/code/vm/Service.java.vm");
        templates.add("templates/modules/sys/code/vm/ServiceImpl.java.vm");
        return templates;
    }

    public static List<String> getFtlTemplates() {
        List<String> templates = new ArrayList<>();
        templates.add("templates/modules/sys/code/ftl/Mapper.xml.ftl");
        templates.add("templates/modules/sys/code/ftl/index.html.ftl");
        templates.add("templates/modules/sys/code/ftl/form.html.ftl");
        templates.add("templates/modules/sys/code/ftl/index.js.ftl");
        templates.add("templates/modules/sys/code/ftl/form.js.ftl");
        templates.add("templates/modules/sys/code/ftl/Controller.java.ftl");
        templates.add("templates/modules/sys/code/ftl/Dao.java.ftl");
        templates.add("templates/modules/sys/code/ftl/DO.java.ftl");
        templates.add("templates/modules/sys/code/ftl/Service.java.ftl");
        templates.add("templates/modules/sys/code/ftl/ServiceImpl.java.ftl");
        return templates;
    }

    public static void buildVmCode(TableDO table, ZipOutputStream zip) throws IOException {
        VelocityEngine engine = new VelocityEngine();
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        engine.init();

        VelocityContext context = new VelocityContext();
        context.put("tableName", table.getTableName());
        context.put("tableComment",table.getTableComment());
        context.put("createTime",table.getCreateTime());
        context.put("columns", table.getColumns());
        String pk = table.getColumns().stream().filter(t -> t.getColumnKey().equals("PRI"))
                .collect(Collectors.toList()).get(0).getColumnName();
        if (StringUtils.isBlank(pk)) {
            pk = table.getColumns().get(0).getColumnName();
        }
        context.put("pk", pk);
        String className = WordUtils.capitalizeFully(table.getTableName(), new char[]{'_'}).split("_")[1];
        context.put("className", className);
        String lowerName = WordUtils.uncapitalize(className);
        context.put("lowerName", lowerName);
        List<String> list = getVmTemplates();
        for (String s : list) {
            StringWriter sw = new StringWriter();
            Template t = engine.getTemplate(s, "UTF-8");
            t.merge(context, sw);
            String path = getZipPath(s, className, "vm");
            ZipEntry z = new ZipEntry(path);
            zip.putNextEntry(z);
            IOUtils.write(sw.toString(), zip, "UTF-8");
            zip.closeEntry();
        }
    }

    public static void buildFtlCode(TableDO table, ZipOutputStream zip) throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_20);
        String pathName = new File("").getCanonicalPath() + "/src/main/resources/templates/modules/sys/code/ftl";
        configuration.setDirectoryForTemplateLoading(new File(pathName));
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setCacheStorage(NullCacheStorage.INSTANCE);

        Map context = new LinkedHashMap();
        context.put("tableName", table.getTableName());
        context.put("tableComment",table.getTableComment());
        context.put("createTime",table.getCreateTime());
        context.put("columns", table.getColumns());
        String pk = table.getColumns().stream().filter(t -> t.getColumnKey().equals("PRI"))
                .collect(Collectors.toList()).get(0).getColumnName();
        if (StringUtils.isBlank(pk)) {
            pk = table.getColumns().get(0).getColumnName();
        }
        context.put("pk", pk);
        String className = WordUtils.capitalizeFully(table.getTableName(), new char[]{'_'}).split("_")[1];
        context.put("className", className);
        String lowerName = WordUtils.uncapitalize(className);
        context.put("lowerName", lowerName);
        List<String> list = getFtlTemplates();
        for(String s : list){
            try (StringWriter sw = new StringWriter()) {
                String fileName = s.substring(s.lastIndexOf("/")+1);
                freemarker.template.Template template = configuration.getTemplate(fileName);
                template.process(context, sw);
                String path = getZipPath(s, className,"ftl");
                ZipEntry z = new ZipEntry(path);
                zip.putNextEntry(z);
                IOUtils.write(sw.toString(), zip,"UTF-8");
                zip.closeEntry();
            } catch (TemplateException e) {
                throw new RuntimeException("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }
    }

    private static String getZipPath(String tempPath, String className, String type) {
        String val = "";
        String realPath = tempPath.replace("." + type, "");
        String filePath = realPath.substring(realPath.lastIndexOf("/")+1);
        String suffix = StringUtils.split(filePath,".")[1];
        String fileName = StringUtils.split(filePath,".")[0];
        String lowerName = WordUtils.uncapitalize(className);
        String sep = File.separator;
        switch (suffix) {
            case "xml":
                val = "src" + sep + "main" + sep + "resources" + sep + "mybatis" + sep + "modules" + sep + "app" + sep + className + filePath;
                break;
            case "html":
                val = "src" + sep + "main" + sep + "resources" + sep + "templates" + sep + "modules" + sep + "app" + sep + lowerName + sep + filePath;
                break;
            case "js":
                val = "src" + sep + "main" + sep + "resources" + sep + "static" + sep + "js" + sep + "modules" + sep + "app" + sep + lowerName + sep + filePath;
                break;
            case "java":
                switch (fileName) {
                    case "Controller":
                        val = "src" + sep + "main" + sep + "java" + sep + "com" + sep + "li" + sep + "experience" + sep + "modules" + sep + "app" + sep + "controller" + sep + className + filePath;
                        break;
                    case "Dao":
                        val = "src" + sep + "main" + sep + "java" + sep + "com" + sep + "li" + sep + "experience" + sep + "modules" + sep + "app" + sep + "dao" + sep + className + filePath;
                        break;
                    case "DO":
                        val = "src" + sep + "main" + sep + "java" + sep + "com" + sep + "li" + sep + "experience" + sep + "modules" + sep + "app" + sep + "domain" + sep + className + filePath;
                        break;
                    case "Service":
                        val = "src" + sep + "main" + sep + "java" + sep + "com" + sep + "li" + sep + "experience" + sep + "modules" + sep + "app" + sep + "service" + sep + className + filePath;
                        break;
                    case "ServiceImpl":
                        val = "src" + sep + "main" + sep + "java" + sep + "com" + sep + "li" + sep + "experience" + sep + "modules" + sep + "app" + sep + "service" + sep + "impl" + sep + className + filePath;
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return val;
    }

    public static String getJavaType(String dataType){
        String val;
        switch(dataType){
            case "bigint":
                val = "Long" ;
                break;
            case "varchar":
                val = "String" ;
                break;
            case "int":
                val = "Long" ;
                break;
            case "datetime":
                val = "Date" ;
                break;
            case "tinyint":
                val = "Integer";
                break;
            default:
                val = "String";
                break;
        }
        return val;
    }
}
