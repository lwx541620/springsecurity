package com.imooc.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;

public interface ValidateCodeGenerator 
{
	public ImageCode generate(HttpServletRequest request);
}