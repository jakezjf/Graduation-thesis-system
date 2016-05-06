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
import com.jf.dao.JFStudentMapper;
import com.jf.model.JFStudent;
import com.jf.service.JFStudentService;
import com.jf.util.Constants;

/**
 * @author JF
 *
 *@date 2016年3月24日 下午1:43:15
 *
 */
@Service
@Transactional
public class JFStudentServiceImpl implements JFStudentService {
	
	protected static final Log log = LogFactory.getLog(JFStudentServiceImpl.class);

	@Autowired
	private JFStudentMapper studentMapper;

	/* (non-Javadoc)
	 * @see com.jf.service.JFStudentService#getList()
	 */
	@Override
	public List<JFStudent> getList() {
		return studentMapper.getList();
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFStudentService#getStudent(java.lang.String)
	 */
	@Override
	public JFStudent getStudent(String id) {
		return studentMapper.getStudent(id);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFStudentService#update(com.jf.model.JFStudent)
	 */
	@Override
	public void update(JFStudent student) {
		studentMapper.updateByPrimaryKeySelective(student);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFStudentService#del(java.lang.String)
	 */
	@Override
	public void del(String id) {
		studentMapper.deleteByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFStudentService#insert(com.jf.model.JFStudent)
	 */
	@Override
	public void insert(JFStudent student) {
		studentMapper.insert(student);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFStudentService#getPage(int, int)
	 */
	@Override
	public Pagination getPage(int pageNo, int pageSize,String stuId,String stuName,String teaId,String groupId,String speechId,int classNo) {
		int total = 0;
		Pagination pagination = null;
		List<JFStudent> students = null;
		JFStudentServiceImpl.log.debug("receive:pageNo=" + pageNo + " pageSize=" + pageSize);
		int begin = (pageNo - 1) * pageSize;
		int end = begin + pageSize;
	    students = studentMapper.getStudents(begin,end,stuId,stuName,teaId,groupId,speechId,classNo);
	    total = studentMapper.getCount(stuId,stuName,teaId,groupId,speechId,classNo);
	    if (total/Constants.PAGE_SIZE  < pageNo && total%Constants.PAGE_SIZE==1 && pageNo >1){
			pageNo = pageNo - 1;
		}
		//解决最后一页跳转问题
	    pagination = new Pagination(pageNo, pageSize, total, students);
	    return pagination;
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFStudentService#updatedel(java.lang.String)
	 */
	@Override
	public void updatedel(String groId) {
		studentMapper.updatedel(groId);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFStudentService#getStudentList(com.jf.model.JFStudent)
	 */
	@Override
	public List<JFStudent> getStudentList(JFStudent student) {
		return studentMapper.getStudentList(student);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFStudentService#getStu(com.jf.model.JFStudent)
	 */
	@Override
	public JFStudent getStu(JFStudent student) {
		// 查找各种数据
		return studentMapper.getStu(student);
	}
	


}
