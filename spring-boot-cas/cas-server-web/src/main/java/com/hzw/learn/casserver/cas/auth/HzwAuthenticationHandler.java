package com.hzw.learn.casserver.cas.auth;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.BadCredentialsAuthenticationException;
import org.jasig.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.jasig.cas.authentication.principal.Credentials;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class HzwAuthenticationHandler extends AbstractPreAndPostProcessingAuthenticationHandler {

	protected boolean doAuthentication(Credentials credentials) throws AuthenticationException {
		log.info("==doAuthentication==>");
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		HzwCredential hzwCredential = (HzwCredential) credentials;
		log.info(">>>>登录信息：{}", hzwCredential.toString());
		String requestCaptcha = hzwCredential.getCaptcha();

		Object attribute = request.getSession().getAttribute("captcha");
		String realCaptcha = attribute == null ? null : attribute.toString().toLowerCase();
//		if (StringUtils.isBlank(requestCaptcha) || !requestCaptcha.toLowerCase().equalsIgnoreCase(realCaptcha)) {
//			throw new BadCredentialsAuthenticationException("验证码错误");
//		}
		
		String username = hzwCredential.getUsername();

		String exceptionStrs = 
				"warn|error|accountDisabled|mustChangePassword|accountLocked|badHours|badWorkstation|passwordExpired";
		if(exceptionStrs.contains(username)) {
			throw new HzwAuthenticationException(username, username, username);
		}

		return true;		// TODO 用户校验
	}

	public boolean supports(Credentials credentials) {
		// 判断当前认证是否支持
		return true;
	}
}
