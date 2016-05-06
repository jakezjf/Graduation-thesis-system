/**
 * 
 */
package com.jf.service;

import java.util.List;

import com.jf.model.JFTeacherMark;

/**
 * @author JF
 *
 *@date 2016年3月31日 下午1:31:25
 *
 */
public interface JFTeacherMarkService {
	
	List<JFTeacherMark> teacherMarks(JFTeacherMark teacherMark);
	
	void delTeacherMarks(JFTeacherMark teacherMark);
	
	void updateTeacherMarks(JFTeacherMark teacherMark);
	
	void insertTeacherMark(JFTeacherMark teacherMark);
	
	Integer getMark(JFTeacherMark teacherMark);
	
	JFTeacherMark getTeacherMark(JFTeacherMark teacherMark);

}
