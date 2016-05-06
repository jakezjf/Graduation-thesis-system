/**
 * 
 */
package com.jf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.page.Pagination;
import com.jf.dao.JFCommentMapper;
import com.jf.model.JFComment;
import com.jf.service.JFCommentService;
import com.jf.util.Constants;

/**
 * @author JF
 *
 *@date 2016年4月8日 下午1:01:44
 *
 */
@Service
@Transactional
public class JFCommentServiceImpl implements JFCommentService {

	@Autowired
	private JFCommentMapper commentMapper;
	
	/* (non-Javadoc)
	 * @see com.jf.service.JFCommentService#del(java.lang.String)
	 */
	@Override
	public void del(String speechId) {
		// TODO Auto-generated method stub
		commentMapper.deleteByPrimaryKey(speechId);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFCommentService#insert(java.lang.String)
	 */
	@Override
	public void insert(JFComment comment) {
		// TODO Auto-generated method stub
		commentMapper.insertSelective(comment);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFCommentService#update(com.jf.model.JFComment)
	 */
	@Override
	public void update(JFComment comment) {
		// TODO Auto-generated method stub
		commentMapper.updateByPrimaryKeySelective(comment);
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFCommentService#getComment(java.lang.String)
	 */
	@Override
	public JFComment getComment(String speechId) {
		// TODO Auto-generated method stub
		return commentMapper.selectByPrimaryKey(speechId);
	}
	/* (non-Javadoc)
	 * @see com.jf.service.JFCommentService#getPage(int, int)
	 */
	@Override
	public Pagination getPage(int pageNo, int pageSize) {
		int total = 0;
		Pagination pagination = null;
//		JFStudent student = new JFStudent();
		List<JFComment> comments = null;
		int begin = (pageNo - 1) * pageSize;
		int end = begin + pageSize;
		JFStudentServiceImpl.log.debug("receive:pageNo=" + pageNo + " pageSize=" + pageSize);
		comments = commentMapper.getList(begin,end);
		total = commentMapper.getCount();
//		for (int i = 0; i < comments.size(); i++) {
//			student.setSpeechId(comments.get(i).getSpeechId());
//			comments.get(i).setStuName(studentService.getStu(student).getStuName());
//		}
		 if (total/Constants.PAGE_SIZE  < pageNo && total%Constants.PAGE_SIZE==1 && pageNo >1){
				pageNo = pageNo - 1;
			}
		 pagination = new Pagination(pageNo, pageSize, total, comments);
		return pagination;
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFCommentService#getList()
	 */
	@Override
	public List<JFComment> getList() {
		// TODO Auto-generated method stub
		return commentMapper.getComments();
	}

}
