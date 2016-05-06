/**
 * 
 */
package com.jf.controller.teacher;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jf.model.JFComment;
import com.jf.model.JFStudent;
import com.jf.model.JFTeacher;
import com.jf.service.JFCommentService;
import com.jf.service.JFSpeechService;
import com.jf.service.JFStudentService;
import com.jf.service.JFTeacherService;

/**
 * @author JF
 *
 *@date 2016年4月8日 下午1:18:57
 *
 */
@Controller
@RequestMapping("/groComment")
public class TCommentController {
	
	@Autowired
	private JFCommentService commentService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private JFTeacherService teacherService;
	
	@Autowired
	private JFStudentService studentService;
	
	@Autowired
	private JFSpeechService speechService;
	
	@RequestMapping("list.do")
	public String list(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		session = request.getSession();
		JFTeacher teacher = new JFTeacher();
		teacher = teacherService.getTeacher((String)session.getAttribute("id"));
		if (teacher!=null) {
			JFStudent student = new JFStudent();
			if (teacher.getGroId()!=null && teacher.getGroId().equals("")==false) {
				student.setGroupId(teacher.getGroId());
				List<JFStudent> students = studentService.getStudentList(student);
				if (students!=null) {
					for (int i = 0; i < students.size(); i++) {
						if (students.get(i).getSpeechId()!=null && students.get(i).getSpeechId().equals("")==false) {
							students.get(i).setSpeechTitle(speechService.getSpeech(students.get(i).getSpeechId(), null, null, null, null).getSpeechTitle());
							JFComment comment = new JFComment();
							try {
								comment = commentService.getComment(students.get(i).getSpeechId());
								if (comment.getGroCom()!=null && comment.getGroCom().equals("")==false) {
									students.get(i).setGroCom("1");
								}
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
					}
				}
				model.addAttribute("students", students);
			}
		}
		return "teachermanager/comment/list";
	}
	
	@RequestMapping("teaPagelist.do")
	public String teaPagelist(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		List<JFComment> comments = commentService.getList();
		model.addAttribute("comments", comments);
		return "";
	}
	
	@RequestMapping("groPagelist.do")
	public String groPagelist(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		List<JFComment> comments = commentService.getList();
		model.addAttribute("comments", comments);
		return "";
	}
	
	@RequestMapping("toAdd.do")
	public String toAdd(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		return "";
	}
	
	@RequestMapping("add.do")
	public String add(JFComment comment,HttpServletRequest request, ModelMap model, HttpServletResponse response){
		commentService.insert(comment);
		return "redirect:pagelist.do";
	}
	
	@RequestMapping("del.do")
	public String del(String speechId,HttpServletRequest request, ModelMap model, HttpServletResponse response){
		commentService.del(speechId);
		return "redirect:pagelist.do";
	}
	
	@RequestMapping("toUpdate.do")
	public String toUpdate(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFStudent student,JFComment comment){
		student = studentService.getStudent(student.getStuId());
		if (student.getSpeechId()!=null) {
			comment = commentService.getComment(student.getSpeechId());
			model.addAttribute("comment", comment);
		}
		model.addAttribute("student",student);
		return "teachermanager/comment/update";
	}
	
	@RequestMapping("toInsert.do")
	public String toInsert(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFStudent student){
		model.addAttribute("student",student);
		return "teachermanager/comment/insert";
	}
	
	@RequestMapping("update.do")
	public String update(JFComment comment,HttpServletRequest request, ModelMap model, HttpServletResponse response,JFStudent student){
		student = studentService.getStu(student);
		if (student!=null && student.getSpeechId()!=null && student.getSpeechId().equals("")==false) {
			comment.setSpeechId(student.getSpeechId());
			try {
				commentService.update(comment);
			} catch (Exception e) {
				commentService.insert(comment);
			}
		}
		return "redirect:list.do";
	}
	
	@RequestMapping("insert.do")
	public String insert(JFComment comment,HttpServletRequest request, ModelMap model, HttpServletResponse response,JFStudent student){
		student = studentService.getStu(student);
		if (student!=null && student.getSpeechId()!=null && student.getSpeechId().equals("")==false) {
			comment.setSpeechId(student.getSpeechId());
			try {
				commentService.insert(comment);
			} catch (Exception e) {
				commentService.update(comment);
			}
			
		}
		return "redirect:list.do";
	}
	

}
