/**
 * 
 */
package com.jf.controller.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jf.model.JFStudent;
import com.jf.model.JFTeacher;
import com.jf.service.JFStudentService;
import com.jf.service.JFTeacherService;

/**
 * @author JF
 *
 *@date 2016年3月29日 下午4:44:37
 *
 */
@Controller
@RequestMapping("/stuadmin")
public class StudentController {
	
	@Autowired
	private JFStudentService studentService;
	
	@Autowired
	private JFTeacherService teacherService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("list.do")
	public String list(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		session = request.getSession();
		String stu_id  = (String) session.getAttribute("id");
		JFStudent student = studentService.getStudent(stu_id);
	    JFTeacher teacher = teacherService.getTeacher(student.getTeaId());
	    model.addAttribute("student", student);
	    model.addAttribute("teacher", teacher);
		return "";
	}
	

}
