package com.jf.model;

public class JFComment {
	
	private String stuName;
	
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.speech_id
     *
     * @mbggenerated
     */
    private String speechId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.tea_com
     *
     * @mbggenerated
     */
    private String teaCom;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.gro_com
     *
     * @mbggenerated
     */
    private String groCom;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.back1
     *
     * @mbggenerated
     */
    private String back1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.back2
     *
     * @mbggenerated
     */
    private String back2;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.speech_id
     *
     * @return the value of comment.speech_id
     *
     * @mbggenerated
     */
    public String getSpeechId() {
        return speechId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.speech_id
     *
     * @param speechId the value for comment.speech_id
     *
     * @mbggenerated
     */
    public void setSpeechId(String speechId) {
        this.speechId = speechId == null ? null : speechId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.tea_com
     *
     * @return the value of comment.tea_com
     *
     * @mbggenerated
     */
    public String getTeaCom() {
        return teaCom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.tea_com
     *
     * @param teaCom the value for comment.tea_com
     *
     * @mbggenerated
     */
    public void setTeaCom(String teaCom) {
        this.teaCom = teaCom == null ? null : teaCom.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.gro_com
     *
     * @return the value of comment.gro_com
     *
     * @mbggenerated
     */
    public String getGroCom() {
        return groCom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.gro_com
     *
     * @param groCom the value for comment.gro_com
     *
     * @mbggenerated
     */
    public void setGroCom(String groCom) {
        this.groCom = groCom == null ? null : groCom.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.back1
     *
     * @return the value of comment.back1
     *
     * @mbggenerated
     */
    public String getBack1() {
        return back1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.back1
     *
     * @param back1 the value for comment.back1
     *
     * @mbggenerated
     */
    public void setBack1(String back1) {
        this.back1 = back1 == null ? null : back1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.back2
     *
     * @return the value of comment.back2
     *
     * @mbggenerated
     */
    public String getBack2() {
        return back2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.back2
     *
     * @param back2 the value for comment.back2
     *
     * @mbggenerated
     */
    public void setBack2(String back2) {
        this.back2 = back2 == null ? null : back2.trim();
    }

	/**
	 * @return the stuName
	 */
	public String getStuName() {
		return stuName;
	}

	/**
	 * @param stuName the stuName to set
	 */
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	/**
	 * jianfeng  加一个答辩组编号呗
	 */
	
	private String groId;
	
	private String teaName;
	

	/**
	 * @return the groId
	 */
	public String getGroId() {
		return groId;
	}

	/**
	 * @param groId the groId to set
	 */
	public void setGroId(String groId) {
		this.groId = groId;
	}

	/**
	 * @return the teaName
	 */
	public String getTeaName() {
		return teaName;
	}

	/**
	 * @param teaName the teaName to set
	 */
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	
}