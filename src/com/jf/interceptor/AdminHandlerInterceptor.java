package com.jf.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class AdminHandlerInterceptor extends HandlerInterceptorAdapter {

	private String[] excludeUrls; // 允许的url

	private String returnUrl;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


		return true;
	}

	private String getLoginUrl(HttpServletRequest request) {
		StringBuilder buff = new StringBuilder();
		String ctx = request.getContextPath();
		if (!StringUtils.isBlank(ctx)) {
			buff.append(ctx);
		}
		return buff.append("/BSMLogin/login.action").toString();

	}

	public void setExcludeUrls(String[] excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String[] getExcludeUrls() {
		return this.excludeUrls;
	}

	public String getReturnUrl() {
		return this.returnUrl;
	}

	private boolean exclude(String uri) {
		if (this.excludeUrls != null) {
			for (String exc : this.excludeUrls) {
				if (exc.equals(uri)) {
					return true;
				}
			}
		}
		return false;
	}

}
