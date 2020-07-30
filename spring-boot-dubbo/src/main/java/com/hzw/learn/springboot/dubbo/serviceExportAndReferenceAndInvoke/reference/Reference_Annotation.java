package com.hzw.learn.springboot.dubbo.serviceExportAndReferenceAndInvoke.reference;

import org.apache.dubbo.config.AbstractInterfaceConfig;
import org.apache.dubbo.config.spring.ReferenceBean;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.cluster.LoadBalance;
import org.apache.dubbo.rpc.proxy.InvokerInvocationHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.AnnotationAttributes;

import java.lang.reflect.Method;
import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = AnnotationConfigApplicationContext.class)
//@EnableDubbo(scanBasePackages = "com.hzw.learn.springboot.dubbo.serviceExportAndReferenceAndInvoke.reference")
//@ComponentScan(value = {"com.hzw.learn.springboot.dubbo.serviceExportAndReferenceAndInvoke.reference"})
public class Reference_Annotation {

    //    @Autowired
    //    ReferenceHandlorBean referenceHandlorBean;

    @Test
    public void referenceByAnnotationTest(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();

        ReferenceHandlorBean referenceHandlorBean = context.getBean(ReferenceHandlorBean.class);
        referenceHandlorBean.hi.sayhi("12313");
    }

    @Configuration
    @EnableDubbo(scanBasePackages = "com.hzw.learn.springboot.dubbo.serviceExportAndReferenceAndInvoke.reference")
    @ComponentScan(value = {"com.hzw.learn.springboot.dubbo.serviceExportAndReferenceAndInvoke.reference"})
    @PropertySource("classpath:/serviceExportAndReferenceAndInvoke/dubbo-consumer.properties")
    static class ConsumerConfiguration{}


    @Test
    public void invokerTest1(){


        /**
         * 简单记录下调用过程    TODO 待调试
         * @see InvokerInvocationHandler#invoke(Object, Method, Object[])
         * @see org.apache.dubbo.rpc.cluster.support.wrapper.MockClusterInvoker#invoke(Invocation)
         * @see org.apache.dubbo.rpc.cluster.support.AbstractClusterInvoker#invoke(Invocation)
         * @see org.apache.dubbo.rpc.cluster.support.FailoverClusterInvoker#doInvoke(Invocation, List, LoadBalance)
         */

        /**
         * @Reference注解配置解析类    TODO 待调试 太混乱了
         * @see org.apache.dubbo.config.spring.beans.factory.annotation.AnnotationInjectedBeanPostProcessor#inject()
         * @see org.apache.dubbo.config.spring.beans.factory.annotation.AnnotationInjectedBeanPostProcessor#getInjectedObject(AnnotationAttributes, Object, String, Class, InjectionMetadata.InjectedElement)
         * @see org.apache.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor#doGetInjectedBean
         * @see org.apache.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor#buildReferenceBeanIfAbsent
         * @see org.apache.dubbo.config.spring.beans.factory.annotation.ReferenceBeanBuilder#build()
         *         extend by  {@link org.apache.dubbo.config.spring.beans.factory.annotation.AnnotatedInterfaceConfigBeanBuilder#build()}
         * @see org.apache.dubbo.config.spring.beans.factory.annotation.AnnotatedInterfaceConfigBeanBuilder#configureBean(AbstractInterfaceConfig)
         * @see org.apache.dubbo.config.spring.beans.factory.annotation.AnnotatedInterfaceConfigBeanBuilder#preConfigureBean(AnnotationAttributes, AbstractInterfaceConfig)
         *        Override by {@link org.apache.dubbo.config.spring.beans.factory.annotation.ReferenceBeanBuilder#preConfigureBean(AnnotationAttributes, ReferenceBean)}
         *
         * reference xml解析类
         * @see org.apache.dubbo.config.spring.schema.DubboNamespaceHandler
         */
    }
}
