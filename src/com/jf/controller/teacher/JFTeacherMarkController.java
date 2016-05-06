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

import com.jf.model.JFStudent;
import com.jf.model.JFTeacherMark;
import com.jf.service.JFSpeechService;
import com.jf.service.JFStudentService;
import com.jf.service.JFTeacherMarkService;

/**
 * @author JF
 *
 *@date 2016年4月22日 下午10:34:38
 *
 */
@Controller
@RequestMapping("/teacherMark")
public class JFTeacherMarkController {
	
	@Autowired
	private JFTeacherMarkService teacherMarkService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private JFStudentService studentService;
	
	@Autowired
	private JFSpeechService speechService;
	
	
	@RequestMapping("student.do")
	public String list(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		session = request.getSession();
		JFStudent student = new JFStudent();
		student.setTeaId((String)session.getAttribute("id"));
		List<JFStudent> students = studentService.getStudentList(student);
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getSpeechId()!=null && students.get(i).getSpeechId().equals("")==false) {
				students.get(i).setSpeechTitle(speechService.getSpeech(students.get(i).getSpeechId(), null, null, null, null).getSpeechTitle());
			}
			try {
				JFTeacherMark teacherMark = new JFTeacherMark();
				teacherMark.setStuId(students.get(i).getStuId());
				teacherMark.setTeaId(students.get(i).getTeaId());
				students.get(i).setScore(teacherMarkService.getMark(teacherMark));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		model.addAttribute("students", students);
		return "teachermanager/teacherMark/student";
	}
	
	@RequestMapping("toUpdate.do")
	public String toUpdate(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFTeacherMark teacherMark){
		session = request.getSession();
		JFStudent student = studentService.getStudent(teacherMark.getStuId());
		model.addAttribute("student", student);
		teacherMark.setTeaId((String)session.getAttribute("id"));
		teacherMark = teacherMarkService.getTeacherMark(teacherMark);
		if (teacherMark!=null) {
			model.addAttribute("teacherMark", teacherMark);
			return "teachermanager/teacherMark/teacherMark";
		}
		return "teachermanager/teacherMark/teacherMark1";
	}
	
	@RequestMapping("insert.do")
	public String insert(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFTeacherMark teacherMark){
		session = request.getSession();
		teacherMark.setTeaId((String)session.getAttribute("id"));
		teacherMarkService.insertTeacherMark(teacherMark);
		return "redirect:student.do";
	}
	
	@RequestMapping("update.do")
	public String update(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFTeacherMark teacherMark){
		session = request.getSession();
		teacherMark.setTeaId((String)session.getAttribute("id"));
		teacherMarkService.updateTeacherMarks(teacherMark);
		return "redirect:student.do";
	}
}
