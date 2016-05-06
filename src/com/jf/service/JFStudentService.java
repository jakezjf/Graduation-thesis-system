/**
 * 
 */
package com.jf.service;

import java.util.List;

import com.jf.common.page.Pagination;
import com.jf.model.JFStudent;

/**
 * @author JF
 *
 *@date 2016年3月24日 上午11:55:19
 *
 */
public interface JFStudentService {
	
	List<JFStudent> getList();
	
	JFStudent getStudent(String id);
	
	void update(JFStudent student);
	
	void del(String id);
	
	void insert(JFStudent student);
	
	Pagination getPage(int pageNo,int pageSize,String stuId,String stuName,String teaId,String groupId,String speechId,int classNo);
	
	void updatedel(String groId);
	
	List<JFStudent> getStudentList(JFStudent student);
	
	JFStudent getStu(JFStudent student);
}
