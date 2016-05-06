package com.jf.vo;

import org.springframework.web.multipart.MultipartFile;

import com.fancy.cms.util.FilesPros;

/**
 * @author ray.wang
 * @date 2013-9-7 下午12:03:12
 * @DESC 文件上传，接收Form表单参数
 */
public class FileBean {

	// 接收Form 表单文件
	MultipartFile uploadFile;

	MultipartFile uploadFile1;

	// 新图片路径
	String newImgPath;

	// 原图片路径
	String oldImgPath;

	// 新图片名称
	String newImgName;

	public MultipartFile getUploadFile1() {
		return uploadFile1;
	}

	public void setUploadFile1(MultipartFile uploadFile1) {
		this.uploadFile1 = uploadFile1;
	}

	// 原图片名称，暂时未用到
	String oldImgName;

	// 临时图片路径变量
	String tmpImgPath;

	// 图片服务器URL路径变量
	String imgUrl;

	// 图片类型
	String imgType;

	// 主图片目录名称，相当于根目录：
	String imgSubDirName;

	// 图片命名分隔符
	String imgSign;

	// 图片尺寸，用于设置生成图片传入参数
	int imgSize;

	// 时间戳
	private long time;

	public FileBean() {

//		long picker  = System.currentTimeMillis() % Long.valueOf(FilesPros.getProper("IMAGE_PICKER"));
		// 动态生成目录名
//		this.setImgSubDirName(FilesPros.getProper("IMAGE_SUB_DIRECTORY_NAME") + picker);
		this.setImgSubDirName(FilesPros.getProper("IMAGE_SUB_DIRECTORY_NAME"));
		// 根据取模参数从配置文件中取得图片URL
//		this.setImgUrl(FilesPros.getProper("IMAGE_URL_" + 0));
		this.setImgUrl(FilesPros.getProper("IMAGE_URL"));

		// 初始化文件信息
		imgSign = FilesPros.getProper("IMAGE_SIGN_UNDERLINE");

	}

	public String getNewImgPath() {
		return newImgPath;
	}

	public void setNewImgPath(String newImgPath) {
		this.newImgPath = newImgPath;
	}

	public String getOldImgPath() {
		return oldImgPath;
	}

	public void setOldImgPath(String oldImgPath) {
		this.oldImgPath = oldImgPath;
	}

	public String getNewImgName() {
		return newImgName;
	}

	public void setNewImgName(String newImgName) {
		this.newImgName = newImgName;
	}

	public String getOldImgName() {
		return oldImgName;
	}

	public void setOldImgName(String oldImgName) {
		this.oldImgName = oldImgName;
	}

	public String getImgType() {
		return imgType = this.uploadFile.getOriginalFilename().substring(this.uploadFile.getOriginalFilename().lastIndexOf('.') + 1);
	}

	public int getImgSize() {
		return imgSize;
	}

	public void setImgSize(int imgSize) {
		this.imgSize = imgSize;
	}

	public String getTmpImgPath() {
		return tmpImgPath;
	}

	public void setTmpImgPath(String tmpImgPath) {
		this.tmpImgPath = tmpImgPath;
	}

	public String getImgSubDirName() {
		return imgSubDirName;
	}

	public void setImgSubDirName(String imgSubDirName) {
		this.imgSubDirName = imgSubDirName;
	}

	public String getImgSign() {
		return imgSign;
	}

	public void setImgSign(String imgSign) {
		this.imgSign = imgSign;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

}
