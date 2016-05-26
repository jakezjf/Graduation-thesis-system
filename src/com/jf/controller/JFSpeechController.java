/**
 * 
 */
package com.jf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jf.model.JFSpeech;


/**
 * @author JF
 *
 *@date 2016年4月5日 下午5:36:54
 *
 */
@Controller
@RequestMapping("/speech")
public class JFSpeechController {
	
	//列出论文
	@RequestMapping("pagelist.do")
	public String pageList(JFSpeech speech,HttpServletRequest request, ModelMap model, HttpServletResponse response){
                List<JFSpeech> speechs = speechService.getSpeech();
		
		return "";
	}

}
