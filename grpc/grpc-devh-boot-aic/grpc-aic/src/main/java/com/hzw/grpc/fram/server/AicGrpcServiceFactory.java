package com.hzw.grpc.fram.server;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName AicGrpcServerFactory
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/26
 **/
public class AicGrpcServiceFactory {

    private Map<Class,AicGrpcServiceDefinition> aicGrpcServiceDefinition;
    private Map<Class,AicGrpcServiceDefinition> cache;

    public AicGrpcServiceFactory() {
        this.aicGrpcServiceDefinition = new ConcurrentHashMap<>();
        this.cache = new ConcurrentHashMap<>();
    }

    public void addServiceDefinition(AicGrpcServiceDefinition def){
        aicGrpcServiceDefinition.putIfAbsent(def.getBeanClazz(),def);
    }


    public AicGrpcServiceDefinition getAicGrpcDef(Class clazz){
        AicGrpcServiceDefinition cachedBean = cache.get(clazz);
        if (cachedBean != null) {
            return cachedBean;
        }

        AicGrpcServiceDefinition bean = aicGrpcServiceDefinition.get(clazz);

        if (bean == null) {
            for (Class cz : aicGrpcServiceDefinition.keySet()) {
                // TODO 是否处理可能存在多个的情况
                if(clazz.isAssignableFrom(cz)){
                    bean = aicGrpcServiceDefinition.get(cz);
                    break;
                }
            }
        }

        if (bean == null) {
            throw new RuntimeException("can not find aic grpc service by class:" + clazz.getName());
        }

        // 使用原子性操作 putIfAbsent
        cache.putIfAbsent(clazz, bean);

        return bean;
    }

    public void addServiceDefinition(Class clazz, AicGrpcServiceDefinition def) {
        aicGrpcServiceDefinition.putIfAbsent(clazz, def);
    }
}
