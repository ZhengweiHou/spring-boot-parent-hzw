package com.hzw.learn.springsecurity.xss;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.LookupTranslator;

import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @ClassName XssUtil
 * @Description TODO
 * @Author houzw
 * @Date 2022/11/2
 **/
public class XssUtil {

    private XssUtil(){}

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

    public static final Map<CharSequence, CharSequence> ESCAPEMAP;
    static {
        final Map<CharSequence, CharSequence> initialMap = new HashMap<>();
        initialMap.put("\"", "&quot;"); // " - double-quote
        initialMap.put("'", "&#39;");
        initialMap.put("&", "&amp;");   // & - ampersand
        initialMap.put("<", "&lt;");    // < - less-than
        initialMap.put(">", "&gt;");    // > - greater-than
        ESCAPEMAP = Collections.unmodifiableMap(initialMap);
    }

    public static String translate(String value){

//        AggregateTranslator trans = new AggregateTranslator(
//                new LookupTranslator(EntityArrays.BASIC_ESCAPE)
//        );
//        value = trans.translate(value);

//        value = StringEscapeUtils.escapeHtml4(value);

        value = filter(value);

//        value = value.replaceAll(" ","\\ ");
        return value;
    }

    public static String filter(final CharSequence  input) {
        if (input == null) {
            return null;
        }
        final StringWriter writer = new StringWriter(input.length() * 2);
        for (int i=0; i<input.length(); ++i) {
            CharSequence v = ESCAPEMAP.get(input.subSequence(i,i+1));
            if(v != null){
                writer.write(v.toString());
            }else {
                writer.write(input.charAt(i));
            }
        }
	        return writer.toString();
    }


    public static void main(String[] args) {
//        String value = "<script>alert(1)侯</script>;'";
//        String value = "'O\"O+O%O&O<>O（）O;";
        String value = ";";
        System.out.println(StringEscapeUtils.escapeHtml4(value));
        System.out.println(StringEscapeUtils.escapeXml11(value));
        System.out.println(StringEscapeUtils.escapeCsv(value));
        System.out.println(StringEscapeUtils.escapeEcmaScript(value));

        System.out.println(StringEscapeUtils.escapeXSI(value));
        System.out.println(StringEscapeUtils.escapeJava(value));
        System.out.println(value);

//        System.out.println(ESCAPEMAP.get("<"));
    }


}
