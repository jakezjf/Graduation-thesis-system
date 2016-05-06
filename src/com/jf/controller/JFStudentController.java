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
import com.jf.model.JFClass;
import com.jf.model.JFGroupInfo;
import com.jf.model.JFStudent;
import com.jf.model.JFTeacher;
import com.jf.model.JFUser;
import com.jf.service.JFClassService;
import com.jf.service.JFGroupInfoService;
import com.jf.service.JFStudentService;
import com.jf.service.JFTeacherService;
import com.jf.service.JFUserService;
import com.jf.util.Constants;

/**
 * @author JF
 *
 *@date 2016年3月24日 上午11:00:16
 *
 */
@Controller
@RequestMapping("/student")
public class JFStudentController {

	@Autowired
	private JFStudentService studentService;
	
	@Autowired
	private JFUserService userService;
	
	@Autowired
	private JFTeacherService teacherService;
	
	@Autowired
	private JFGroupInfoService groupInfoService;
	
	@Autowired
	private JFClassService classService;
	
	@RequestMapping("pagelist.do")
	public String pageList(Integer pageNo,JFStudent student,HttpServletRequest request, ModelMap model, HttpServletResponse response,String stuIdNow,String stuNameNow,String teaIdNow,String groupIdNow,String speechIdNow,Integer classNoNow){
		model.addAttribute("pageNo",pageNo);
		if (classNoNow==null) {
			classNoNow = 0;
		}
		Pagination pagination = this.studentService.getPage(pageNo,Constants.PAGE_SIZE,stuIdNow,stuNameNow,teaIdNow,groupIdNow,speechIdNow,classNoNow);
		model.addAttribute("pagination", pagination);
		@SuppressWarnings("unchecked")
		List<JFStudent> students = (List<JFStudent>) pagination.getList();
		List<JFTeacher> teachers = teacherService.getList();
		for (int i = 0; i < students.size(); i++) {
			students.get(i).setTeaName(teacherService.getTeacherName(students.get(i).getTeaId()));
			if (students.get(i).getClassNo()!=0) {
				students.get(i).setClassName(classService.getClassName(students.get(i).getClassNo()));
			}
		}
		List<JFGroupInfo> groupInfos = groupInfoService.groupInfos();
		List<JFClass> classes  = classService.getList();
		model.addAttribute("classes",classes);
		model.addAttribute("groupInfos",groupInfos);
		model.addAttribute("students",students);
		model.addAttribute("teachers",teachers);
		model.addAttribute("stuIdNow",stuIdNow);
		model.addAttribute("stuNameNow",stuNameNow);
		model.addAttribute("classNoNow",classNoNow);
		model.addAttribute("teaIdNow",teaIdNow);
		model.addAttribute("groupIdNow",groupIdNow);
		model.addAttribute("speechIdNow",speechIdNow);
		String result = "";
		if (stuIdNow!=null && stuIdNow.equals("")==false) {
			result = result + "&&stuIdNow=" + stuIdNow;
		}
		if (stuNameNow!=null && stuNameNow.equals("")==false) {
			result = result + "&&stuNameNow=" + stuNameNow;
		}
		if (teaIdNow!=null && teaIdNow.equals("")==false) {
			result = result + "&&teaIdNow=" + teaIdNow;
		}
		if (groupIdNow!=null && groupIdNow.equals("")==false) {
			result = result + "&&groupIdNow=" + groupIdNow;
		}
		if (speechIdNow!=null && speechIdNow.equals("")==false) {
			result = result + "&&speechIdNow=" + speechIdNow;
		}
		if (classNoNow!=null) {
			result = result + "&&classNoNow=" + classNoNow;
		}
		model.addAttribute("params",result);
		return "student/list";
	}
	
	@RequestMapping("toadd.do")
	public String toadd(HttpServletRequest request, ModelMap model, HttpServletResponse response,Integer pageNo,String stuIdNow,String stuNameNow,String teaIdNow,String groupIdNow,String speechIdNow){
		return "student/add";
	}
	
	@RequestMapping("add.do")
	public String add(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		return "";
	}
	
	@RequestMapping("del.do")
	public String del(HttpServletRequest request, ModelMap model, HttpServletResponse response,Integer pageNo,JFStudent student,String stuIdNow,String stuNameNow,String teaIdNow,String groupIdNow,String speechIdNow,Integer classNoNow){
	    synchronized (student) {
	    	studentService.del(student.getStuId());
	    	userService.delUser(student.getStuId());
		}
	    model.addAttribute("pageNo",pageNo);
	    model.addAttribute("classNoNow",classNoNow);
		model.addAttribute("stuIdNow",stuIdNow);
		model.addAttribute("stuNameNow",stuNameNow);
		model.addAttribute("teaIdNow",teaIdNow);
		model.addAttribute("groupIdNow",groupIdNow);
		model.addAttribute("speechIdNow",speechIdNow);
	    return "redirect:pagelist.do";
	}
//	?pageNo=" + pageNo + "&&stuIdNow=" + stuIdNow + "&&stuNameNow=" + stuNameNow + "&&teaIdNow=" + teaIdNow + "&&groupIdNow=" + groupIdNow + "&&speechIdNow=" + speechIdNow ;
	@RequestMapping("toupdate.do")
	public String toupdate(HttpServletRequest request, ModelMap model, HttpServletResponse response,Integer pageNo,String stuId,String stuIdNow,String stuNameNow,String teaIdNow,String groupIdNow,String speechIdNow,Integer classNoNow){
		JFStudent student = studentService.getStudent(stuId);
		model.addAttribute("student", student);
		List<JFTeacher> teachers = teacherService.getList();
		model.addAttribute("teachers", teachers);
		List<JFGroupInfo> groupInfos = groupInfoService.groupInfos();
		List<JFClass> classes = classService.getList();
		model.addAttribute("groupInfos",groupInfos);
		model.addAttribute("pageNo",pageNo);
		model.addAttribute("classes",classes);
		model.addAttribute("stuIdNow",stuIdNow);
		model.addAttribute("stuNameNow",stuNameNow);
		model.addAttribute("teaIdNow",teaIdNow);
		model.addAttribute("groupIdNow",groupIdNow);
		model.addAttribute("speechIdNow",speechIdNow);
	    model.addAttribute("classNoNow",classNoNow);
		return "student/update";
	}
	
	@RequestMapping("update.do")
	public String update(HttpServletRequest request, ModelMap model, HttpServletResponse response,Integer pageNo,JFStudent student,String stuId,String stuIdNow,String stuNameNow,String teaIdNow,String groupIdNow,String speechIdNow,Integer classNoNow){
		synchronized (student) {
			studentService.update(student);
			JFUser user = new JFUser();
			user.setId(student.getStuId());
			user.setName(student.getStuName());
			userService.update(user);
		}
		//String params="";
  		String result = "?pageNo=" +pageNo;
		if (stuIdNow!=null && stuIdNow.equals("")==false) {
			result = result + "&&stuIdNow=" + stuIdNow;
		}
		if (stuNameNow!=null && stuNameNow.equals("")==false) {
			result = result + "&&stuNameNow=" + stuNameNow;
		}
		if (teaIdNow!=null && teaIdNow.equals("")==false) {
			result = result + "&&teaIdNow=" + teaIdNow;
		}
		if (groupIdNow!=null && groupIdNow.equals("")==false) {
			result = result + "&&groupIdNow=" + groupIdNow;
		}
		if (speechIdNow!=null && speechIdNow.equals("")==false) {
			result = result + "&&speechIdNow=" + speechIdNow;
		}
		if (classNoNow!=null) {
			result = result + "&&classNoNow=" + classNoNow;
		}
		//model.addAttribute("params", params);
		return "redirect:pagelist.do" + result;
	}
	
	
}
