package com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest;

import org.apache.dubbo.common.extension.AdaptiveClassCodeGenerator;
import org.apache.dubbo.rpc.ProxyFactory;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName AdaptiveClassCodeGeneratorTest
 * @Description AdaptiveClassCodeGenerator 自适应扩展类代码生成器测试
 * @Author houzw
 * @Date 2020/7/17
 **/
public class AdaptiveClassCodeGeneratorTest {

    @Test
    public void test() throws IOException {
        //  issue : https://github.com/apache/dubbo/issues/6490   fix bug by issue https://github.com/apache/dubbo/pull/4339   version:2.7.4 up

        String hzw1 = new AdaptiveClassCodeGenerator(A1.class, "hzw").generate();

        String hzw2 = new AdaptiveClassCodeGenerator(A2.class, null).generate();

        String baseDir = "src/main/java/com/hzw/learn/springboot/dubbo/AdaptiveExtension/AdaptiveClassCodeGeneratorTest/";

        try(
            FileWriter fileWriter1 = new FileWriter(baseDir + "A1$Adaptive.java");
            FileWriter fileWriter2 = new FileWriter(baseDir + "A2$Adaptive.java");
        ){
            fileWriter1.write(hzw1);
            fileWriter2.write(hzw2);
        }

    }

    @Test
    public void showAdaptiveForAnyClass() throws IOException {

        Class clazz = ProxyFactory.class;
        String defaultExeName = "javassist";

        String code = new AdaptiveClassCodeGenerator(clazz,defaultExeName).generate();

        System.out.println(code);
        

        String baseDir = "src/main/java/com/hzw/learn/springboot/dubbo/AdaptiveExtension/AdaptiveClassCodeGeneratorTest/";

        try(
                FileWriter fileWriter = new FileWriter(baseDir + clazz.getSimpleName() +"$Adaptive.java");
        ){
            fileWriter.write(code);
        }

    }

}
