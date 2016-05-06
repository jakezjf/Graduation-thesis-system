package com.jf.common.session;

import java.io.Serializable;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: RcPlatform,Inc Copyright (c) 2013-7-31 下午4:17:57
 * <p>
 * Team:RcPlatform Beijing
 * <p>
 * 
 * @author rc.wys
 * @version 1.0.0
 */
public interface SessionProvider {

	/**
	 * @DESC 从session中获取属性值
	 * @param request
	 * @param name
	 * @return
	 */
	public Serializable getAttribute(HttpServletRequest request, String name);

	/**
	 * @DESC 获取sessionID
	 * @param request
	 * @param response
	 * @return
	 */
	public String getSessionId(HttpServletRequest request, HttpServletResponse response);

	/**
	 * @DESC 登离
	 * @param request
	 * @param response
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response);

	/**
	 * @DESC 设置用户实体到session中
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 */
	public void setAttribute(HttpServletRequest request, HttpServletResponse response, String name, Serializable value);

	/**
	 * @DESC 设置权限或者菜单列表到session中
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 */
	public void setAttribute(HttpServletRequest request, HttpServletResponse response, String name, Set<?> value);
}
