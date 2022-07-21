package com.hzw.learn.springboot.cas.server.auth;


import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.RememberMeUsernamePasswordCredential;

import javax.annotation.Generated;
import javax.validation.constraints.Size;

/**
 * @ClassName HzwCredentials
 * @Description 定义自定义的认证信息类
 * @Author houzw
 * @Date 2022/7/21
 **/
public class HzwCredential extends RememberMeUsernamePasswordCredential {
    @Size(
            min = 5,
            max = 5,
            message = "required.checkcode"
    )
    private String checkcode;

    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

}
