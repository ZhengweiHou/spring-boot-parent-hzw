package com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest;

import com.sun.deploy.xdg.BaseDir;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.AdaptiveClassCodeGenerator;
import org.apache.dubbo.rpc.Invocation;
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


}
