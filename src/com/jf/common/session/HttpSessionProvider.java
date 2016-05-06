package com.jf.common.session;

import java.io.Serializable;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: RcPlatform,Inc Copyright (c) 2013-7-31 下午4:21:47
 * <p>
 * Team:RcPlatform Beijing
 * <p>
 * 
 * @author rc.wys
 * @version 1.0.0
 */
@Service
public class HttpSessionProvider implements SessionProvider {

	public Serializable getAttribute(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return (Serializable) session.getAttribute(name);
		} else {
			return null;
		}
	}

	public String getSessionId(HttpServletRequest request, HttpServletResponse response) {
		return request.getSession().getId();
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}

	public void setAttribute(HttpServletRequest request, HttpServletResponse response, String name, Serializable value) {
		HttpSession session = request.getSession();
		session.setAttribute(name, value);
	}

	public void setAttribute(HttpServletRequest request, HttpServletResponse response, String name, Set<?> value) {
		HttpSession session = request.getSession();
		session.setAttribute(name, value);
	}
}
