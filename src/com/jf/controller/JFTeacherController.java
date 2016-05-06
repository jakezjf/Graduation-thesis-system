/**
 * 
 */
package com.jf.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jf.common.page.Pagination;
import com.jf.model.JFGroupInfo;
import com.jf.model.JFTeacher;
import com.jf.model.JFUser;
import com.jf.service.JFGroupInfoService;
import com.jf.service.JFTeacherService;
import com.jf.service.JFUserService;
import com.jf.util.Constants;

/**
 * @author JF
 *
 *@date 2016年3月25日 下午6:55:38
 *
 */
@Controller
@RequestMapping("/teacher")
public class JFTeacherController {
	
	@Autowired
	private JFTeacherService teacherService;
	
	@Autowired
	private JFUserService userService;
	
	@Autowired
	private JFGroupInfoService groupInfoService;

	@RequestMapping("list.do")
	public String list(Integer pageNo,HttpServletRequest request, ModelMap model, HttpServletResponse response,String teaIdNow,String teaNameNow,String groIdNow,Integer groTypeNow,Integer speechCountNow){
		Pagination pagination = this.teacherService.getPage(pageNo, Constants.PAGE_SIZE, teaIdNow, teaNameNow, groIdNow, groTypeNow, speechCountNow);
		model.addAttribute("pagination", pagination);
		@SuppressWarnings("unchecked")
		List<JFTeacher> getTeachers = (List<JFTeacher>) pagination.getList();
		List<JFGroupInfo> groupInfos = groupInfoService.groupInfos();
		model.addAttribute("groupInfos",groupInfos);
		model.addAttribute("teachers", getTeachers);
		model.addAttribute("teaIdNow", teaIdNow);
		model.addAttribute("teaNameNow", teaNameNow);
		model.addAttribute("groIdNow", groIdNow);
		model.addAttribute("groTypeNow", groTypeNow);
		model.addAttribute("speechCountNow", speechCountNow);
		String result = "";
		if (teaIdNow!=null && teaIdNow.equals("")==false) {
			result = result + "&&teaIdNow=" + teaIdNow;
		}
		if (teaNameNow!=null && teaNameNow.equals("")==false) {
			result = result + "&&teaNameNow=" + teaNameNow;
		}
		if (groIdNow!=null && groIdNow.equals("")==false) {
			result = result + "&&groIdNow=" + groIdNow;
		}
		if (groTypeNow!=null && groTypeNow.equals("")==false) {
			result = result + "&&groTypeNow=" + groTypeNow;
		}
		if (speechCountNow!=null && speechCountNow.equals("")==false) {
			result = result + "&&speechCountNow=" + speechCountNow;
		}
		model.addAttribute("params",result);
		return "teacher/list";
	}
	
	@RequestMapping("toadd.do")
	public String toadd(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		return "teacher/add";
	}
	
	@RequestMapping("add.do")
	public String add(HttpServletRequest request, ModelMap model, HttpServletResponse response,String teaId){
		synchronized (teaId) {
			userService.delUser(teaId);
			teacherService.del(teaId);
		}
		return "redirect:list.do";
	}
	
	@RequestMapping("del.do")
	public String del(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFTeacher teacher,Integer pageNo,Integer groType,Integer speechCount,String groId,String teaIdNow,String teaNameNow,String groIdNow,Integer groTypeNow,Integer speechCountNow){
		synchronized (teacher) {
			teacherService.del(teacher.getTeaId());
			userService.delUser(teacher.getTeaId());
		}
		String result = "?pageNo=" +pageNo;
		if (teaIdNow!=null && teaIdNow.equals("")==false) {
			result = result + "&&teaIdNow=" + teaIdNow;
		}
		if (teaNameNow!=null && teaNameNow.equals("")==false) {
			result = result + "&&teaNameNow=" + teaNameNow;
		}
		if (groIdNow!=null && groIdNow.equals("")==false) {
			result = result + "&&groIdNow=" + groIdNow;
		}
		if (groTypeNow!=null && groTypeNow.equals("")==false) {
			result = result + "&&groTypeNow=" + groTypeNow;
		}
		if (speechCountNow!=null && speechCountNow.equals("")==false) {
			result = result + "&&speechCountNow=" + speechCountNow;
		}
		return "redirect:list.do" + result;
	}
	
	@RequestMapping("toupdate.do")
	public String toupdate(HttpServletRequest request, ModelMap model, HttpServletResponse response,String teaId,Integer pageNo,String teaIdNow,String teaNameNow,String groIdNow,Integer groTypeNow,Integer speechCountNow){
		JFTeacher teacher = teacherService.getTeacher(teaId);
		model.addAttribute("teacher", teacher);
		List<JFGroupInfo> groupInfos = groupInfoService.groupInfos();
		model.addAttribute("groupInfos",groupInfos);
		model.addAttribute("pageNo",pageNo);
		model.addAttribute("teaIdNow",teaIdNow);
		model.addAttribute("teaNameNow",teaNameNow);
		model.addAttribute("groIdNow",groIdNow);
		model.addAttribute("groTypeNow",groTypeNow);
		model.addAttribute("speechCountNow",speechCountNow);
		return "teacher/update";
	}
	
	@RequestMapping("update.do")
	public String update(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFTeacher teacher,Integer pageNo,String teaIdNow,String teaNameNow,String groIdNow,Integer groTypeNow,Integer speechCountNow){
		synchronized (teacher) {
			teacherService.update(teacher);
			JFUser user = new JFUser();
			user.setId(teacher.getTeaId());
			user.setName(teacher.getTeaName());
			userService.update(user);
		}
		String result = "?pageNo=" +pageNo;
		if (teaIdNow!=null && teaIdNow.equals("")==false) {
			result = result + "&&teaIdNow=" + teaIdNow;
		}
		if (teaNameNow!=null && teaNameNow.equals("")==false) {
			result = result + "&&teaNameNow=" + teaNameNow;
		}
		if (groIdNow!=null && groIdNow.equals("")==false) {
			result = result + "&&groIdNow=" + groIdNow;
		}
		if (groTypeNow!=null && groTypeNow.equals("")==false) {
			result = result + "&&groTypeNow=" + groTypeNow;
		}
		if (speechCountNow!=null && speechCountNow.equals("")==false) {
			result = result + "&&speechCountNow=" + speechCountNow;
		}
		return "redirect:list.do" + result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
