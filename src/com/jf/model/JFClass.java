package com.jf.model;

public class JFClass {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column class.class_no
     *
     * @mbggenerated
     */
    private Integer classNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column class.class_name
     *
     * @mbggenerated
     */
    private String className;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column class.class_no
     *
     * @return the value of class.class_no
     *
     * @mbggenerated
     */
    public Integer getClassNo() {
        return classNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column class.class_no
     *
     * @param classNo the value for class.class_no
     *
     * @mbggenerated
     */
    public void setClassNo(Integer classNo) {
        this.classNo = classNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column class.class_name
     *
     * @return the value of class.class_name
     *
     * @mbggenerated
     */
    public String getClassName() {
        return className;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column class.class_name
     *
     * @param className the value for class.class_name
     *
     * @mbggenerated
     */
    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }
}