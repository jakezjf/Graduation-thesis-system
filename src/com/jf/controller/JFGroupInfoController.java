/**
 * 
 */
package com.jf.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jf.common.page.Pagination;
import com.jf.model.JFGroupInfo;
import com.jf.service.JFGroupInfoService;
import com.jf.service.JFStudentService;
import com.jf.service.JFTeacherService;
import com.jf.util.Constants;

/**
 * @author JF
 *
 *@date 2016年4月3日 上午10:02:51
 *
 */
@Controller
@RequestMapping("/group")
public class JFGroupInfoController {
	
	@Autowired
	private JFGroupInfoService groupInfoService;
	
	@Autowired
	private JFStudentService studentService;
	
	@Autowired
	private JFTeacherService teacherService;
	
	@RequestMapping("pagelist.do")
	public String pageList(Integer pageNo,HttpServletRequest request, ModelMap model, HttpServletResponse response){
		Pagination pagination = this.groupInfoService.getPage(pageNo, Constants.PAGE_SIZE,null, null, null);
		model.addAttribute("pagination", pagination);
		@SuppressWarnings("unchecked")
		List<JFGroupInfo> groupInfos = (List<JFGroupInfo>) pagination.getList();
		model.addAttribute("groupInfos", groupInfos);
		return "group/list";
	}
	
	@RequestMapping("del.do")
	public String del(HttpServletRequest request, ModelMap model, HttpServletResponse response,Integer pageNo,JFGroupInfo groupInfo){
	    synchronized (this) {
			groupInfoService.del(groupInfo.getGroId());
			teacherService.updatedel(groupInfo.getGroId());
			studentService.updatedel(groupInfo.getGroId());
		}
		model.addAttribute("pageNo",pageNo);
		return "redirect:pagelist.do";
	}
	
	@RequestMapping("toadd.do")
	public String toadd(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		return "group/add";
	}
	
	@RequestMapping("add.do")
	public String add(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFGroupInfo groupInfo,String time){
		synchronized (this) {
			groupInfo.setGroTime(Timestamp.valueOf(time));
			groupInfoService.insert(groupInfo);
		}
		return "redirect:pagelist.do?pageNo=1";
	}
	
	@RequestMapping("toupdate.do")
	public String toupdate(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFGroupInfo groupInfo){
		groupInfo = groupInfoService.getGroupInfo(groupInfo);
		model.addAttribute("groupInfo", groupInfo);
		return "group/update";
	}
	
	@RequestMapping("update.do")
	public String update(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFGroupInfo groupInfo,String time){
		synchronized (this) {
			groupInfo.setGroTime(Timestamp.valueOf(time));
			groupInfoService.update(groupInfo);
		}
		return "redirect:pagelist.do?pageNo=1";
	}
	
	
	
	

}
