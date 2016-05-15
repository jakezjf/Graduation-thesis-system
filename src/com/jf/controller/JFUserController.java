/**
 * 
 */
package com.jf.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jf.common.session.SessionProvider;
import com.jf.common.page.Pagination;
import com.jf.util.Constants;
import com.jf.model.JFSpeech;
import com.jf.model.JFStudent;
import com.jf.model.JFTeacher;
import com.jf.model.JFUser;
import com.jf.service.JFSpeechService;
import com.jf.service.JFStudentService;
import com.jf.service.JFTeacherService;
import com.jf.service.JFUserService;
import com.octo.captcha.service.image.ImageCaptchaService;


/**
 * @author JF
 *
 *@date 2016年3月20日 下午6:57:05
 *
 */
@Controller
@RequestMapping("/login")
public class JFUserController {
	
	@Autowired
	private JFUserService userService;
	
	@Autowired
	private JFStudentService studentService;
	
	@Autowired
	private JFTeacherService teacherService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private SessionProvider sessionProvider;
	
	@Autowired
	private ImageCaptchaService captchaService;
	
	@Autowired
	private JFSpeechService speechService;
	
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request, ModelMap model, HttpServletResponse response) {
		session = request.getSession();
		try {
			//判断用户的id，权限，跳转到相应类型的界面
			if (session.getAttribute("id") !=null && session.getAttribute("ty")!=null) {
				int ty = (Integer) session.getAttribute("ty");
				if (ty==1) {
					return "redirect:../studentlogin/login.do";
				}
				else if (ty==2) {
					return "redirect:../teacherlogin/login.do";
				}
				else if (ty==3) {
					//管理员只有一个表，user
					String a = (String) session.getAttribute("id");
					JFUser user =  userService.getUser(a);
					model.addAttribute("idd", user.getName());
					return "login/index";
				}
			}
			if(session.getAttribute("ty")!=null){
				int ty = (Integer) session.getAttribute("ty");
				if (session.getAttribute("id") !=null && ty==3 ) {
					String a = (String) session.getAttribute("id");
					JFUser user =  userService.getUser(a);
					model.addAttribute("idd", user.getName());
					return "login/index";
				}
			}
		} catch (Exception e) {
			return "login/login";
		}
		return "login/login";
		}
	
	
	@RequestMapping("/gogo.do")
	public String welcome(HttpServletRequest request, ModelMap model, HttpServletResponse response,String id,String password, String captcha) {
		session = request.getSession();
		//判断验证码是否为空
		try {
			if (captcha!=null) {
				if (!this.captchaService.validateResponseForID(this.sessionProvider.getSessionId(request, response), captcha)) {
					String error = "验证码错误";
					model.addAttribute("error", error);
					return "login/login";
				}
			}
		} catch (Exception e) {
			return "redirect:login.do";
		}
		//刷新时判断id，和权限（无账号密码）
		if (session.getAttribute("id") !=null && session.getAttribute("ty")!=null && id == null && password ==null) {
			int ty = (Integer) session.getAttribute("ty");
			if (ty==1) {
				return "redirect:../studentlogin/login.do";
			}
			else if (ty==2) {
				return "redirect:../teacherlogin/login.do";
			}
			else if (ty==3) {
				String a = (String) session.getAttribute("id");
				JFUser user =  userService.getUser(a);
				model.addAttribute("idd", user.getName());
				return "login/index";
			}
		}
		if (id != null && password !=null) {
			try {
				String nowPassword = userService.getPassword(id);
				int s = userService.getType(id);
				if(nowPassword.equals(password)){
						session.setAttribute("id", id);
						session.setAttribute("ty", s);
						if (s==1) {
							return "redirect:../studentlogin/login.do";
						}
						else if (s==2) {
							return "redirect:../teacherlogin/login.do";
						}
						else if (s==3) {
							String a = (String) session.getAttribute("id");
							JFUser user =  userService.getUser(a);
							model.addAttribute("idd", user.getName());
							return "login/index";
						}
					}
				} catch (Exception e) {
					String error = "用户名或密码错误";
					model.addAttribute("error", error);
					return "login/login";
				}
			}
			String error = "用户名或密码错误";
			model.addAttribute("error", error);
			return "login/login";
		}
	
	@RequestMapping("/toregister.do")
	public String toregister(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		return "login/register";
	}
	
	@RequestMapping("/register.do")
	public String register(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFUser user){
	    userService.insert(user);
		return "login/login";
	}
	
	@RequestMapping("/list.do")
	public String list(HttpServletRequest request, ModelMap model, HttpServletResponse response) {
		List<JFUser> users = userService.getList();
		model.addAttribute("users", users);
		return "login/list";
		}
	
	@RequestMapping("/del.do")
	public String del(HttpServletRequest request, ModelMap model, HttpServletResponse response,String id,Integer pageNo,Integer type){
		synchronized (id) {
			userService.delUser(id);
			teacherService.del(id);
			studentService.del(id);
		}
		if (type!=null) {
			return "redirect:pagelist.do?pageNo="+pageNo + "&&type="+type;
		}
		return "redirect:pagelist.do?pageNo="+pageNo;
	}
	
	@RequestMapping("/toupdate.do")
	public String toupdate(HttpServletRequest request, ModelMap model, HttpServletResponse response,String id,Integer pageNo,Integer type){
		JFUser user =userService.getUser(id);
		model.addAttribute("user", user);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("type",type);
		return "login/update";
	}
	
	@RequestMapping("/update.do")
	public String update(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFUser user,Integer pageNo,JFStudent student){
			if(user.getType()==1){
				synchronized (this) {
					student.setStuId(user.getId());
					student.setStuName(user.getPassword());
					studentService.update(student);
					userService.update(user);
				}
			}
			if(user.getType()==2){
				synchronized (this) {
				JFTeacher teacher = new JFTeacher();
				teacher.setTeaId(user.getId());
				teacher.setTeaName(user.getName());
				teacherService.update(teacher);
				userService.update(user);
			}
		}
		return "redirect:pagelist.do?pageNo=" + pageNo + "&&type=" + user.getType();
	}
	
	@RequestMapping("/toadd.do")
	public String toadd(HttpServletRequest request, ModelMap model, HttpServletResponse response,Integer type){
		model.addAttribute("type",type);
		return "login/add";
	}
	
	@RequestMapping("/add.do")
	public String inset(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFUser user,JFStudent student){
		try {
			userService.insert(user);
			if (user.getType()==1) {
				student.setStuId(user.getId());
				student.setStuName(user.getName());
				studentService.insert(student);
				if (student.getSpeechId()!=null && student.getSpeechId().equals("")==false && student.getSpeechTitle()!=null && student.getSpeechTitle().equals("")==false) {
					JFSpeech speech = new JFSpeech();
					speech.setSpeechId(student.getSpeechId());
					speech.setSpeechTitle(student.getSpeechTitle());
					speech.setStuId(student.getStuId());
					speechService.insert(speech);
				}
				return "redirect:../student/pagelist.do?pageNo=1";
			}
			if(user.getType()==2){
				JFTeacher teacher = new JFTeacher();
				teacher.setTeaId(user.getId());
				teacher.setTeaName(user.getName());
				teacherService.insert(teacher);
				return "redirect:../teacher/list.do?pageNo=1";
			}
		} catch (Exception e) {
			return "redirect:pagelist.do?pageNo=1";
		}
		List<JFUser> users = userService.getList();
		model.addAttribute("users", users);
		return "redirect:pagelist.do?pageNo=1";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		session.removeAttribute("id");
		session.removeAttribute("ty");
		return "login/login";
	}
	
	@RequestMapping("pagelist.do")
	public String pageList(Integer pageNo,String id,String name,Integer type,HttpServletRequest request, ModelMap model, HttpServletResponse response){
		Pagination pagination = this.userService.getPage(pageNo, Constants.PAGE_SIZE,id,name,type);
		model.addAttribute("pagination", pagination);
		@SuppressWarnings("unchecked")
		List<JFUser> users = (List<JFUser>) pagination.getList();
		model.addAttribute("users", users);
		model.addAttribute("name", name);
		model.addAttribute("type", type);
		return "login/list";
	}
	
	@RequestMapping("index.do")
	public String index(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		return "login/index1";
	}
	
}
