/**
 * 
 */
package com.jf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.JFTeacherMarkMapper;
import com.jf.model.JFTeacherMark;
import com.jf.service.JFTeacherMarkService;

/**
 * @author JF
 *
 *@date 2016年4月13日 下午1:55:06
 *
 */
@Service
@Transactional
public class JFTeacherMarkServiceImpl implements JFTeacherMarkService {
	
	@Autowired
	private JFTeacherMarkMapper teacherMarkMapper;

	/* (non-Javadoc)
	 * @see com.jf.service.JFTeacherMarkService#teacherMarks(java.lang.String, java.lang.String)
	 */
	@Override
	public List<JFTeacherMark> teacherMarks(JFTeacherMark teacherMark) {
		// TODO Auto-generated method stub
		return teacherMarkMapper.getTeacherMarks(teacherMark);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFTeacherMarkService#delTeacherMarks(java.lang.String, java.lang.String)
	 */
	@Override
	public void delTeacherMarks(JFTeacherMark teacherMark) {
		// TODO Auto-generated method stub
		teacherMarkMapper.delTeacherMark(teacherMark);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFTeacherMarkService#updateTeacherMarks(com.jf.model.JFTeacherMark)
	 */
	@Override
	public void updateTeacherMarks(JFTeacherMark teacherMark) {
		// TODO Auto-generated method stub
		teacherMarkMapper.updateByPrimaryKeySelective(teacherMark);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFTeacherMarkService#insertTeacherMark(com.jf.model.JFTeacherMark)
	 */
	@Override
	public void insertTeacherMark(JFTeacherMark teacherMark) {
		// TODO Auto-generated method stub
		teacherMarkMapper.insertSelective(teacherMark);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFTeacherMarkService#getMark(com.jf.model.JFTeacherMark)
	 */
	@Override
	public Integer getMark(JFTeacherMark teacherMark) {
		// TODO Auto-generated method stub
		return teacherMarkMapper.getMark(teacherMark);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFTeacherMarkService#getTeacherMark(com.jf.model.JFTeacherMark)
	 */
	@Override
	public JFTeacherMark getTeacherMark(JFTeacherMark teacherMark) {
		// TODO Auto-generated method stub
		return teacherMarkMapper.getTeacherMark(teacherMark);
	}

}
