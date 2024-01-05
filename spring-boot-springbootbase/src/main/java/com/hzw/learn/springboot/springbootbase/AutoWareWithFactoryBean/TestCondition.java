package com.hzw.learn.springboot.springbootbase.AutoWareWithFactoryBean;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;

import static java.util.Objects.requireNonNull;

public class TestCondition implements ConfigurationCondition {

    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.REGISTER_BEAN;
    }

    @Override
    public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
        final ConfigurableListableBeanFactory safeBeanFactory =
                requireNonNull(context.getBeanFactory(), "ConfigurableListableBeanFactory is null");
        return safeBeanFactory.getBeanNamesForAnnotation(Test1.class).length != 0;
    }

}
