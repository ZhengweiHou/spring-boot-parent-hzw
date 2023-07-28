
package com.hzw.grpc.fram.server;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collection;
import java.util.List;
import java.util.Map;
/**
 * @ClassName AicGrpcServiceAnnotationDiscoverer
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/26
 **/
@Slf4j
public class AicGrpcServiceAnnotationDiscoverer implements ApplicationContextAware,AicGrpcServiceDiscoverer{

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Collection<AicGrpcServiceDefinition> findAicGrpcServiceDefinitions() {

        Map<String, Object> aicGrpcServiceMap = applicationContext.getBeansWithAnnotation(AicGrpcService.class);
        List<AicGrpcServiceDefinition> definitions = Lists.newArrayListWithCapacity(aicGrpcServiceMap.size());

        // TODO aic grpc service 是否添加过滤器等逻辑？
        aicGrpcServiceMap.forEach((beanName,obj) ->{
            AicGrpcService aicGrpcServiceAnnotation = applicationContext.findAnnotationOnBean(beanName, AicGrpcService.class);

            AicGrpcServiceDefinition def = new AicGrpcServiceDefinition(beanName, obj.getClass(), obj);
            definitions.add(def);
            log.debug("Found aic gRPC service: bean: "
                    + beanName + ", class: " + def.getBeanClazz());
        });

        return definitions;
    }

}
