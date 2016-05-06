/**
 * 
 */
package com.jf.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jf.model.JFTeacher;
import com.jf.service.JFStudentService;
import com.jf.service.JFTeacherService;

/**
 * @author JF
 *
 *@date 2016年4月3日 下午4:30:58
 *
 */
@Controller
@RequestMapping("/judgment")
public class JFJudgment {
	
	@Autowired
	private JFTeacherService teacherService;
	
	@Autowired
	private JFStudentService studentService;

	@RequestMapping("group.do")
	public void  group(HttpServletRequest request, ModelMap model, HttpServletResponse response,String teaId){
		JFTeacher teacher = teacherService.getTeacher(teaId);
		try {
			response.getWriter().print(teacher.getGroId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
