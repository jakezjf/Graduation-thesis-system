/**
 * 
 */
package com.jf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author JF
 *
 *@date 2016年4月9日 下午10:11:15
 *
 */
@Controller
@RequestMapping("/test")
public class JFTest {

	@RequestMapping("to.do")
	public String to(HttpServletRequest request, ModelMap model, HttpServletResponse response){
		
		return "comment/insert";
	}
	
	
}
