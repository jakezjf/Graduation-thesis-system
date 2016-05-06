/**
 * 
 */
package com.jf.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.page.Pagination;
import com.jf.dao.JFUserMapper;
import com.jf.model.JFUser;
import com.jf.service.JFUserService;
import com.jf.util.Constants;

/**
 * @author JF
 *
 *@date 2016年3月21日 上午11:28:29
 *
 */
@Service
@Transactional
public class JFUserServiceImpl implements JFUserService {
	
	protected static final Log log = LogFactory.getLog(JFUserServiceImpl.class);
	
	@Autowired
	private JFUserMapper userMapper;

	/* (non-Javadoc)
	 * @see com.jf.service.JFUserService#getList()
	 */
	@Override
	public List<JFUser> getList() {
		return userMapper.getList();
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFUserService#getPassword(java.lang.String)
	 */
	@Override
	public String getPassword(String id) {
		return userMapper.getPassword(id);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFUserService#update(java.lang.String, com.jf.model.JFUser)
	 */
	@Override
	public void update(JFUser user) {
		userMapper.updateByPrimaryKeySelective(user);;
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFUserService#delUser(java.lang.String)
	 */
	@Override
	public void delUser(String id) {
		userMapper.deleteByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFUserService#insert(com.jf.model.JFUser)
	 */
	@Override
	public void insert(JFUser user) {
		userMapper.insert(user);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFUserService#getUser(java.lang.String)
	 */
	@Override
	public JFUser getUser(String id) {
		return userMapper.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFUserService#getPage(int, int)
	 */
	@Override
	public Pagination getPage(int pageNo, int pageSize,String id,String name,Integer type) {
		int total = 0;
		Pagination pagination = null;
		List<JFUser> users = null;
		JFUserServiceImpl.log.debug("receive:pageNo=" + pageNo + " pageSize=" + pageSize);
		int begin = (pageNo - 1) * pageSize;
		int end = begin + pageSize;
		users = this.userMapper.getUsers(begin, end,id,name,type);
		total = this.userMapper.getCount(id,name,type);
		if (total/Constants.PAGE_SIZE  < pageNo && total%Constants.PAGE_SIZE==1 && pageNo >1){
			pageNo = pageNo - 1;
		}
		//解决最后一页跳转问题
		pagination = new Pagination(pageNo, pageSize, total, users);
		return pagination;
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFUserService#getType(java.lang.String)
	 */
	@Override
	public int getType(String id) {
		return userMapper.getType(id);
	}

}
