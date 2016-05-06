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

import com.jf.model.JFGroupInfo;
import com.jf.model.JFStudent;
import com.jf.model.JFTeacher;
import com.jf.model.JFUser;
import com.jf.service.JFClassService;
import com.jf.service.JFGroupInfoService;
import com.jf.service.JFSpeechService;
import com.jf.service.JFStudentService;
import com.jf.service.JFTeacherService;
import com.jf.service.JFUserService;

/**
 * @author JF
 *
 *@date 2016年4月5日 下午1:40:34
 *
 */
@Controller
@RequestMapping("/teacherInfo")
public class JFTeacherInfoController {
	
	@Autowired
	private JFTeacherService teacherService;
	
	@Autowired
	private JFStudentService studentService;
	
	@Autowired
	private JFUserService userService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private JFSpeechService speechService;
	
	@Autowired
	private JFGroupInfoService groupInfoService;
	
	@Autowired
	private JFClassService classService;
	
	@RequestMapping("pagelist.do")
	public String pageList(JFTeacher teacher,HttpServletRequest request, ModelMap model, HttpServletResponse response){
		session = request.getSession();
        teacher.setTeaId((String)session.getAttribute("id"));
        teacher = teacherService.getTeacher(teacher.getTeaId());
        model.addAttribute("teacher", teacher);
		return "teachermanager/teacher/list";
	}

	@RequestMapping("toupdate.do")
	public String toupdate(JFTeacher teacher,HttpServletRequest request, ModelMap model, HttpServletResponse response){
		teacher = teacherService.getTeacher(teacher.getTeaId());
		List<JFGroupInfo> groupInfos = groupInfoService.groupInfos();
		JFUser user = userService.getUser(teacher.getTeaId());
		model.addAttribute("groupInfos",groupInfos);
		model.addAttribute("teacher", teacher);
		model.addAttribute("user", user);
		return "teachermanager/teacher/update";
	}
	

	@RequestMapping("update.do")
	public String update(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFTeacher teacher,String password){
		synchronized (teacher) {
			teacherService.update(teacher);
			JFUser user = new JFUser();
			user.setId(teacher.getTeaId());
			user.setName(teacher.getTeaName());
			user.setPassword(password);
			userService.update(user);
		}
		return "redirect:pagelist.do";
	}
	
	@RequestMapping("student.do")
	public String student(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		session = request.getSession();
		JFStudent student = new JFStudent();
		student.setTeaId((String)session.getAttribute("id"));
		List<JFStudent> students = studentService.getStudentList(student);
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getSpeechId()!=null && students.get(i).getSpeechId().equals("")==false) {
				students.get(i).setSpeechTitle(speechService.getSpeech(students.get(i).getSpeechId(), null, null, null, null).getSpeechTitle());
				if (students.get(i).getClassNo()!=0) {
					students.get(i).setClassName(classService.getClassName(students.get(i).getClassNo()));
				}
			}
		}
		model.addAttribute("students", students);
		return "teachermanager/teacher/student";
	}
	
	
	
	
	
}
