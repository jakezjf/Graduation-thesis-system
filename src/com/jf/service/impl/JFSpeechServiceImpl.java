/**
 * 
 */
package com.jf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.JFSpeechMapper;
import com.jf.model.JFSpeech;
import com.jf.service.JFSpeechService;

/**
 * @author JF
 *
 *@date 2016年4月5日 下午5:44:06
 *
 */
@Service
@Transactional
public class JFSpeechServiceImpl implements JFSpeechService {
	
	@Autowired
	private JFSpeechMapper speechMapper;

	/* (non-Javadoc)
	 * @see com.jf.service.JFSpeechService#del(java.lang.String)
	 */
	@Override
	public void del(String speechId) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFSpeechService#update(com.jf.model.JFSpeech)
	 */
	@Override
	public void update(JFSpeech speech) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFSpeechService#insert(com.jf.model.JFSpeech)
	 */
	@Override
	public void insert(JFSpeech speech) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFSpeechService#getList()
	 */
	@Override
	public List<JFSpeech> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFSpeechService#getSpeechs()
	 */
	@Override
	public List<JFSpeech> getSpeechs() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jf.service.JFSpeechService#getSpeech(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public JFSpeech getSpeech(String speechId, String teaId, String stuId,
			String teaTitle, String speechTitle) {
		// TODO Auto-generated method stub
		return speechMapper.getSpeech(speechId, teaId, stuId, teaTitle, speechTitle);
	}

}
