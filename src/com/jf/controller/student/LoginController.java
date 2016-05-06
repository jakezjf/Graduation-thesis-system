/**
 * 
 */
package com.jf.controller.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jf.model.JFGroupInfo;
import com.jf.model.JFGroupMark;
import com.jf.model.JFPercent;
import com.jf.model.JFStudent;
import com.jf.model.JFTeacher;
import com.jf.model.JFTeacherMark;
import com.jf.model.JFUser;
import com.jf.service.JFGroupInfoService;
import com.jf.service.JFGroupMarkService;
import com.jf.service.JFPercentService;
import com.jf.service.JFStudentService;
import com.jf.service.JFTeacherMarkService;
import com.jf.service.JFTeacherService;
import com.jf.service.JFUserService;

/**
 * @author JF
 *
 *@date 2016年3月29日 下午2:58:43
 *
 */
@Controller
@RequestMapping("/studentlogin")
public class LoginController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private JFStudentService studentService;
	
	@Autowired
	private JFTeacherService teacherService;
	
	@Autowired
	private JFGroupInfoService groupInfoService;
	
	@Autowired
	private JFUserService userService;
	
	@Autowired
	private JFGroupMarkService groupMarkService;
	
	@Autowired
	private JFPercentService percentService;
	
	@Autowired
	private JFTeacherMarkService teacherMarkService;
	
	@RequestMapping("login.do")
	public String login(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		session = request.getSession();
		try {
			String a = (String) session.getAttribute("id");
			int type = (Integer)session.getAttribute("ty");
			if (type==1) {
				JFStudent student = studentService.getStudent(a);
				if (student==null) {
					return "login/login";
				}
				model.addAttribute("student", student);
				if (student.getTeaId()!=null && student.getTeaId().equals("")==false) {
					JFTeacher teacher = teacherService.getTeacher(student.getTeaId());
					model.addAttribute("teacher", teacher);
				}
				if (student.getGroupId()!=null && student.getGroupId().equals("")==false) {
					JFGroupInfo groupInfo = new JFGroupInfo();
					groupInfo.setGroId(student.getGroupId());
					groupInfo = groupInfoService.getGroupInfo(groupInfo);
					model.addAttribute("groupInfo", groupInfo);
					JFStudent student2 = new JFStudent();
					student2.setGroupId(student.getGroupId());
					List<JFStudent> students = studentService.getStudentList(student2);
					model.addAttribute("students", students);
				}
				//算分。。。苦逼的时刻到了
				JFPercent percent = new JFPercent();
				percent = percentService.getPercent();
				double groupScore = 0;
				double teacherScore = 0;
				double allScore =0;
				if (percent!=null) {
					try {
						JFGroupMark groupMark = new JFGroupMark();
						groupMark.setStuId(student.getStuId());
						List<JFGroupMark> groupMarks = groupMarkService.groupMarks(groupMark);
						if (groupMarks.size()>0) {
							for (int i = 0; i < groupMarks.size(); i++) {
								groupMarks.get(i).setScore(groupMarks.get(i).getGroA() + groupMarks.get(i).getGroB() + groupMarks.get(i).getGroC() + groupMarks.get(i).getGroD() + groupMarks.get(i).getGroE() + groupMarks.get(i).getGroF() + groupMarks.get(i).getGroG() );
							    groupScore = groupScore + groupMarks.get(i).getScore();
							}
							groupScore = groupScore/(groupMarks.size());
							model.addAttribute("groupScore", groupScore);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					//只假设有多个指导老师打分
					try {
						JFTeacherMark teacherMark = new JFTeacherMark();
						teacherMark.setStuId(student.getStuId());
						List<JFTeacherMark> teacherMarks = teacherMarkService.teacherMarks(teacherMark);
						if (teacherMarks.size()>0) {
							for (int i = 0; i < teacherMarks.size(); i++) {
								teacherMarks.get(i).setScore(teacherMarks.get(i).getTeaA() + teacherMarks.get(i).getTeaB() + teacherMarks.get(i).getTeaC() + teacherMarks.get(i).getTeaD() + teacherMarks.get(i).getTeaE() + teacherMarks.get(i).getTeaF() + teacherMarks.get(i).getTeaG() + teacherMarks.get(i).getTeaH());
								teacherScore = teacherScore + teacherMarks.get(i).getScore();
							}
							model.addAttribute("teacherScore", teacherScore);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (groupScore!=0 && teacherScore!=0) {
						allScore = groupScore * (1-percent.getAdminPercent()) + teacherScore*percent.getAdminPercent();
						model.addAttribute("allScore", allScore);
					}
					return "studentmanager/login/index";
				}
				return "studentmanager/login/index";
			}
		} catch (Exception e) {
		}
		return "login/login";
	}
	
	
	@RequestMapping("update.do")
	public String update(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFUser user){
		userService.update(user);
		return "redirect:login.do";
	}

	
	
	
}
