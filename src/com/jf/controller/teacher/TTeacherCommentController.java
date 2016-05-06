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
import com.jf.service.JFCommentService;
import com.jf.service.JFSpeechService;
import com.jf.service.JFStudentService;
import com.jf.service.JFTeacherService;

/**
 * @author JF
 *
 *@date 2016年4月25日 下午3:51:00
 *
 */
@Controller
@RequestMapping("/teacherComment")
public class TTeacherCommentController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private JFTeacherService teacherService;
	
	@Autowired
	private JFStudentService studentService;
	
	@Autowired
	private JFSpeechService speechService;
	
	@Autowired
	private JFCommentService commentService;
	
	
	@RequestMapping("comment.do")
	public String comment(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		session = request.getSession();
		JFStudent student = new JFStudent();
		student.setTeaId((String)session.getAttribute("id"));
		List<JFStudent> students = studentService.getStudentList(student);
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getSpeechId()!=null && students.get(i).getSpeechId().equals("")==false) {
				students.get(i).setSpeechTitle(speechService.getSpeech(students.get(i).getSpeechId(), null, null, null, null).getSpeechTitle());
				JFComment comment = new JFComment();
				try {
					comment = commentService.getComment(students.get(i).getSpeechId());
					if (comment.getTeaCom()!=null && comment.getTeaCom().equals("")==false) {
						students.get(i).setTeaCom("1");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		model.addAttribute("students", students);
		return "teachermanager/teacherComment/list";
	}
	
	@RequestMapping("toUpdate.do")
	public String toUpdate(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFStudent student,JFComment comment){
		student = studentService.getStudent(student.getStuId());
		if (student.getSpeechId()!=null) {
			comment = commentService.getComment(student.getSpeechId());
			model.addAttribute("comment", comment);
		}
		model.addAttribute("student",student);
		return "teachermanager/teacherComment/update";
	}
	
	@RequestMapping("toInsert.do")
	public String toInsert(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFStudent student){
		model.addAttribute("student",student);
		return "teachermanager/teacherComment/insert";
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
		return "redirect:comment.do";
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
		return "redirect:comment.do";
	}
	

}
