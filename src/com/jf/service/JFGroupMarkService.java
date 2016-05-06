/**
 * 
 */
package com.jf.service;

import java.util.List;

import com.jf.model.JFGroupMark;

/**
 * @author JF
 *
 *@date 2016年4月13日 下午3:24:25
 *
 */
public interface JFGroupMarkService {
	
	List<JFGroupMark> groupMarks(JFGroupMark groupMark);
	
	void delGroupMark(JFGroupMark groupMark);
	
	void insertGroupMark(JFGroupMark groupMark);
	
	void update(JFGroupMark groupMark);

	Integer getGroupMark(JFGroupMark groupMark);
	
	JFGroupMark groupMark(JFGroupMark groupMark);
	
}
