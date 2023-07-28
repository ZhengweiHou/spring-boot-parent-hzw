
package com.hzw.grpc.fram.server;

/**
 * @ClassName AicGrpcServiceDefinition
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/26
 **/
public class AicGrpcServiceDefinition {

    private final String beanName;
    private final Class<?> beanClazz;
    //
    private final Object ref;


    public AicGrpcServiceDefinition(final String beanName, final Class<?> beanClazz,
                                 final Object ref) {
        this.beanName = beanName;
        this.beanClazz = beanClazz;
        this.ref = ref;
    }

    public String getBeanName() {
        return this.beanName;
    }


    public Class<?> getBeanClazz() {
        return this.beanClazz;
    }


    public Object getRef() {
        return this.ref;
    }

}
