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
import com.jf.dao.JFTeacherMapper;
import com.jf.model.JFTeacher;
import com.jf.service.JFTeacherService;
import com.jf.util.Constants;

/**
 * @author JF
 *
 *@date 2016年3月25日 下午7:08:36
 *
 */
@Service
@Transactional
public class JFTeacherServiceImpl implements JFTeacherService {

	protected static final Log log = LogFactory.getLog(JFTeacherServiceImpl.class);
	
	@Autowired
	private JFTeacherMapper teacherMapper;
	
	/* (non-Javadoc)
	 * @see com.jf.service.JFTeacherService#getList()
	 */
	@Override
	public List<JFTeacher> getList() {
		return teacherMapper.getList();
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFTeacherService#getTeacher()
	 */
	@Override
	public JFTeacher getTeacher(String teaId) {
		return teacherMapper.getTeacher(teaId);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFTeacherService#del(java.lang.String)
	 */
	@Override
	public void del(String teaId) {
		teacherMapper.deleteByPrimaryKey(teaId);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFTeacherService#update(com.jf.model.JFTeacher)
	 */
	@Override
	public void update(JFTeacher teacher) {
		teacherMapper.update(teacher);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFTeacherService#insert(com.jf.model.JFTeacher)
	 */
	@Override
	public void insert(JFTeacher teacher) {
		teacherMapper.insertSelective(teacher);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFTeacherService#getPage(int, int, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Pagination getPage(int pageNo, int pageSize, String teaId,String teaName, String groId, Integer groType, Integer speechCount) {
		int total = 0;
		Pagination pagination = null;
		List<JFTeacher> teachers = null;
		JFTeacherServiceImpl.log.debug("receive:pageNo=" + pageNo + " pageSize=" + pageSize);
		int begin = (pageNo - 1) * pageSize;
		int end = begin + pageSize;
		teachers = this.teacherMapper.getTeachers(begin, end, teaId, teaName, groId, groType, speechCount);
		total = this.teacherMapper.getCount(teaId, teaName, groId, groType, speechCount);
		if (total/Constants.PAGE_SIZE  < pageNo && total%Constants.PAGE_SIZE==1 && pageNo >1){
			pageNo = pageNo - 1;
		}
		//解决最后一页跳转问题
		pagination = new Pagination(pageNo, pageSize, total, teachers);
		return pagination;
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFTeacherService#getteaId(java.lang.String)
	 */
	@Override
	public String getTeaId(String teaName) {
		return teacherMapper.getTeaId(teaName);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFTeacherService#getTeacherName(java.lang.String)
	 */
	@Override
	public String getTeacherName(String teaId) {
		// TODO Auto-generated method stub
		return teacherMapper.getTeaName(teaId);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFTeacherService#updatedel(java.lang.String)
	 */
	@Override
	public void updatedel(String groId) {
		teacherMapper.updatedel(groId);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFTeacherService#getTeacherList(com.jf.model.JFTeacher)
	 */
	@Override
	public List<JFTeacher> getTeacherList(String groId) {
		// TODO Auto-generated method stub
		return teacherMapper.getTeacherList(groId);
	}

}
