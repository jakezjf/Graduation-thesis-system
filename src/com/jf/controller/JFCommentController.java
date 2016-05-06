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
import com.jf.model.JFComment;
import com.jf.model.JFGroupInfo;
import com.jf.model.JFStudent;
import com.jf.model.JFTeacher;
import com.jf.service.JFClassService;
import com.jf.service.JFCommentService;
import com.jf.service.JFGroupInfoService;
import com.jf.service.JFStudentService;
import com.jf.service.JFTeacherService;
import com.jf.util.Constants;

/**
 * @author JF
 *
 *@date 2016年4月8日 下午1:18:57
 *
 */
@Controller
@RequestMapping("/comment")
public class JFCommentController {
	
	@Autowired
	private JFCommentService commentService;
	
	@Autowired
	private JFTeacherService teacherService;
	
	@Autowired
	private JFStudentService studentService;
	
	@Autowired
	private JFClassService classService;
	
	@Autowired
	private JFGroupInfoService groupInfoService;
	
	@RequestMapping("/page.do")
	public String pagelist(Integer pageNo,HttpServletRequest request, ModelMap model, HttpServletResponse response){
		Pagination pagination = this.commentService.getPage(pageNo, Constants.PAGE_SIZE);
		model.addAttribute("pagination", pagination);
		List<JFComment> comments = commentService.getList();
		for (int i = 0; i < comments.size(); i++) {
			JFStudent student = new JFStudent();
			student.setSpeechId(comments.get(i).getSpeechId());
			student = studentService.getStu(student);
			if (student.getStuName()!=null) {
				comments.get(i).setStuName(student.getStuName());
			}
			if (student.getGroupId()!=null) {
				comments.get(i).setGroId(student.getGroupId());
			}
			if (student.getTeaId()!=null) {
				comments.get(i).setTeaName(teacherService.getTeacherName(student.getTeaId()));
			}
		}
		model.addAttribute("comments", comments);
		return "comment/list";
	}
	
	@RequestMapping("/pagelist.do")
	public String page(HttpServletRequest request, ModelMap model, HttpServletResponse response,Integer pageNo,String stuIdNow,String stuNameNow,String teaIdNow,String groupIdNow,String speechIdNow,Integer classNoNow){
		model.addAttribute("pageNo",pageNo);
		if (classNoNow==null) {
			classNoNow = 0;
		}
		Pagination pagination = this.studentService.getPage(pageNo,Constants.PAGE_SIZE,stuIdNow,stuNameNow,teaIdNow,groupIdNow,speechIdNow,classNoNow);
		model.addAttribute("pagination", pagination);
		@SuppressWarnings("unchecked")
		List<JFStudent> students = (List<JFStudent>) pagination.getList();
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getClassNo()!=0) {
				students.get(i).setClassName(classService.getClassName(students.get(i).getClassNo()));
			}
			if (students.get(i).getTeaId()!=null && students.get(i).getTeaId().equals("")==false) {
				students.get(i).setTeaName(teacherService.getTeacherName(students.get(i).getTeaId()));
			}
			if (students.get(i).getSpeechId()!=null) {
				JFComment comment = new JFComment();
				try {
					comment = commentService.getComment(students.get(i).getSpeechId());
					if (comment.getGroCom()!=null && comment.getGroCom().equals("")==false) {
						students.get(i).setGroCom(comment.getGroCom());
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					if (comment.getTeaCom()!=null && comment.getTeaCom().equals("")==false) {
						students.get(i).setTeaCom(comment.getTeaCom());
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		List<JFTeacher> teachers = teacherService.getList();
		List<JFGroupInfo> groupInfos = groupInfoService.groupInfos();
		List<JFClass> classes  = classService.getList();
		model.addAttribute("classes",classes);
		model.addAttribute("groupInfos",groupInfos);
		model.addAttribute("students", students);
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
		return "comment/list";
	}
	
	@RequestMapping("/toadd.do")
	public String toadd(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		return "";
	}
	
	@RequestMapping("/add.do")
	public String add(JFComment comment,HttpServletRequest request, ModelMap model, HttpServletResponse response){
		commentService.insert(comment);
		return "redirect:pagelist.do";
	}
	
	@RequestMapping("/del.do")
	public String del(String speechId,HttpServletRequest request, ModelMap model, HttpServletResponse response){
		commentService.del(speechId);
		return "redirect:pagelist.do";
	}
	
	@RequestMapping("/toupdate.do")
	public String toupdate(String speechId,HttpServletRequest request, ModelMap model, HttpServletResponse response){
		JFComment comment = commentService.getComment(speechId);
		model.addAttribute("comment", comment);
		return "";
	}
	
	@RequestMapping("/update.do")
	public String update(JFComment comment,HttpServletRequest request, ModelMap model, HttpServletResponse response){
		commentService.update(comment);
		return "redirect:pagelist.do";
	}
	

}
