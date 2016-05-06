/**
 * 
 */
package com.jf.service;

import java.util.List;

import com.jf.common.page.Pagination;
import com.jf.model.JFUser;

/**
 * @author JF
 *
 *@date 2016年3月21日 上午11:23:06
 *
 */
public interface JFUserService {
	
	List<JFUser> getList();
	
	String getPassword(String id);
	
	void update(JFUser user);
	
	void delUser(String id);
	
	void insert(JFUser user);
	
	JFUser getUser(String id);
	
	Pagination getPage(int pageNo,int pageSize,String id,String name,Integer type);
	
	int getType(String id);

}
