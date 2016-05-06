package com.jf.dao;

import com.jf.model.JFUser;
import com.jf.model.JFUserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface JFUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    int countByExample(JFUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    int deleteByExample(JFUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    int insert(JFUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    int insertSelective(JFUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    List<JFUser> selectByExample(JFUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    JFUser selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") JFUser record, @Param("example") JFUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") JFUser record, @Param("example") JFUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(JFUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
	int updateByPrimaryKey(JFUser record);
    
    List<JFUser> getList();
    
    String getPassword(String id);
    
    List<JFUser> getUsers(@Param("begin")int begin,@Param("end") int end,@Param("id")String id,@Param("name")String name,@Param("type")Integer type);
    
    int getCount(@Param("id")String id,@Param("name")String name,@Param("type")Integer type);
    
    int getType(String id);
    
    
}