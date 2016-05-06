package com.jf.common.exception;

/**
 * @author ray.wang
 * @date 2013-9-26 下午10:36:10
 * @DESC 登陆密码错误异常
 */
@SuppressWarnings("serial")
public class InvalidPasswordException extends AuthenticationException {
	public InvalidPasswordException() {
	}

	public InvalidPasswordException(String msg) {
		super(msg);
	}
}
