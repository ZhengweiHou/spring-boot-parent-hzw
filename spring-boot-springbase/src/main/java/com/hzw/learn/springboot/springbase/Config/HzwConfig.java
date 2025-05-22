package com.hzw.learn.springboot.springbase.Config;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @ClassName HzwConfig
 * @Description TODO
 * @Author houzw
 * @Date 2025/4/23
 **/
@Component
public class HzwConfig implements EnvironmentAware {
    Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        String[] actProf = environment.getActiveProfiles();

        for (String s : actProf) {
            System.out.println(s);
        }

        this.environment = environment;
    }

    public String getProperty(String key) {
        return environment.getProperty(key);
    }
}
