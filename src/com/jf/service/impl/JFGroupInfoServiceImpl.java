/**
 * 
 */
package com.jf.service.impl;

import java.sql.Time;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.page.Pagination;
import com.jf.dao.JFGroupInfoMapper;
import com.jf.model.JFGroupInfo;
import com.jf.service.JFGroupInfoService;
import com.jf.util.Constants;

/**
 * @author JF
 *
 *@date 2016年4月2日 下午6:06:26
 *
 */
@Service
@Transactional
public class JFGroupInfoServiceImpl implements JFGroupInfoService{
	
	@Autowired
	private JFGroupInfoMapper groupInfoMapper;
	
	protected static final Log log = LogFactory.getLog(JFGroupInfoServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.jf.service.JFGroupInfoService#groupInfos()
	 */
	@Override
	public List<JFGroupInfo> groupInfos() {
		// TODO Auto-generated method stub
		return groupInfoMapper.getList();
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFGroupInfoService#del(com.jf.model.JFGroupInfo)
	 */
	@Override
	public void del(String groId) {
		groupInfoMapper.deleteByPrimaryKey(groId);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFGroupInfoService#insert(com.jf.model.JFGroupInfo)
	 */
	@Override
	public void insert(JFGroupInfo groupInfo) {
		groupInfoMapper.insertSelective(groupInfo);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFGroupInfoService#update(com.jf.model.JFGroupInfo)
	 */
	@Override
	public void update(JFGroupInfo groupInfo) {
		groupInfoMapper.update(groupInfo);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFGroupInfoService#getGroupInfo(com.jf.model.JFGroupInfo)
	 */
	@Override
	public JFGroupInfo getGroupInfo(JFGroupInfo groupInfo) {
		return groupInfoMapper.getGroupInfo(groupInfo);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFGroupInfoService#getPage(int, int, java.lang.String, java.lang.String, java.sql.Time)
	 */
	@Override
	public Pagination getPage(int pageNo, int pageSize, String groId,String groAddress, Time groTime) {
		int total = 0;
		Pagination pagination = null;
		List<JFGroupInfo> groupInfos = null;
		JFGroupInfoServiceImpl.log.debug("receive:pageNo=" + pageNo + " pageSize=" + pageSize);
		int begin = (pageNo - 1) * pageSize;
		int end = begin + pageSize;
		groupInfos = groupInfoMapper.getGroupInfos(begin,end,groId,groAddress, groTime);
		total = groupInfoMapper.getCount(groId, groAddress, groTime);
		 if (total/Constants.PAGE_SIZE  < pageNo && total%Constants.PAGE_SIZE==1 && pageNo >1){
				pageNo = pageNo - 1;
			}
			//解决最后一页跳转问题
		pagination = new Pagination(pageNo, pageSize, total,groupInfos);
		return pagination;
	}

}
