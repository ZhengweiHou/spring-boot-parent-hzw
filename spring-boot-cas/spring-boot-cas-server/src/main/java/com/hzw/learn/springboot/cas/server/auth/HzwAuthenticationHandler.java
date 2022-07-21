package com.hzw.learn.springboot.cas.server.auth;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletRequest;
import java.security.GeneralSecurityException;

/**
 * @ClassName HzwAuthenticationHandler
 * @Description TODO
 * @Author houzw
 * @Date 2022/4/2
 **/
public class HzwAuthenticationHandler extends AbstractPreAndPostProcessingAuthenticationHandler {
    Logger log = LoggerFactory.getLogger(this.getClass());

    public HzwAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    protected AuthenticationHandlerExecutionResult doAuthentication(Credential credential) throws GeneralSecurityException, PreventedException {
        log.info("==doAuthentication==>");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        HzwCredential hzwCredential = (HzwCredential)credential;
        log.info(">>>>Credential:{}",new Gson().toJson(hzwCredential));
        String requestCheckCode = hzwCredential.getCheckcode();

        Object attribute = request.getSession().getAttribute("checkcode");
        String realCheckcode = attribute == null ? null : attribute.toString().toLowerCase();
        if(StringUtils.isBlank(requestCheckCode) || !requestCheckCode.toLowerCase().equalsIgnoreCase(realCheckcode)){
            throw new FailedLoginException("验证码错误");
        }

        return createHandlerResult(credential,principalFactory.createPrincipal(hzwCredential.getUsername()));
    }

    public boolean preAuthenticate(Credential credential) {
        log.info("==preAuthenticate==>");
        return true;
    }

    public AuthenticationHandlerExecutionResult postAuthenticate(Credential credential, AuthenticationHandlerExecutionResult result) {
        log.info("==postAuthenticate==>");
        return result;
    }

    public boolean supports(Credential credential) {
        // 是否支持认证
        log.info("==supports2==>");
        return true;
    }
}
