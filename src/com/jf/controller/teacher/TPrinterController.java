/**
 * 
 */
package com.jf.controller.teacher;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jf.model.JFComment;
import com.jf.model.JFGroupMark;
import com.jf.model.JFPercent;
import com.jf.model.JFStudent;
import com.jf.model.JFTeacher;
import com.jf.model.JFTeacherMark;
import com.jf.service.JFClassService;
import com.jf.service.JFCommentService;
import com.jf.service.JFGroupMarkService;
import com.jf.service.JFPercentService;
import com.jf.service.JFSpeechService;
import com.jf.service.JFStudentService;
import com.jf.service.JFTeacherMarkService;
import com.jf.service.JFTeacherService;

/**
 * @author JF
 *
 *@date 2016年4月26日 下午1:31:23
 *
 */
@Controller
@RequestMapping("/teacherPrinter")
public class TPrinterController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private JFStudentService studentService;
	
	@Autowired
	private JFSpeechService speechService;
	
	@Autowired
	private JFTeacherMarkService teacherMarkService;
	
	@Autowired
	private JFTeacherService teacherService;
	
	@Autowired
	private JFGroupMarkService groupMarkService;
	
	@Autowired
	private JFCommentService commentService;
	
	@Autowired
	private JFClassService classService;
	
	@Autowired
	private JFPercentService percentService;
	
	
	@RequestMapping("student.do")
	public String list(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		session = request.getSession();
		JFStudent student = new JFStudent();
//		student.setTeaId((String)session.getAttribute("id"));
		JFTeacher teacher = new JFTeacher();
		teacher = teacherService.getTeacher((String)session.getAttribute("id"));
		if (teacher.getGroId()!= null && teacher.getGroId().equals("")==false) {
			student.setGroupId(teacher.getGroId());
			List<JFStudent> students = studentService.getStudentList(student);
			for (int i = 0; i < students.size(); i++) {
				if (students.get(i).getSpeechId()!=null && students.get(i).getSpeechId().equals("")==false && speechService.getSpeech(students.get(i).getSpeechId(), null, null, null, null).getSpeechTitle()!=null&& speechService.getSpeech(students.get(i).getSpeechId(), null, null, null, null).getSpeechTitle().equals("")==false) {
					students.get(i).setSpeechTitle(speechService.getSpeech(students.get(i).getSpeechId(), null, null, null, null).getSpeechTitle());
				}
				if (students.get(i).getStuId()!=null) {
					JFGroupMark groupMark =new JFGroupMark();
					groupMark.setStuId(students.get(i).getStuId());
					groupMark.setTeaId(teacher.getTeaId());
					if (groupMarkService.getGroupMark(groupMark)!=null) {
						students.get(i).setScore(groupMarkService.getGroupMark(groupMark));
					}
				}
			}
			model.addAttribute("students", students);
		}
		return "teachermanager/printer/student";
	}
	
	@RequestMapping("printerGroup.do")
	public String printerGroup(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFGroupMark groupMark){
		session = request.getSession();
		groupMark.setTeaId((String)session.getAttribute("id"));
		groupMark = groupMarkService.groupMark(groupMark);
	    String teaName = teacherService.getTeacherName((String)session.getAttribute("id"));
	    if (teaName!=null) {
	    	model.addAttribute("teaName", teaName);
		}
		if (groupMark!=null) {
			JFStudent student = new JFStudent();
			student = studentService.getStudent(groupMark.getStuId());
			if (student.getClassNo()!=0) {
				student.setClassName(classService.getClassName(student.getClassNo()));
			}
			model.addAttribute("student", student);
			model.addAttribute("groupMark", groupMark);
			int score = groupMark.getGroA() + groupMark.getGroB() +groupMark.getGroC() +groupMark.getGroD() +groupMark.getGroE() +groupMark.getGroF() +groupMark.getGroG();
			model.addAttribute("score", score);	
		}
		return "teachermanager/printer/groupScore";
	}
	
	@RequestMapping("teacherScore.do")
	public String teacherScore(HttpServletRequest request, ModelMap model, HttpServletResponse response){
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
		return "teachermanager/printer/teacherMark";
	}
	
	@RequestMapping("printerTeacher.do")
	public String printerTeacher(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFTeacherMark teacherMark){
		session = request.getSession();
		String teaName = teacherService.getTeacherName((String)session.getAttribute("id"));
	    if (teaName!=null) {
	    	model.addAttribute("teaName", teaName);
		}
		teacherMark.setTeaId((String)session.getAttribute("id"));
		teacherMark = teacherMarkService.getTeacherMark(teacherMark);
		if (teacherMark!=null) {
			model.addAttribute("teacherMark", teacherMark);	
			JFStudent student = new JFStudent();
			student = studentService.getStudent(teacherMark.getStuId());
			if (student.getClassNo()!=0) {
				student.setClassName(classService.getClassName(student.getClassNo()));
			}
			model.addAttribute("student", student);
			int score = teacherMark.getTeaA() + teacherMark.getTeaB() + teacherMark.getTeaC() + teacherMark.getTeaD() + teacherMark.getTeaE() + teacherMark.getTeaF() + teacherMark.getTeaG() + teacherMark.getTeaH() ;
			model.addAttribute("score", score);	
		}
		return "teachermanager/printer/teacherScore";
	}
	
	@RequestMapping("listTeacherScore.do")
	public String listTeacherScore(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		session = request.getSession();
		String teaName = teacherService.getTeacherName((String)session.getAttribute("id"));
	    if (teaName!=null) {
	    	model.addAttribute("teaName", teaName);
		}
	    JFTeacherMark teacherMark = new JFTeacherMark();
	    teacherMark.setTeaId((String)session.getAttribute("id"));
	    List<JFTeacherMark> teacherMarks = teacherMarkService.teacherMarks(teacherMark);
	    for (int i = 0; i < teacherMarks.size(); i++) {
	    	JFStudent student = new JFStudent();
	    	student.setStuId(teacherMarks.get(i).getStuId());
	    	student = studentService.getStu(student);
	    	student.setScore(teacherMarks.get(i).getTeaA()+teacherMarks.get(i).getTeaB()+teacherMarks.get(i).getTeaC()+teacherMarks.get(i).getTeaD()+teacherMarks.get(i).getTeaE()+teacherMarks.get(i).getTeaF()+teacherMarks.get(i).getTeaG()+teacherMarks.get(i).getTeaH());
	    	student.setClassName(classService.getClassName(student.getClassNo()));
			teacherMarks.get(i).setStudent(student);
		}
	    model.addAttribute("teacherMarks", teacherMarks);
		return "teachermanager/printer/listTeacherScore";
	}
	
	@RequestMapping("listGroupScore.do")
	public String listGroScore(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		session = request.getSession();
		String teaName = teacherService.getTeacherName((String)session.getAttribute("id"));
	    if (teaName!=null) {
	    	model.addAttribute("teaName", teaName);
		}
	    JFGroupMark groupMark = new JFGroupMark();
	    groupMark.setTeaId((String)session.getAttribute("id"));
	    List<JFGroupMark> groupMarks = groupMarkService.groupMarks(groupMark);
	    for (int i = 0; i < groupMarks.size(); i++) {
	    	JFStudent student = new JFStudent();
	    	student.setStuId(groupMarks.get(i).getStuId());
	    	student = studentService.getStu(student);
	    	student.setScore(groupMarks.get(i).getGroA()+groupMarks.get(i).getGroB()+groupMarks.get(i).getGroC()+groupMarks.get(i).getGroD()+groupMarks.get(i).getGroE()+groupMarks.get(i).getGroF()+groupMarks.get(i).getGroG());
	    	student.setClassName(classService.getClassName(student.getClassNo()));
			groupMarks.get(i).setStudent(student);
		}
	    model.addAttribute("groupMarks", groupMarks);
		return "teachermanager/printer/listGroupScore";
	}
	
	@RequestMapping("comment.do")
	public String comment(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		session = request.getSession();
		JFStudent student = new JFStudent();
		JFTeacher teacher = new JFTeacher();
		teacher = teacherService.getTeacher((String)session.getAttribute("id"));
		if (teacher !=null && teacher.getGroId()!=null) {
			student.setGroupId(teacher.getGroId());
		}
		List<JFStudent> students = studentService.getStudentList(student);
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getSpeechId()!=null && students.get(i).getSpeechId().equals("")==false) {
				students.get(i).setSpeechTitle(speechService.getSpeech(students.get(i).getSpeechId(), null, null, null, null).getSpeechTitle());
				if (students.get(i).getClassNo()!=0) {
					students.get(i).setClassName(classService.getClassName(students.get(i).getClassNo()));
				}
				JFComment comment = new JFComment();
				try {
					comment = commentService.getComment(students.get(i).getSpeechId());
					if (comment.getTeaCom()!=null && comment.getTeaCom().equals("")==false) {
						students.get(i).setTeaCom("1");
					}
					if (comment.getGroCom()!=null && comment.getGroCom().equals("")==false) {
						students.get(i).setGroCom("1");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		model.addAttribute("students", students);
		return "teachermanager/printer/comment";
	}
	
	@RequestMapping("com.do")
	public String com(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFStudent student){
		student = studentService.getStu(student);
		JFPercent percent = new JFPercent();
		percent = percentService.getPercent();
		if (student.getSpeechId()!=null) {
			student.setSpeechTitle(speechService.getSpeech(student.getSpeechId(), null, null, null, null).getSpeechTitle());
			double teaScore =0;
			double groScore =0;
			double score =0;
			JFComment comment =new JFComment();
			comment = commentService.getComment(student.getSpeechId());
			if (comment!=null) {
				student.setGroCom(comment.getGroCom());
				student.setTeaCom(comment.getTeaCom());
			}
			JFGroupMark groupMark = new JFGroupMark();
			groupMark.setStuId(student.getStuId());
			List<JFGroupMark> groupMarks = groupMarkService.groupMarks(groupMark);
			if (groupMarks!=null) {
				for (int j = 0; j < groupMarks.size(); j++) {
					groScore = groScore + groupMarks.get(j).getGroA() + groupMarks.get(j).getGroB() + groupMarks.get(j).getGroC() + groupMarks.get(j).getGroD() + groupMarks.get(j).getGroE() + groupMarks.get(j).getGroF() + groupMarks.get(j).getGroG() ;
				}
				groScore = groScore/groupMarks.size();
			}
			JFTeacherMark teacherMark = new JFTeacherMark();
			teacherMark.setStuId(student.getStuId());
			teacherMark.setTeaId(student.getTeaId());
			teacherMark = teacherMarkService.getTeacherMark(teacherMark);
			if (teacherMark!=null) {
				teaScore = teacherMark.getTeaA() + teacherMark.getTeaB() + teacherMark.getTeaC() + teacherMark.getTeaD() + teacherMark.getTeaE() + teacherMark.getTeaF() + teacherMark.getTeaG() + teacherMark.getTeaH();
			}
			score = teaScore * percent.getAdminPercent() +groScore * (1-percent.getAdminPercent());
			student.setScore((int)score);
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR)-4;
			model.addAttribute("year",year);
			model.addAttribute("student",student);
			return "teachermanager/printer/com";
		}
		return "redirect:comment.do";
	}
	
	@RequestMapping("listCom.do")
	public String listCom(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		session = request.getSession();
		JFStudent student = new JFStudent();
		JFTeacher teacher = new JFTeacher();
		JFPercent percent = new JFPercent();
		percent = percentService.getPercent();
		teacher = teacherService.getTeacher((String)session.getAttribute("id"));
		if (teacher!=null && teacher.getGroId()!=null) {
			student.setGroupId(teacher.getGroId());
			List<JFStudent>  students = studentService.getStudentList(student);
			for (int i = 0; i < students.size(); i++) {
				double teaScore =0;
				double groScore =0;
				double score =0;
				if (students.get(i).getSpeechId()!=null) {
					students.get(i).setSpeechTitle(speechService.getSpeech(students.get(i).getSpeechId(), null, null, null, null).getSpeechTitle());
					JFComment comment = new JFComment();
					comment = commentService.getComment(students.get(i).getSpeechId());
					if (comment!=null) {
						students.get(i).setTeaCom(comment.getTeaCom());
						students.get(i).setGroCom(comment.getGroCom());
					}
				}
				if (students.get(i).getGroupId()!= null) {
					JFGroupMark groupMark = new JFGroupMark();
					groupMark.setStuId(students.get(i).getStuId());
					List<JFGroupMark> groupMarks = groupMarkService.groupMarks(groupMark);
					if (groupMarks!=null) {
						for (int j = 0; j < groupMarks.size(); j++) {
							groScore = groScore + groupMarks.get(j).getGroA() + groupMarks.get(j).getGroB() + groupMarks.get(j).getGroC() + groupMarks.get(j).getGroD() + groupMarks.get(j).getGroE() + groupMarks.get(j).getGroF() + groupMarks.get(j).getGroG() ;
						}
						groScore = groScore/groupMarks.size();
					}
				}
				if (students.get(i).getTeaId()!=null) {
					JFTeacherMark teacherMark = new JFTeacherMark();
					teacherMark.setStuId(students.get(i).getStuId());
					teacherMark.setTeaId(students.get(i).getTeaId());
					teacherMark = teacherMarkService.getTeacherMark(teacherMark);
					if (teacherMark!=null) {
						teaScore = teacherMark.getTeaA() + teacherMark.getTeaB() + teacherMark.getTeaC() + teacherMark.getTeaD() + teacherMark.getTeaE() + teacherMark.getTeaF() + teacherMark.getTeaG() + teacherMark.getTeaH();
					}
				}
				score = teaScore * percent.getAdminPercent() +groScore * (1-percent.getAdminPercent());
				students.get(i).setScore((int)score);
			}
			model.addAttribute("students", students);
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR)-4;
			model.addAttribute("year",year);
			return "teachermanager/printer/listCom";
		}
		return "redirect:comment.do";
	}
	
	//成绩统计表
	@RequestMapping("score.do")
	public String score(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFGroupMark groupMark,JFTeacherMark teacherMark){
		double teaScore =0;
		double groScore = 0;
		double score = 0;
		List<JFGroupMark> groupMarks = groupMarkService.groupMarks(groupMark);
		for (int i = 0; i < groupMarks.size(); i++) {
			groupMarks.get(i).setScore(groupMarks.get(i).getGroA() + groupMarks.get(i).getGroB() + groupMarks.get(i).getGroC() + groupMarks.get(i).getGroD() + groupMarks.get(i).getGroE() + groupMarks.get(i).getGroF() + groupMarks.get(i).getGroG());
			groupMarks.get(i).setTeaName(teacherService.getTeacherName(groupMarks.get(i).getTeaId()));
			groScore = groScore + groupMarks.get(i).getScore();
		}
		groScore = groScore/groupMarks.size();
		model.addAttribute("groupMarks", groupMarks);
		model.addAttribute("groScore", groScore);
		teacherMark = 	teacherMarkService.getTeacherMark(teacherMark);
		teaScore = teacherMark.getTeaA() + teacherMark.getTeaB() +teacherMark.getTeaC() +teacherMark.getTeaD() +teacherMark.getTeaE() +teacherMark.getTeaF() +teacherMark.getTeaG() +teacherMark.getTeaH();
		teacherMark.setTeaName(teacherService.getTeacherName(teacherMark.getTeaId()));
		model.addAttribute("teacherMark", teacherMark);
		JFPercent percent = new JFPercent();
		percent = percentService.getPercent();
		model.addAttribute("percent", percent.getAdminPercent());
		score = groScore * (1-percent.getAdminPercent()) + teaScore * percent.getAdminPercent();
		model.addAttribute("score", score);
		return "teachermanager/printer/score";
	}
	
	
	
	
	@RequestMapping()
	public String listScore(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		session = request.getSession();
		if (session.getAttribute("id")!=null ) {
			JFTeacher teacher = new JFTeacher();
			teacher = teacherService.getTeacher((String)session.getAttribute("id"));
			if (teacher!=null && teacher.getGroId()!=null && teacher.getGroId().equals("")==false) {
				JFStudent student = new JFStudent();
				student.setGroupId(teacher.getGroId());
				List<JFStudent> students = studentService.getStudentList(student);
				if (students.size()>0) {
					for (int i = 0; i < students.size(); i++) {
						if (students.get(i).getClassNo()!=0) {
							students.get(i).setClassName(classService.getClassName(students.get(i).getClassNo()));
						}
						JFGroupMark groupMark = new JFGroupMark();
						groupMark.setStuId("");
						List<JFGroupMark> groupMarks = groupMarkService.groupMarks(groupMark);
						
					}
					
				}
			}
		}
		return "redirect:toScore.do+pageNo=1";
	}
	
	
	
	
	/**
	@RequestMapping("comment.do")
	public String comment(HttpServletRequest request, ModelMap model, HttpServletResponse response){
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
				JFComment comment = new JFComment();
				try {
					comment = commentService.getComment(students.get(i).getSpeechId());
					if (comment.getTeaCom()!=null && comment.getTeaCom().equals("")==false) {
						students.get(i).setTeaCom("1");
					}
					if (comment.getGroCom()!=null && comment.getGroCom().equals("")==false) {
						students.get(i).setGroCom("1");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		model.addAttribute("students", students);
		return "teachermanager/printer/comment";
	}
	
	@RequestMapping("com.do")
	public String com(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFStudent student){
		student = studentService.getStu(student);
		if (student.getSpeechId()!=null) {
			JFComment comment = new JFComment();
			comment = commentService.getComment(student.getSpeechId());
			model.addAttribute("comment", comment);
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR)-4;
			model.addAttribute("year",year);
			return "teachermanager/printer/com";
		}
		return "redirect:comment.do";
	}
	
	
	
	
	**/

}
