package com.hzw.learn.casserver.cas.auth;

import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import javax.validation.constraints.Size;

/**
 * @ClassName HzwCredentials
 * @Description 定义自定义的认证信息类
 * @Author houzw
 * @Date 2022/7/21
 **/
public class HzwCredential extends UsernamePasswordCredentials {
    @Size(
            min = 2,
            max = 5,
            message = "required.captcha"
    )
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
    
    public String toString() {
        return "[username: " + this.getUsername() + ",captcha: " + this.getCaptcha() + "]";
    }

}
