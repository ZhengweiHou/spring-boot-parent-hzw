package com.hzw.learn.springboot.dubbo.Wrapper;

import com.hzw.learn.springboot.dubbo.serviceExportAndReferenceAndInvoke.api.Hi;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName WrapperCodeTest
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/21
 **/
public class WrapperCodeTest {

    @Test
    public void test() throws IOException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {

//        Class clazz = HiImpl.class;
//        Class clazz = RefillMaker.class;
//        Class clazz = String.class;
        Class clazz = Hi.class;


        String baseDir = "src/main/java/com/hzw/learn/springboot/dubbo/Wrapper/";
//        String fileName = baseDir + clazz.getSimpleName() + "$Wrapper.java";

        // 生成Wrapper示例文件（修改了源码进行测试的，源码没有该方法）
        WrapperHzw.getWrapperToFile(clazz,baseDir);




    }
}
