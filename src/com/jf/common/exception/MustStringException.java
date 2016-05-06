package com.jf.common.exception;

import freemarker.template.TemplateModelException;

/**
 * 非数字参数异帄1�7
 * 
 * @author
 * 
 */
@SuppressWarnings("serial")
public class MustStringException extends TemplateModelException {

	public MustStringException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a string.");
	}
}
