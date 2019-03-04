package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器，封装不同校验码逻辑
 * @author user
 *
 */
public interface ValidateCodeProcessor 
{
  String SESSION_KEY_PREFIX="SESSION_KEY_FOR_CODE_";
  /**
      *  创建校验码
   * @param request
   * @throws Exception
   */
  void create(ServletWebRequest request) throws Exception;
}
