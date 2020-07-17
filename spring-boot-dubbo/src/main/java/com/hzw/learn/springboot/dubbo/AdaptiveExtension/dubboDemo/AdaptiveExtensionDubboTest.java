package com.hzw.learn.springboot.dubbo.AdaptiveExtension.dubboDemo;

import com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest.A1;
import com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest.A2;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.AdaptiveClassCodeGenerator;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName AdaptiveExtensionDubboTest
 * @Description 自适应扩展Dubbo实现
 * @Author houzw
 * @Date 2020/7/16
 **/
public class AdaptiveExtensionDubboTest {


    @Test
    public void refillTest(){
        ExtensionLoader<RefillMaker> extensionLoader = ExtensionLoader.getExtensionLoader(RefillMaker.class);
        RefillMaker adaptiveRefillMaker = extensionLoader.getAdaptiveExtension();

        URL url = URL.valueOf("hzw://xxx.xxx.xxx.xxx:20880/hzwService?refill.maker=redmaker");
        adaptiveRefillMaker.makeRefill(url);

        url = URL.valueOf("hzw://xxx.xxx.xxx.xxx:20880/hzwService?refill.maker=bluemaker");
        adaptiveRefillMaker.makeRefill(url);

        url = URL.valueOf("hzw://xxx.xxx.xxx.xxx:20880/hzwService?makerkey=redmaker");
        adaptiveRefillMaker.makeRefillLight(url);

        url = URL.valueOf("hzw://xxx.xxx.xxx.xxx:20880/hzwService?makername=bluemaker");
        adaptiveRefillMaker.makeRefillLight(url);


    }

    // dubbo动态生成自适应扩展代码，编译执行的源码细节全都在这
    @Test
    public void showAdaptiveCode() throws IOException, IllegalAccessException, InstantiationException {
        String code = new AdaptiveClassCodeGenerator(RefillMaker.class,null).generate();

        String baseDir = "src/main/java/com/hzw/learn/springboot/dubbo/AdaptiveExtension/dubboDemo/";

        try(
                FileWriter fileWriter1 = new FileWriter(baseDir + "RefillMaker$Adaptive.java");
        ){
            fileWriter1.write(code);
        }

        // 获取编译器
        org.apache.dubbo.common.compiler.Compiler compiler = ExtensionLoader.getExtensionLoader(org.apache.dubbo.common.compiler.Compiler.class).getAdaptiveExtension();

        // 通过编译器实现类，编译动态生成的代码，生成Class
        Class<?> compile = compiler.compile(code, this.getClass().getClassLoader());

        // 反射实例化自适应扩展类
        RefillMaker refillMaker = (RefillMaker)compile.newInstance();

        URL url = URL.valueOf("hzw://xxx.xxx.xxx.xxx:20880/hzwService?makername=bluemaker");
        refillMaker.makeRefillLight(url);

        url = URL.valueOf("hzw://xxx.xxx.xxx.xxx:20880/hzwService?makername=redmaker");
        refillMaker.makeRefillLight(url);
    }
}
