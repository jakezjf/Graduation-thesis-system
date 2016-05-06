package com.jf.common.exception;

import freemarker.template.TemplateModelException;

/**
 * 缺少必须参数异常
 * 
 * @author
 * 
 */
@SuppressWarnings("serial")
public class ParamsRequiredException extends TemplateModelException {

	public ParamsRequiredException(String paramName) {
		super("The required \"" + paramName + "\" paramter is missing.");
	}
}
