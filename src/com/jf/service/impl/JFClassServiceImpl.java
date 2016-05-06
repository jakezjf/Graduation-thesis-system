/**
 * 
 */
package com.jf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.JFClassMapper;
import com.jf.model.JFClass;
import com.jf.service.JFClassService;

/**
 * @author JF
 *
 *@date 2016年4月27日 下午1:12:07
 *
 */
@Service
@Transactional
public class JFClassServiceImpl implements JFClassService {
	
	@Autowired
	private JFClassMapper classMapper;

	/* (non-Javadoc)
	 * @see com.jf.service.JFClassService#del(com.jf.model.JFClass)
	 */
	@Override
	public void del(int classNo) {
		// TODO Auto-generated method stub
		classMapper.deleteByPrimaryKey(classNo);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFClassService#update(com.jf.model.JFClass)
	 */
	@Override
	public void update(JFClass jfClass) {
		// TODO Auto-generated method stub
		classMapper.updateByPrimaryKeySelective(jfClass);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFClassService#insert(com.jf.model.JFClass)
	 */
	@Override
	public void insert(JFClass jfClass) {
		// TODO Auto-generated method stub
		classMapper.insertSelective(jfClass);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFClassService#getClass(com.jf.model.JFClass)
	 */
	@Override
	public JFClass getClass(JFClass jfClass) {
		// TODO Auto-generated method stub
		return classMapper.getClass(jfClass);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFClassService#getClasses(com.jf.model.JFClass)
	 */
	@Override
	public List<JFClass> getClasses(JFClass jfClass) {
		// TODO Auto-generated method stub
		return classMapper.getClasses(jfClass);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFClassService#getClassName(int)
	 */
	@Override
	public String getClassName(int classNo) {
		// TODO Auto-generated method stub
		return classMapper.getClassName(classNo);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFClassService#getList()
	 */
	@Override
	public List<JFClass> getList() {
		// TODO Auto-generated method stub
		return classMapper.getList();
	}

}
