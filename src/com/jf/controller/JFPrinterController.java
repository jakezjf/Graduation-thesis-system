/**
 * 
 */
package com.jf.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jf.common.page.Pagination;
import com.jf.model.JFGroupInfo;
import com.jf.model.JFGroupMark;
import com.jf.model.JFPercent;
import com.jf.model.JFStudent;
import com.jf.model.JFTeacher;
import com.jf.model.JFTeacherMark;
import com.jf.service.JFGroupInfoService;
import com.jf.service.JFGroupMarkService;
import com.jf.service.JFPercentService;
import com.jf.service.JFSpeechService;
import com.jf.service.JFStudentService;
import com.jf.service.JFTeacherMarkService;
import com.jf.service.JFTeacherService;
import com.jf.util.Constants;

/**
 * @author JF
 *
 *@date 2016年4月12日 下午4:35:35
 *
 */
@Controller
@RequestMapping("/printer")
public class JFPrinterController {
	
	@Autowired
	private JFStudentService studentService;
	
	@Autowired
	private JFSpeechService speechService;
	
	@Autowired
	private JFTeacherService teacherService;
	
	@Autowired
	private JFPercentService percentService;
	
	@Autowired
	private JFTeacherMarkService teacherMarkService;
	
	@Autowired
	private JFGroupMarkService groupMarkService;
	
	@Autowired
	private JFGroupInfoService groupInfoService;

	@RequestMapping("studentScore.do")
	public String studentScore(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		List<JFStudent> students = studentService.getList();
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getSpeechId()!=null && students.get(i).getSpeechId().equals("")==false) {
				students.get(i).setSpeechTitle(speechService.getSpeech(students.get(i).getSpeechId(), null, null, null, null).getSpeechTitle());
			}
			if (students.get(i).getTeaId()!=null && students.get(i).getTeaId().equals("")==false) {
				students.get(i).setTeaName(teacherService.getTeacherName(students.get(i).getTeaId()));
			}
		}
		model.addAttribute("students", students);
		return "printer/list";
	}
	
	@RequestMapping("score.do")
	public String score(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		List<JFStudent> students = studentService.getList();
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getSpeechId()!=null && students.get(i).getSpeechId().equals("")==false) {
				students.get(i).setSpeechTitle(speechService.getSpeech(students.get(i).getSpeechId(), null, null, null, null).getSpeechTitle());
			}
			if (students.get(i).getTeaId()!=null && students.get(i).getTeaId().equals("")==false) {
				students.get(i).setTeaName(teacherService.getTeacherName(students.get(i).getTeaId()));
			}
		}
		model.addAttribute("students", students);
		return "printer/studentList";
	}
	
	/**
	 * 打印学生成绩表的
	 * @param request
	 * @param model
	 * @param response
	 * @param stuId
	 * @return
	 */
	@RequestMapping("personal.do")
	public String personal(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFTeacherMark teacherMark,JFGroupMark groupMark){
		JFPercent percent = percentService.getPercent();
		if (percent.getAdminPercent()!=null) {
			model.addAttribute("percent", percent);
		}
		if (teacherMark.getStuId()!=null && teacherMark.getStuId().equals("")==false) {
			JFStudent student = studentService.getStudent(teacherMark.getStuId());
			if (student.getTeaId()!=null && student.getTeaId().equals("")==false) {
				student.setTeaName(teacherService.getTeacherName(student.getTeaId()));
			}
			model.addAttribute("student", student);
		}
		List<JFTeacherMark> teacherMarks = teacherMarkService.teacherMarks(teacherMark);
		for (int i = 0; i < teacherMarks.size(); i++) {
			teacherMarks.get(i).setScore(teacherMarks.get(i).getTeaA()+teacherMarks.get(i).getTeaB() +teacherMarks.get(i).getTeaC() 
					+teacherMarks.get(i).getTeaD()+teacherMarks.get(i).getTeaE()+teacherMarks.get(i).getTeaF()+teacherMarks.get(i).getTeaG());
		}
		//指导成绩
		model.addAttribute("teacherMarks", teacherMarks);
		List<JFGroupMark> groupMarks = groupMarkService.groupMarks(groupMark);
		for (int i = 0; i < groupMarks.size(); i++) {
			if (groupMarks.get(i).getTeaId()!=null && groupMarks.get(i).getTeaId().equals("")==false) {
				groupMarks.get(i).setTeaName(teacherService.getTeacherName(groupMarks.get(i).getTeaId()));
			}
			groupMarks.get(i).setScore(groupMarks.get(i).getGroA() +groupMarks.get(i).getGroB() + groupMarks.get(i).getGroC() + groupMarks.get(i).getGroD() + groupMarks.get(i).getGroE() + groupMarks.get(i).getGroF() + groupMarks.get(i).getGroG() + groupMarks.get(i).getGroH());
		}
		//小组成绩
		model.addAttribute("count", groupMarks.size());
		model.addAttribute("groupMarks", groupMarks);
		return "printer/personalmark";
	}
	
	@RequestMapping("pagelist.do")
	public String pageList(Integer pageNo,JFStudent student,HttpServletRequest request, ModelMap model, HttpServletResponse response,String stuIdNow,String stuNameNow,String teaIdNow,String groupIdNow,String speechIdNow){
		model.addAttribute("pageNo",pageNo);
		Pagination pagination = this.studentService.getPage(pageNo,Constants.PAGE_SIZE,stuIdNow,stuNameNow,teaIdNow,groupIdNow,speechIdNow,0);
		model.addAttribute("pagination", pagination);
		@SuppressWarnings("unchecked")
		List<JFStudent> students = (List<JFStudent>) pagination.getList();
		List<JFTeacher> teachers = teacherService.getList();
		for (int i = 0; i < students.size(); i++) {
			students.get(i).setTeaName(teacherService.getTeacherName(students.get(i).getTeaId()));
		}
		List<JFGroupInfo> groupInfos = groupInfoService.groupInfos();
		model.addAttribute("groupInfos",groupInfos);
		model.addAttribute("students",students);
		model.addAttribute("teachers",teachers);
		model.addAttribute("stuIdNow",stuIdNow);
		model.addAttribute("stuNameNow",stuNameNow);
		model.addAttribute("teaIdNow",teaIdNow);
		model.addAttribute("groupIdNow",groupIdNow);
		model.addAttribute("speechIdNow",speechIdNow);
		return "printer/studentMark";
	}
	
	@RequestMapping("toFace.do")
	public String toFace(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		List<JFGroupInfo> groupInfos = groupInfoService.groupInfos();
		model.addAttribute("groupInfos", groupInfos);
		return "printer/toFace";
	}
	
	@RequestMapping("face.do")
	public String face(HttpServletRequest request, ModelMap model, HttpServletResponse response,String groId){
		JFStudent student = new JFStudent();
		student.setGroupId(groId);
		List<JFStudent> students = studentService.getStudentList(student);
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getSpeechId()!=null ) {
				students.get(i).setSpeechTitle(speechService.getSpeech(students.get(i).getSpeechId(), null, null, null, null).getSpeechTitle());
			}
			if (students.get(i).getTeaId()!=null && students.get(i).getTeaId().equals("")==false) {
				students.get(i).setTeaName(teacherService.getTeacherName(students.get(i).getTeaId()));
			}
		}
		model.addAttribute("students", students);
		Calendar  c=new  GregorianCalendar();//新建日期对象  搜索
		int  year=c.get(Calendar.YEAR);//获取年份  
		int  month=c.get(Calendar.MONTH);//获取月份  
		int  day=c.get(Calendar.DATE);//获取日期  
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		return "printer/face";
	}
	
	
}
