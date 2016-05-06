/**
 * 
 */
package com.jf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.JFPercentMapper;
import com.jf.model.JFPercent;
import com.jf.service.JFPercentService;

/**
 * @author JF
 *
 *@date 2016年3月23日 上午8:46:37
 *
 */
@Service
@Transactional
public class JFPercentServiceImpl implements JFPercentService {

	@Autowired
	private JFPercentMapper percentMapper;
	
	/* (non-Javadoc)
	 * @see com.jf.service.JFPercentService#getPercent()
	 */
	@Override
	public JFPercent getPercent() {
		return percentMapper.getPercent();
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFPercentService#upPercent(double)
	 */
	@Override
	public void upPercent(JFPercent percent) {
		percentMapper.updatePercent(percent);
	}

}
