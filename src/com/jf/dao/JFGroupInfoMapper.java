package com.jf.dao;

import com.jf.model.JFGroupInfo;
import com.jf.model.JFGroupInfoExample;

import java.sql.Time;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface JFGroupInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    int countByExample(JFGroupInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    int deleteByExample(JFGroupInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String groId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    int insert(JFGroupInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    int insertSelective(JFGroupInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    List<JFGroupInfo> selectByExample(JFGroupInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    JFGroupInfo selectByPrimaryKey(String groId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") JFGroupInfo record, @Param("example") JFGroupInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") JFGroupInfo record, @Param("example") JFGroupInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(JFGroupInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(JFGroupInfo groupInfo);
    
    List<JFGroupInfo> getGroupInfos(@Param("begin")int begin,@Param("end") int end,@Param("groId")String groId,@Param("groAddress")String groAddress, @Param("groTime")Time groTime);
    
    JFGroupInfo getGroupInfo(JFGroupInfo groupInfo);
    
    int getCount(@Param("groId")String groId,@Param("groAddress")String groAddress, @Param("groTime")Time groTime);
    
    List<JFGroupInfo> getList();
    
    void update(JFGroupInfo groupInfo);
    
    
}