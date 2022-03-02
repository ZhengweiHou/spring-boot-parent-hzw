package com.hzw.learn.springboot.freemarker;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

public class SimpleTest {

    @Test
    public void test_0() throws IOException, TemplateException {
        Configuration cfg = new Configuration();
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        templateLoader.putTemplate("hello","=${value1}=");
        cfg.setTemplateLoader(templateLoader);
        cfg.setTemplateUpdateDelayMilliseconds(-1); // 设置模板缓存为负数，不缓存

        StringWriter sw = new StringWriter();
        Template template = cfg.getTemplate("hello");
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("value1","hello");
        template.process(values,sw);

        System.out.println(sw.toString());
    }
}
