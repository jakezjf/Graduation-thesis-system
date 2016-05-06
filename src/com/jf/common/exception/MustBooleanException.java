package com.jf.common.exception;

import freemarker.template.TemplateModelException;

/**
 * 非布尔参数
 * 
 * @author
 * 
 */
@SuppressWarnings("serial")
public class MustBooleanException extends TemplateModelException {

	public MustBooleanException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a boolean.");
	}
}
