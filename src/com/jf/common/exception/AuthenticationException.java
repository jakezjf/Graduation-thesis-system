package com.jf.common.exception;

/**
 * @author ray.wang
 * @date 2013-9-26 下午10:34:50
 * @DESC 登陆认证异常
 */
@SuppressWarnings("serial")
public class AuthenticationException extends Exception {
	private Object extraInfo;

	public AuthenticationException() {

	}

	public AuthenticationException(String msg) {
		super(msg);
	}

	public AuthenticationException(String msg, Object extraInfo) {
		super(msg);
		this.extraInfo = extraInfo;
	}

	public Object getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(Object extraInfo) {
		this.extraInfo = extraInfo;
	}

}
