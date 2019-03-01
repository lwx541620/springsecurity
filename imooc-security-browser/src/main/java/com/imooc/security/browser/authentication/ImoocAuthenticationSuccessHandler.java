package com.imooc.security.browser.authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.imooc.security.core.LoginType;
import com.imooc.security.core.properties.SecurityProperties;

@Component("imoocAuthenticationSuccessHandler")
public class ImoocAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{
	private Logger logger=LoggerFactory.getLogger(getClass());
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException 
	{
		logger.info("登录成功");
		if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) 
		{
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		}else 
		{
			super.onAuthenticationSuccess(request, response, authentication);
		}
		
	}
}
