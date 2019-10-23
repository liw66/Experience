package com.li.experience;

import com.sun.management.OperatingSystemMXBean;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-04-12 14:22
 **/
@EnableCaching
@SpringBootApplication
@EnableTransactionManagement
public class ExperienceApplication {
    //应用程序入口
    public static void main(String[] args) {
        SpringApplication.run(ExperienceApplication.class, args);

        OperatingSystemMXBean o = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long sys_total = o.getTotalPhysicalMemorySize() / 1048576;
        long sys_free = o.getFreePhysicalMemorySize() / 1048576;
        long sys_use = sys_total - sys_free;

        System.out.println("操作系统版本：" + System.getProperty("os.name"));
        System.out.println("总的系统内存：" + sys_total + "MB");
        System.out.println("可用系统内存：" + sys_free + "MB");
        System.out.println("使用系统内存：" + sys_use + "MB");

        Runtime rt = Runtime.getRuntime();
        long total = rt.totalMemory() / 1048576;
        long free = rt.freeMemory() / 1048576;
        long use = total - free;

        System.out.println("总的JVM内存：" + total + "MB");
        System.out.println("可用JVM内存：" + free + "MB");
        System.out.println("使用JVM内存：" + use + "MB");

        String now = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(now + "  Experience启动成功  ok");

        /*try {

            //类加载器
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Class clazz = classLoader.loadClass("org.apache.commons.lang3.StringUtils");

            //构造函数
            Constructor[] c = clazz.getDeclaredConstructors();
            System.out.print("构造函数：");
            for (Constructor v : c) {
                System.out.print(v.getName() + " ");
            }
            System.out.println();
            //内部方法
            Method[] m = clazz.getDeclaredMethods();
            System.out.print("内部方法：");
            for (Method v : m) {
                System.out.print(v.getName() + " ");
            }
            System.out.println();
            //成员变量
            Field[] f = clazz.getDeclaredFields();
            System.out.print("成员变量：");
            for (Field v : f) {
                System.out.print(v.getName() + " ");
            }
            System.out.println();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }
}
