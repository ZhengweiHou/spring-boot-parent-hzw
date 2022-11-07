package com.hzw.learn.springsecurity.xss;

import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

/**
 * @ClassName XssHttpServletRequestWrapper
 * @Description TODO
 * @Author houzw
 * @Date 2022/11/1
 **/
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    final public HttpServletRequest origRequest;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        origRequest = request;
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(replaceXSS(name));
        if (value != null) {
            value = replaceXSS(value);
        }

        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(replaceXSS(name));
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                values[i] = replaceXSS(values[i]);
            }
        }
        return values;
    }

    public static String replaceXSS(String value){
//        value = XssUtil.stripXSS(value);    // 删除敏感字串
        value = XssUtil.translate(value);   // 翻译特殊字符
        return value;
    }


    public HttpServletRequest getOrigRequest(){
        return origRequest;
    }

}
