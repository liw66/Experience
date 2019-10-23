package com.li.experience.common.utils;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-08-14 16:07
 **/
public class HttpUtils {

    /**
     * 是否是Ajax异步请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {

        String accept = request.getHeader("accept");
        if (accept != null && accept.indexOf("application/json") != -1) {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1) {
            return true;
        }

        return false;
    }

    public static Map getAllRequestParam(HttpServletRequest request) {
        String type = request.getMethod();
        if (StringUtils.equals(type,"GET")){
            return getHeader(request);
        }else{
            return getBody(request);
        }
    }

    private static Map getHeader(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        Map<String, String> map = new HashMap();
        int start = url.indexOf("?");
        if (start >= 0) {
            String str = url.substring(start + 1);
            System.out.println(str);
            String[] paramsArr = str.split("&");
            for (String param : paramsArr) {
                String[] temp = param.split(":");
                map.put(temp[0], temp[1]);
            }
        }
        return map;
    }

    private static Map getBody(HttpServletRequest request) {
        Map<String, String> res = new HashMap();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
            }
        }
        return res;
    }
}
