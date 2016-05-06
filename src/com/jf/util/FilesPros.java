package com.jf.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * added by damon.niu 2013-01-08
 * 读取 Properties 的工具类
 */
public class FilesPros {

	private static Properties Prop;

	public static String getProper(String key) {
		if (FilesPros.Prop == null) {
			FilesPros.proload();
		}
		return FilesPros.Prop.getProperty(key).trim();
	}

	public static String getRealPath(String relativePosition) {
		String str = FilesPros.class.getResource("/").getPath();
		try {
			/* 转码。比如路径中有“%20”会被替换成空格； */
			str = URLDecoder.decode(str, "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		System.out.println(relativePosition);
		String projectRealPath = str.substring(0, str.lastIndexOf("WEB-INF/classes")) + relativePosition;

		return projectRealPath;
	}

	public static void main(String[] args) {
		FilesPros.proload();
		System.out.println(FilesPros.Prop.get("IMAGE_BIG_SIZE"));
		System.out.println(FilesPros.Prop.get("IMAGE_SMA_SIZE"));
		System.out.println(FilesPros.Prop.get("IMAGE_MID_SIZE"));
		System.out.println(FilesPros.Prop.get("IMAGE_SIGN_UNDERLINE"));
		System.out.println(FilesPros.Prop.get("IMAGE_SUB_DIRECTORY_NAME"));
	}

	private static Properties proload() {
		FilesPros.Prop = new Properties();
		try {
			FileInputStream inputstr3 = new FileInputStream(FilesPros.getRealPath("/WEB-INF/classes/config.properties"));
			FilesPros.Prop.load(inputstr3);
			inputstr3.close();
		}
		catch (FileNotFoundException ex) {
			System.out.println("没有找到配置文件");
			ex.printStackTrace();
		}
		catch (IOException ex) {
			System.out.println("IO错误");
			ex.printStackTrace();
		}
		return FilesPros.Prop;
	}

	// 根据定时任务重新初始化 配置文件
	public void initProper() {
		FilesPros.proload();
	}

}
