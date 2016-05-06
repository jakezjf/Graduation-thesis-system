/**
 * 
 */
package com.jf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author JF
 *
 *@date 2016年4月12日 下午2:02:05
 *
 */
@Controller
@RequestMapping("/printerNull")
public class JFPrinterNullController {
	
	@RequestMapping("groupScore.do")
	public String groupScore(HttpServletRequest request, HttpServletResponse response){
		return "printerNull/groupScore";
	}
	
	@RequestMapping("groupRecord.do")
	public String groupRecord(HttpServletRequest request, HttpServletResponse response){
		return "printerNull/groupRecord";
	}
	
	@RequestMapping("teacherScore.do")
	public String teacherScore(HttpServletRequest request, HttpServletResponse response){
		return "printerNull/teacherScore";
	}
	
	@RequestMapping("comment.do")
	public String comment(HttpServletRequest request, HttpServletResponse response){
		return "printerNull/comment";
	}
	
	
}
