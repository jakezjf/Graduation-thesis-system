package com.jf.common.exception;

import freemarker.template.TemplateModelException;

/**
 * 非布尔参数异帄1�7
 * 
 * @author
 * 
 */
@SuppressWarnings("serial")
public class MustDateException extends TemplateModelException {

	public MustDateException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a date.");
	}
}
