/**
 * 
 */
package com.jf.controller.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jf.model.JFTeacher;
import com.jf.service.JFTeacherService;

/**
 * @author JF
 *
 *@date 2016年3月31日 下午3:20:21
 *
 */
@Controller
@RequestMapping("/teacherlogin")
public class TLoginController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private JFTeacherService teacherService;
	
	@RequestMapping("login.do")
	public String login(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFTeacher teacher){
		session = request.getSession();
		teacher = teacherService.getTeacher((String) session.getAttribute("id"));
		if (teacher !=null) {
			model.addAttribute("teacher", teacher);
		}
		return "teachermanager/login/index";
	}
	
	
}
