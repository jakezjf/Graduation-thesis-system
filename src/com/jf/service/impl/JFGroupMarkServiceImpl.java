/**
 * 
 */
package com.jf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.JFGroupMarkMapper;
import com.jf.model.JFGroupMark;
import com.jf.service.JFGroupMarkService;

/**
 * @author JF
 *
 *@date 2016年4月13日 下午3:30:01
 *
 */
@Service
@Transactional
public class JFGroupMarkServiceImpl implements JFGroupMarkService {
	
	@Autowired
	private JFGroupMarkMapper groupMarkMapper;

	/* (non-Javadoc)
	 * @see com.jf.service.JFGroupMarkService#groupMarks(com.jf.model.JFGroupMark)
	 */
	@Override
	public List<JFGroupMark> groupMarks(JFGroupMark groupMark) {
		// TODO Auto-generated method stub
		return groupMarkMapper.getGroupMarks(groupMark);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFGroupMarkService#delGroupMark(com.jf.model.JFGroupMark)
	 */
	@Override
	public void delGroupMark(JFGroupMark groupMark) {
		// TODO Auto-generated method stub
		groupMarkMapper.delGroupMark(groupMark);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFGroupMarkService#insertGroupMark(com.jf.model.JFGroupMark)
	 */
	@Override
	public void insertGroupMark(JFGroupMark groupMark) {
		// TODO Auto-generated method stub
		groupMarkMapper.insertSelective(groupMark);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFGroupMarkService#update(com.jf.model.JFGroupMark)
	 */
	@Override
	public void update(JFGroupMark groupMark) {
		// TODO Auto-generated method stub
		groupMarkMapper.updateByPrimaryKeySelective(groupMark);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFGroupMarkService#getGroupMark(com.jf.model.JFGroupMark)
	 */
	@Override
	public Integer getGroupMark(JFGroupMark groupMark) {
		// TODO Auto-generated method stub
		return groupMarkMapper.getGroupMark(groupMark);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFGroupMarkService#groupMark(com.jf.model.JFGroupMark)
	 */
	@Override
	public JFGroupMark groupMark(JFGroupMark groupMark) {
		// TODO Auto-generated method stub
		return groupMarkMapper.groupMark(groupMark);
	}

}
