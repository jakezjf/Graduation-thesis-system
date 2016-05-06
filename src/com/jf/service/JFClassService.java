/**
 * 
 */
package com.jf.service;

import java.util.List;

import com.jf.model.JFClass;

/**
 * @author JF
 *
 *@date 2016年4月27日 下午1:08:40
 *
 */
public interface JFClassService {

	void del(int classNo);
	
	void update(JFClass jfClass);
	
	void insert(JFClass jfClass);
	
	JFClass getClass(JFClass jfClass);
	
	List<JFClass> getClasses(JFClass jfClass);
	
	String getClassName(int classNo);
	
	List<JFClass> getList();
}
