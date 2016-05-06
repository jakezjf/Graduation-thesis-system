/**
 * 
 */
package com.jf.service;

import java.sql.Time;
import java.util.List;

import com.jf.common.page.Pagination;
import com.jf.model.JFGroupInfo;

/**
 * @author JF
 *
 *@date 2016年4月2日 下午6:02:52
 *
 */
public interface JFGroupInfoService {
	
	List<JFGroupInfo> groupInfos();
	
	void del(String groId);
	
	void insert(JFGroupInfo groupInfo);
	
	void update(JFGroupInfo groupInfo);
	
	JFGroupInfo getGroupInfo(JFGroupInfo groupInfo);

	Pagination getPage(int pageNo,int pageSize,String groId,String groAddress,Time groTime);
			
}
