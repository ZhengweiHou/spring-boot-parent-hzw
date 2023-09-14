/*
 * Copyright (c) 2016-2021 Michael Zhang <yidongnan@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.devh.boot.grpc.server.advice;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * Condition to check if {@link GrpcAdvice @GrpcAdvice} is present. Mainly checking if {@link GrpcAdviceDiscoverer}
 * should be a instantiated.
 *
 * @author Andjelko Perisic (andjelko.perisic@gmail.com)
 * @see GrpcAdviceDiscoverer
 */
// 重写覆盖GrpcAdviceIsPresentCondition, 原逻辑在condition中遍历beanfactory，逻辑是存在问题的
//public class GrpcAdviceIsPresentCondition extends AllNestedConditions {
//
//    public GrpcAdviceIsPresentCondition() {
//        super(ConfigurationPhase.REGISTER_BEAN);
//    }
//
//    @ConditionalOnProperty(name = "grpc.server.advice.enable", havingValue = "true", matchIfMissing = false)
//    static class OnAdviceEnable {
//
//    }

//    @Override
//    public ConfigurationPhase getConfigurationPhase() {
//        return ConfigurationPhase.REGISTER_BEAN;
//    }
//
//    @Override
//    public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
//        final ConfigurableListableBeanFactory safeBeanFactory =
//                requireNonNull(context.getBeanFactory(), "ConfigurableListableBeanFactory is null");
//        return safeBeanFactory.getBeanNamesForAnnotation(GrpcAdvice.class).length != 0;
//    }

public class GrpcAdviceIsPresentCondition implements ConfigurationCondition {
    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.REGISTER_BEAN;
    }

    @Override
    public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
        final ConfigurableListableBeanFactory safeBeanFactory =
                requireNonNull(context.getBeanFactory(), "ConfigurableListableBeanFactory is null");
        String[] bdNames = safeBeanFactory.getBeanDefinitionNames();
        for (String name : bdNames) {
            BeanDefinition df = safeBeanFactory.getBeanDefinition(name);
            if (df instanceof AnnotatedBeanDefinition) {
                Set<String> annoNames = ((AnnotatedBeanDefinition) df).getMetadata().getAnnotationTypes();
                for (String annoName : annoNames) {
                    if (annoName.equals(GrpcAdvice.class.getName())) {
                        System.out.println("==========================true");
                        return true;
                    }
                }
            }
        }
//        safeBeanFactory.getBeanNamesForAnnotation(GrpcAdvice.class);
        System.out.println("==========================false");
        return false;

    }
}
