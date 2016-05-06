package com.jf.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Constants {

	/**
	 * PT应用ID
	 */
	public static final int APPID_PT = 1;

	public final static String AUTH_KEY = "auth_key";

	public final static String MOBILE_KEY = "mobile_key";

	/**
	 * 漂流瓶相关的redis key前缀
	 */
	// 禁图前缀
	public static final String BAN_PIC = "ban-pic-";

	public static final String DEFAULT_SALT = "rchklimit";

	/**
	 * HTTP GET请求
	 */
	public static final String GET = "GET";

	public final static String MENU_KEY = "menu_key";

	/** 
	 * 每页记录数
	 */
	public final static int PAGE_SIZE = 10;

	public final static String PERMISSION_MODEL = "perms_model";

	public final static String PERMS_KEY = "perms_key";

	// 图片扩展名
	public static final String PIC_EXTENSION = ".jpg";

	/**
	 * 图片禁止状态
	 */
	public static final int PIC_FORBID = 1;

	/**
	 * 图片正常状态
	 */
	public static final int PIC_NORMAL = 0;

	/**
	 * HTTP POST请求
	 */
	public static final String POST = "POST";

	public static final String RETURN_URL = "returnUrl";

	public final static String SUPER_KEY = "super_key";

	public final static String USER_INFO_SESSION = "userSessionInfo";

	/**
	 * UTF-8编码
	 */
	public static final String UTF8 = "UTF-8";

	// 压缩包扩展名
	public static final String ZIP_EXTENSION = ".zip";

	public static final Map<Integer, String> DEPTLEVEL_MAP = new LinkedHashMap<Integer, String>();
	static {
		DEPTLEVEL_MAP.put(1, "第一级");
		DEPTLEVEL_MAP.put(2, "第二级");
		DEPTLEVEL_MAP.put(3, "第三级");
		DEPTLEVEL_MAP.put(4, "第四级");
		DEPTLEVEL_MAP.put(5, "第五级");
		DEPTLEVEL_MAP.put(6, "第六级");
		DEPTLEVEL_MAP.put(7, "第七级");
		DEPTLEVEL_MAP.put(8, "第八级");
		DEPTLEVEL_MAP.put(9, "第九级");
		DEPTLEVEL_MAP.put(10, "第十级");
	}

	public static final Map<Integer, String> TYPELEVEL_MAP = new LinkedHashMap<Integer, String>();
	static {
		TYPELEVEL_MAP.put(1, "第一级");
		TYPELEVEL_MAP.put(2, "第二级");
		TYPELEVEL_MAP.put(3, "第三级");
		TYPELEVEL_MAP.put(4, "第四级");
		TYPELEVEL_MAP.put(5, "第五级");
		TYPELEVEL_MAP.put(6, "第六级");
		TYPELEVEL_MAP.put(7, "第七级");
		TYPELEVEL_MAP.put(8, "第八级");
		TYPELEVEL_MAP.put(9, "第九级");
		TYPELEVEL_MAP.put(10, "第十级");
	}

	/**
	 * 表单常量
	 */
	public final static String FORM_NAME_T_NoticeInstructions = "T_NoticeInstructions";

	public final static String FORM_NAME_T_Request = "T_Request";

	public final static String HTML_SUFFIX = ".html";

	public final static String FORM_RELATIVE_PATH = "../autoform/";

	/**
	 * 统计柱状图的颜色
	 */
	public final static String[] COLOR = { "#FF0F00", "#FF6600", "#FF9E01", "#FCD202", "#F8FF01", "#04D215", "#0D8ECF" };

}
