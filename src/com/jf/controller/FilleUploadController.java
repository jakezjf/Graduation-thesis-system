package com.jf.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.model.JFUser;
import com.jf.service.JFUserService;
import com.jf.util.FilesPros;
import com.jf.util.ImageUtil;
import com.jf.vo.FileBean;


@Controller
@RequestMapping("/common")
public class FilleUploadController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private JFUserService userService;

	@RequestMapping(value = "/uploadImg.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadAttachment(HttpServletRequest request, HttpServletResponse response, FileBean bean, String num, String type) {
		int status = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			/* 文件基础信息 */
			// 获取webRoot路径
			String webRootPath = FilesPros.getProper("IMAGE_DIRECTORY");
			// 生成新文件名称
			bean.setNewImgName(ImageUtil.getNewName(bean));
			// String webRootPath = this.getWebContextPath(request);
			// 获得新文件存储路径
			bean.setNewImgPath(ImageUtil.getNewFilePath(webRootPath, bean,(String)session.getAttribute("id")));
			System.out.println(bean.getNewImgPath());
			// 临时变量用于存储文件存放路径
			bean.setTmpImgPath(bean.getNewImgPath());
			// 设置源文件存储路径
			bean.setOldImgPath(ImageUtil.getOldFilePath(bean,(String)session.getAttribute("id")));
			System.out.println("------------------------1" + bean.getNewImgPath());
			if (bean.getUploadFile() != null && bean.getUploadFile().getSize() != 0) {
				/* 生成文件及文件夹操作 */
				System.out.println("------------------------2" + bean.getNewImgPath());
				System.out.println("------------------------3" + bean.getOldImgPath());
				System.out.println("------------------------4" + bean.getUploadFile().getOriginalFilename());
				status = ImageUtil.imageCreate(bean.getNewImgPath(), bean.getOldImgPath(), bean.getUploadFile());
				String hou = bean.getOldImgPath();
				String houzui = hou.substring(hou.lastIndexOf(".")+1);
				System.out.println(houzui);
				if (status != 0) {
					map.put("success", false);
					map.put("attachmentPath", "");
					map.put("attachmentName", "");
				} else {
					map.put("success", true);
					map.put("attachmentPath", getAttachmentURLPath(bean));
					map.put("attachmentName", bean.getUploadFile().getOriginalFilename());
				}
			}
			map.put("serverpath", getImgURLPath(bean));
			map.put("error", "");
			map.put("msg", "上传成功!");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("error", "上传失败!");
		}
			String hou = bean.getOldImgPath();
			String houzui = hou.substring(hou.lastIndexOf(".")+1);
			if (houzui.equals("xls")) {
				  InputStream is = null;
					try {
						is = new FileInputStream(bean.getOldImgPath());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						map.put("error", "上传失败!");
					}
					HSSFWorkbook hssfWorkbook = null;
					try {
						hssfWorkbook = new HSSFWorkbook(is);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						map.put("error", "上传失败!");
					}
					HSSFSheet hssfSheet=hssfWorkbook.getSheetAt(0);
					for (int i = hssfSheet.getFirstRowNum()+1; i < hssfSheet.getLastRowNum(); i++) {
						HSSFRow hssfRow = hssfSheet.getRow(i);
						JFUser user = new JFUser();
						user.setId(hssfRow.getCell(0).getStringCellValue());
						user.setName(hssfRow.getCell(1).getStringCellValue());
						user.setPassword(hssfRow.getCell(2).getStringCellValue());
						user.setType((int)hssfRow.getCell(3).getNumericCellValue());
						userService.insert(user);
						System.out.println(user.toString());
					}
			}
		return map;
	}

	/**
	 * @DESC 获得文件的URL访问路径
	 * @param bean
	 * @return
	 */
	public String getImgURLPath(FileBean bean) {
		String dir = "jianfeng";
		System.out.println(bean.getOldImgPath());
		return bean.getOldImgPath().substring(bean.getOldImgPath().indexOf(dir) );
	}

	/**
	 * @DESC 获得文件的URL访问路径
	 * @param bean
	 * @return
	 */
	public String getAttachmentURLPath(FileBean bean) {
		String dir = bean.getImgSubDirName();
		String tmp = bean.getOldImgPath().substring(0, bean.getOldImgPath().lastIndexOf('.'));
		return tmp.substring(tmp.indexOf(dir));
	}

	public String getAttachmentServerPath(FileBean bean) {
		String url = bean.getImgUrl();
		String dir = bean.getImgSubDirName();
		url = url + bean.getOldImgPath().substring(bean.getOldImgPath().indexOf(dir), bean.getOldImgPath().length());
		return url;
	}
}