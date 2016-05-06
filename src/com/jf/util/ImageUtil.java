package com.jf.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;

import org.springframework.web.multipart.MultipartFile;

import com.jf.vo.FileBean;

/**
 * @author ray.wang
 * @date 2013-9-7 下午12:04:33
 * @DESC 文件上传工具类
 */
public class ImageUtil {

	// 文件类型前缀固定变量 例如：webRoot/img/20121212.jpg
	public final static String IMAGE_SIGN_POINT = ".";

	// 生成原始图片，相当于把流文件直接生成图片
	public static boolean createOldImages(String oldImgPath, MultipartFile file) {

		OutputStream out = null;
		InputStream in = null;

		try {
			in = file.getInputStream();
			out = new FileOutputStream(new File(oldImgPath));
			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = in.read(b)) != -1)
				out.write(b, 0, len);

			out.flush();
			if (in != null)
				in.close();
			if (out != null)
				out.close();

		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	// 成成缩略图片，找到原始图片路径进行复制缩放
	public static boolean createPreviewImages(FileBean bean) {

		// 图形转换类
		AffineTransform transform = new AffineTransform();

		try {
			// 原始图片路径
			File in = new File(bean.getOldImgPath());
			// 新图片路径
			File to = new File(bean.getNewImgPath());

			BufferedImage bis = ImageIO.read(in);

			int w = bis.getWidth();
			int h = bis.getHeight();
			int nh = (bean.getImgSize() * h) / w;
			double sx = (double) bean.getImgSize() / w;
			double sy = (double) nh / h;

			transform.setToScale(sx, sy);
			AffineTransformOp ato = new AffineTransformOp(transform, null);
			BufferedImage bid = new BufferedImage(bean.getImgSize(), nh, BufferedImage.TYPE_3BYTE_BGR);
			ato.filter(bis, bid);

			ImageIO.write(bid, bean.getImgType(), to);

		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 创建文件夹
	public static void createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		dir.mkdirs();
	}

	// 生成图片操作
	public static int imageCreate(String newImgPath, String oldImgPath, MultipartFile file) {

		// 返回成功失败状态码
		int status = 0;

		/* 生成文件及文件夹操作 */

		try {
			// 生成文件目录
			createDir(newImgPath);
		}
		catch (Exception e) {
			status = 2;
		}

		try {
			// 生成原始文件
			createOldImages(oldImgPath, file);
		}
		catch (Exception e) {
			status = 3;
		}

		return status;
	}

	// 生成图片操作
	// public static Map<String, Object> imageTaskCreate(ImageBean bean){
	//
	// // 异常捕捉
	// Map<String, Object> map = new HashMap<String, Object>();
	//
	// // 返回成功失败消息
	// String message = "成功";
	// // 返回成功失败状态码
	// int status = 10000;
	//
	// /* 生成文件及文件夹操作 */
	//
	// try {
	// // 生成文件目录
	// createDir(bean.getNewImgPath());
	// } catch (Exception e) {
	// status = 2;
	// message = "文件夹生成失败";
	// }
	//
	// try {
	// // 生成原始文件
	// createOldImages(bean);
	// } catch (Exception e) {
	// status = 3;
	// message = "原始文件生成失败";
	// }
	//
	// map.put("status", status);
	// map.put("message", message);
	//
	// return map;
	// }

	// public void createIMGPreview(ImageBean bean){
	//
	//
	// List<ImageBean> keys = new ArrayList<ImageBean>();
	//
	// // 根据要生成的图片尺寸动态设置TASK任务
	// for(int i=0;i<imgSizes.length;i++){
	// bean.setImgSize(imgSizes[i]);
	// bean.setNewImgPath(getPreFilePath(bean));
	//
	// ImageBean b = new ImageBean();
	// // BeanUtils.copyProperties(bean,b);
	// b.setOldImgPath(bean.getOldImgPath());
	// b.setNewImgPath(bean.getNewImgPath());
	// b.setImgSize(bean.getImgSize());
	// b.setImgType(bean.getImgType());
	//
	// keys.add(b);
	// }
	//
	// try {
	// executer.GetResult(keys, new Task());
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

	// 生成缩放图片文件存储路径，封装方法
	public String getPreFilePath(FileBean bean) {
		String tmpFilePath = bean.getTmpImgPath() + bean.getNewImgName() + bean.getImgSign() + bean.getImgSize() + IMAGE_SIGN_POINT
		        + bean.getImgType();
		// System.out.println(tmpFilePath);
		return formatPath(tmpFilePath);
	}

	/**
	 * added by damon.niu 2013-01-25 获得图片根目录地址
	 */
	public static String getImagePath() {
		String path = FilesPros.getProper("IMAGE_DIRECTORY");
		return path;
	}

	// 获得 WebRoot 下图片路径
	public static String getNewFilePath(String webRootPath, FileBean bean, String userId) throws ServletException {

		// 生成日期小时字符串
		String sDatePath = getTimesPath();
		// 暂时使用变量 需用 properties 文件获取
		String sFilePath = bean.getImgSubDirName();
		// 临时变量 用于拼装 WebRoot 下图片路径
		String newFilePath;
		if (userId != null) {
			Date date = new Date();
			SimpleDateFormat dateformat = new SimpleDateFormat("HHmmss");
			String sDate = dateformat.format(date);
			newFilePath = webRootPath + sFilePath + File.separator + sDatePath + userId + File.separator + sDate + File.separator;
		} else {
			newFilePath = webRootPath + sFilePath + File.separator + sDatePath;
		}

		return newFilePath;
	}

	// 生成原始图片文件存储路径，封装方法
	public static String getOldFilePath(FileBean bean, String userId) {
		// 原始文件路径 由于先要生成原始图片，再由原始图片生成缩略图 - 临时变量
		String tmpFilePath;
		if (userId != null && !"".equals(userId)) {
			tmpFilePath = bean.getNewImgPath() + bean.getNewImgName();
		} else {
			tmpFilePath = bean.getNewImgPath() + bean.getNewImgName() + IMAGE_SIGN_POINT + bean.getImgType();
		}
		return formatPath(tmpFilePath);
	}

	// 获得 WebRoot 下图片路径
	public static String getNewName(FileBean bean) {
		// 文件名称
		String fileName = bean.getUploadFile().getOriginalFilename();

		return fileName;
	}

	// 获得日期小时字符串，用于生成文件夹目录
	public static String getTimesPath() {

		// 获取当前时间
		Date date = new Date();
		// 获取当前小时 1-24 单位计量
		@SuppressWarnings("deprecation")
		String sHours = String.valueOf(date.getHours());
		// 格式化当前时间 示例：20130106
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		String sDate = dateformat.format(date);
		// 拼装日期时间 示例：20130106/15/
		String sPath = sDate + File.separator + sHours + File.separator;

		return sPath;
	}

	// 格式化文件路径为 反斜杠/ 用于数据库存储时不被专一
	public static String formatPath(String path) {
		path = path.replace("\\", "/");
		return path;
	}

	// 获得文件的URL访问路径
	public static String getImgURLPath(String path, FileBean bean) {

		String url = bean.getImgUrl();
		String dir = bean.getImgSubDirName();
		url = url + path.substring(path.indexOf(dir) + dir.length(), path.length());

		return url;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
