package com.jf.common.exception;

import freemarker.template.TemplateModelException;

/**
 * 非数字参数异帄1�7
 * 
 * @author
 * 
 */
@SuppressWarnings("serial")
public class MustNumberException extends TemplateModelException {

	public MustNumberException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a number.");
	}
}
