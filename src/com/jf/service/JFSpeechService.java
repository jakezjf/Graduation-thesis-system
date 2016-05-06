/**
 * 
 */
package com.jf.service;

import java.util.List;

import com.jf.model.JFSpeech;

/**
 * @author JF
 *
 *@date 2016年4月5日 下午5:39:24
 *
 */
public interface JFSpeechService {

	void del(String speechId);
	
	void update(JFSpeech speech);
	
	void insert(JFSpeech speech);
	
	List<JFSpeech> getList();
	
	List<JFSpeech> getSpeechs();
	
    JFSpeech getSpeech(String speechId,String teaId,String stuId,String teaTitle,String speechTitle);
	
}
