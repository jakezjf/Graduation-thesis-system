/**
 * 
 */
package com.jf.service;

import java.util.List;

import com.jf.common.page.Pagination;
import com.jf.model.JFTeacher;

/**
 * @author JF
 *
 *@date 2016年3月25日 下午7:04:54
 *
 */
public interface JFTeacherService {

	List<JFTeacher> getList();
	
	JFTeacher getTeacher(String teaId);
	
	void del(String teaId);
	
	void update(JFTeacher teacher);
	
	void insert(JFTeacher teacher);
	
	String getTeaId(String teaName);
	
	Pagination getPage(int pageNo,int pageSize,String teaId,String teaName,String groId,Integer groType,Integer speechCount);
	
	String getTeacherName(String teaId);
	
	void updatedel(String groId);
	
	List<JFTeacher> getTeacherList(String groId);
	
	
}
