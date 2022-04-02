package com.hzw.learn.springboot.cas.server.auth;

import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        UsernamePasswordCredential userCredential = (UsernamePasswordCredential)credential;
        return createHandlerResult(credential,principalFactory.createPrincipal(userCredential.getUsername()));
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
