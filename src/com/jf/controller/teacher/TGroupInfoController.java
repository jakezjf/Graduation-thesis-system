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
import com.jf.model.JFGroupMark;
import com.jf.model.JFStudent;
import com.jf.model.JFTeacher;
import com.jf.service.JFClassService;
import com.jf.service.JFCommentService;
import com.jf.service.JFGroupInfoService;
import com.jf.service.JFGroupMarkService;
import com.jf.service.JFSpeechService;
import com.jf.service.JFStudentService;
import com.jf.service.JFTeacherService;
import com.jf.service.JFUserService;

/**
 * @author JF
 *
 *@date 2016年4月5日 下午4:31:50
 *
 */
@Controller
@RequestMapping("/teachergroup")
public class TGroupInfoController {
	
	@Autowired
	private JFTeacherService teacherService;
	
	@Autowired
	private JFStudentService studentService;
	
	@Autowired
	private JFUserService userService;
	
	@Autowired
	private JFSpeechService speechService;
	
	@Autowired
	private JFGroupMarkService groupMarkService;
	
	@Autowired
	private JFCommentService commentService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private JFGroupInfoService groupInfoService;
	
	@Autowired
	private JFClassService classService;
	
	@RequestMapping("pagelist.do")
	public String pageList(JFTeacher teacher,HttpServletRequest request, ModelMap model, HttpServletResponse response){
		session = request.getSession();
		teacher.setTeaId((String)session.getAttribute("id"));
        teacher = teacherService.getTeacher(teacher.getTeaId());
        try {
        	int type = teacher.getGroType();
        	 model.addAttribute("type", type);
		} catch (Exception e) {
		}
        if (teacher.getGroId()==null) {
        	return "teachermanager/group/list";
		}
        model.addAttribute("groupId", teacher.getGroId());
        List<JFTeacher> teachers = teacherService.getTeacherList(teacher.getGroId());
        model.addAttribute("teachers", teachers);
		return "teachermanager/group/list";
	}
	
	@RequestMapping("studentPagelist.do")
	public String studentPageList(JFTeacher teacher,HttpServletRequest request, ModelMap model, HttpServletResponse response){
		session = request.getSession();
		teacher.setTeaId((String)session.getAttribute("id"));
		teacher = teacherService.getTeacher(teacher.getTeaId());
		if (teacher.getGroId()!=null && teacher.getGroId().equals("")==false) {
			try {
	        	int type = teacher.getGroType();
	        	 model.addAttribute("type", type);
			} catch (Exception e) {
			}
			model.addAttribute("groupId", teacher.getGroId());
			JFStudent student = new JFStudent();
			student.setGroupId(teacher.getGroId());
			List<JFStudent> students = studentService.getStudentList(student);
			if (students!=null) {
				for (int i = 0; i < students.size(); i++) {
					if (students.get(i).getSpeechId()!=null && students.get(i).getSpeechId().equals("")==false) {
						if (speechService.getSpeech(students.get(i).getSpeechId(), null, null, null, null).getSpeechTitle()!=null && speechService.getSpeech(students.get(i).getSpeechId(), null, null, null, null).getSpeechTitle().equals("") ==false) {
							students.get(i).setSpeechTitle(speechService.getSpeech(students.get(i).getSpeechId(), null, null, null, null).getSpeechTitle());
						}
					}
					if (students.get(i).getClassNo()!=0) {
						students.get(i).setClassName(classService.getClassName(students.get(i).getClassNo()));
					}
				}
			}
			model.addAttribute("students", students);
		}
		return "teachermanager/group/studentList";
	}
	
	
	
	/**
	 * 实际上不是删除
	 * 是修改
	 * @return
	 */
	@RequestMapping("del.do")
	public String del(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFTeacher teacher){
		JFGroupMark groupMark = new JFGroupMark();
		groupMark.setTeaId(teacher.getTeaId()); 
		groupMarkService.delGroupMark(groupMark);
		teacher = teacherService.getTeacher(teacher.getTeaId());
	    teacher.setGroId(null);
		teacher.setGroType(null);
		teacherService.update(teacher);
		return "redirect:pagelist.do";
	}
	
	@RequestMapping("studentDel.do")
	public String studentDel(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFStudent student,JFComment comment,JFGroupMark groupMark){
			synchronized (this) {
				student = studentService.getStu(student);
				comment.setSpeechId(student.getSpeechId());
				//将答辩组的编号变为空
				student.setGroupId("");
				studentService.update(student);
				//将答辩组的评语设为空
				comment.setGroCom("");
				commentService.update(comment);
				//删除答辩组给的分数
				groupMarkService.delGroupMark(groupMark);
			}
		return "redirect:studentPagelist.do";
	}
	
	@RequestMapping("toMark.do")
	public String toMark(HttpServletRequest request, ModelMap model, HttpServletResponse response){
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
		return "teachermanager/mark/list";
	}
	
	@RequestMapping("toupdate.do")
	public String toupdate(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFGroupMark groupMark){
		session = request.getSession();
		JFStudent student = studentService.getStudent(groupMark.getStuId());
		model.addAttribute("student", student);
		groupMark.setTeaId((String)session.getAttribute("id"));
		groupMark = groupMarkService.groupMark(groupMark);
		if (groupMark!=null ) {
		model.addAttribute("groupMark", groupMark);
		return "teachermanager/mark/groupMark";
		}
		return "teachermanager/mark/groupMark1";
	}
	
	@RequestMapping("update.do")
	public String update(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFGroupMark groupMark){
		session = request.getSession();
		groupMark.setTeaId((String)session.getAttribute("id"));
		groupMarkService.update(groupMark);
		return "redirect:toMark.do";
	}
	
	@RequestMapping("insert.do")
	public String insert(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFGroupMark groupMark){
		session = request.getSession();
		groupMark.setTeaId((String)session.getAttribute("id"));
		groupMarkService.insertGroupMark(groupMark);
		return "redirect:toMark.do";
	}
	
	
	

	
	
	
}
