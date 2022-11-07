package com.hzw.learn.springsecurity.xss;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import java.util.regex.Pattern;

/**
 * @ClassName XssUtil
 * @Description TODO
 * @Author houzw
 * @Date 2022/11/2
 **/
public class XssUtil {
    private final static Pattern SCRIPT_PATTERN     = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
    private final static Pattern SRC_PATTERN_ONE    = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private final static Pattern SRC_PATTERN_TWO    = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private final static Pattern SCRIPT_PATTERN_ONE = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
    private final static Pattern SCRIPT_PATTERN_TWO = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private final static Pattern EVAL_PATTERN       = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private final static Pattern EXPRESSION_PATTERN = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    private final static Pattern JAVASCRIPT_PATTERN = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
    private final static Pattern VB_SCRIPT_PATTERN  = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
    private final static Pattern ON_LOAD_PATTERN    = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);


    private XssUtil(){}


    public static String stripXSS(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = SCRIPT_PATTERN.matcher(value).replaceAll("");
            value = SRC_PATTERN_ONE.matcher(value).replaceAll("");
            value = SRC_PATTERN_TWO.matcher(value).replaceAll("");
            value = SCRIPT_PATTERN_ONE.matcher(value).replaceAll("");
            value = SCRIPT_PATTERN_TWO.matcher(value).replaceAll("");
            value = EVAL_PATTERN.matcher(value).replaceAll("");
            value = EXPRESSION_PATTERN.matcher(value).replaceAll("");
            value = JAVASCRIPT_PATTERN.matcher(value).replaceAll("");
            value = VB_SCRIPT_PATTERN.matcher(value).replaceAll("");
            value = ON_LOAD_PATTERN.matcher(value).replaceAll("");
        }
        return value;
    }

    public static String translate(String value){
        value = StringEscapeUtils.escapeHtml4(value);
        value = value.replaceAll(" ","\\ ");
//        value = StringEscapeUtils.escapeXSI(value);
        return value;
    }

    public static void main(String[] args) {
        String value = "<script>alert(1)</script>";
        value = StringEscapeUtils.escapeHtml4(value);
        System.out.println(value);
    }


}
