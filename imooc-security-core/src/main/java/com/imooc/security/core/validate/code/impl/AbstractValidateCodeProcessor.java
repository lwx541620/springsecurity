package com.imooc.security.core.validate.code.impl;

import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;
import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;
import com.imooc.security.core.validate.code.ValidateCodeProcessor;


public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor 
{

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	/**
	 * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
	 */
	@Autowired
	private Map<String, ValidateCodeGenerator> validateCodeGenerators;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.imooc.security.core.validate.code.ValidateCodeProcessor#create(org.
	 * springframework.web.context.request.ServletWebRequest)
	 */
	@Override
	public void create(ServletWebRequest request) throws Exception 
	{
		C validateCode = generate(request);
		save(request, validateCode);
		send(request, validateCode);
	}

	/**
	 * 生成校验码
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private C generate(ServletWebRequest request) 
	{
		String type = getProcessorType(request);
		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type+"CodeGenerator");
		return (C) validateCodeGenerator.generate(request);
	}

	/**
	 * 保存校验码
	 * 
	 * @param request
	 * @param validateCode
	 */
	private void save(ServletWebRequest request, C validateCode) 
	{
		//ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
		//validateCodeRepository.save(request, code, getValidateCodeType(request));
		sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX+getProcessorType(request), validateCode);
	}
	
	private String getProcessorType(ServletWebRequest request) 
	{
		String result=StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
		return result;
	}

	/**
	 * 发送校验码，由子类实现
	 * 
	 * @param request
	 * @param validateCode
	 * @throws Exception
	 */
	protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;	

}

