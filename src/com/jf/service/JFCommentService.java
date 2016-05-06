/**
 * 
 */
package com.jf.service;

import java.util.List;

import com.jf.common.page.Pagination;
import com.jf.model.JFComment;

/**
 * @author JF
 *
 *@date 2016年4月8日 下午12:58:06
 *
 */
public interface JFCommentService {

	List<JFComment> getList();
	
	void del(String speechId);
	
	void insert(JFComment comment);
	
	void update(JFComment comment);
	
	JFComment getComment(String speechId);
	
	Pagination getPage(int pageNo,int pageSize);
	
}
