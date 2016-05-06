package com.jf.common.exception;

/**
 * @author ray.wang
 * @date 2013-9-26 下午10:33:15
 * @DESC 用户未找到
 */
@SuppressWarnings("serial")
public class UserNotFoundException extends AuthenticationException {
	public UserNotFoundException() {
	}

	public UserNotFoundException(String msg) {
		super(msg);
	}
}