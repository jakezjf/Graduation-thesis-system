/**
 * 
 */
package com.jf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jf.model.JFPercent;
import com.jf.service.JFPercentService;

/**
 * @author JF
 *
 *@date 2016年3月24日 下午9:30:44
 *
 */
@Controller
@RequestMapping("/percent")
public class JFPercentController {

	@Autowired
	private JFPercentService percentService;
	
	@RequestMapping("list.do")
	public String list(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		JFPercent percent = percentService.getPercent();
		double percent1 = 100*percent.getAdminPercent();
		double percent2 = 100*(1-percent.getAdminPercent());
		model.addAttribute("percent1", percent1);
		model.addAttribute("percent2", percent2);
		return "percent/list";
	}
	
	@RequestMapping("toupdate.do")
	public String toupdate(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		return "percent/update";
	}
	
	@RequestMapping("update.do")
	public String update(HttpServletRequest request, ModelMap model, HttpServletResponse response,JFPercent percent){
		percentService.upPercent(percent);
		return "redirect:list.do";
	}
	 
}
