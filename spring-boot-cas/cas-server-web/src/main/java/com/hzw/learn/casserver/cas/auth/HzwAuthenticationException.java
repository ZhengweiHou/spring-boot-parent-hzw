package com.hzw.learn.casserver.cas.auth;

import org.jasig.cas.authentication.handler.AuthenticationException;

public class HzwAuthenticationException extends AuthenticationException {

	public HzwAuthenticationException(final String code, final String msg, final String type) {
		super(code,msg,type);
	}

}
