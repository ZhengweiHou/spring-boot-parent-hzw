package com.hzw.learn.springboot.springbase.CreatBeanToIOC.b_ImportSelector;

import com.google.gson.Gson;
import com.hzw.learn.springboot.springbase.CreatBeanToIOC.z_BaseBean.W;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @ClassName BImportSelector
 * @Description TODO
 * @Author houzw
 * @Date 2022/11/14
 **/
public class BImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // importingClassMetadata 此处能获取到@Import引入该ImportSelector的类上的所有注解
        Map<String, Object> anAtt = importingClassMetadata.getAnnotationAttributes(W.class.getName());
        Object values = anAtt.get("value");
        System.out.println("注解解析出:"+new Gson().toJson(values));
        return (String[]) values;
    }
}
