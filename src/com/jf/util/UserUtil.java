package com.jf.util;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.fancy.cms.vo.RoleMenuVo;

public class UserUtil {

	/**
	 * 用户菜单key
	 */
	public static final String MENU_KEY = "_menu_key";

	/**
	 * 用户权限key
	 */
	public static final String PERMS_KEY = "_perms_key";

	/**
	 * 是否超级管理员
	 */
	public static final String SUPER_KEY = "_super_key";

	/**
	 * 用户KEY
	 */
	public static final String USER_KEY = "_user_key";

	/**
	 * 获得用户菜单
	 * 
	 * @param request
	 * @return
	 */
	public static Set<RoleMenuVo> getMenu(HttpServletRequest request) {
		return (Set<RoleMenuVo>) request.getAttribute(UserUtil.MENU_KEY);
	}

	/**
	 * 获得用户权限
	 * 
	 * @param request
	 * @return
	 */
	public static Set<String> getPerms(HttpServletRequest request) {
		return (Set<String>) request.getAttribute(UserUtil.PERMS_KEY);
	}

	/**
	 * 获得是否为超级管理员
	 * 
	 * @param request
	 * @return
	 */
	public static String getSuper(HttpServletRequest request) {
		return (String) request.getAttribute(UserUtil.SUPER_KEY);
	}


}
