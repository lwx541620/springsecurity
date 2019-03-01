package com.imooc.interceptor;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//@Component
public class TimeInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/*
		 * System.out.println("preHandle"); HandlerMethod
		 * handlerMethod=(HandlerMethod)handler; String
		 * className=handlerMethod.getBean().getClass().getName(); String
		 * methodName=handlerMethod.getMethod().getName();
		 * System.out.println("classname:"+className);
		 * System.out.println("methodName:"+methodName);
		 * request.setAttribute("startTime", new Date().getTime());
		 */
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception 
	{
		/*
		 * System.out.println("postHandle"); Long startTime=(Long)
		 * request.getAttribute("startTime"); System.out.println("cost111  time:"+(new
		 * Date().getTime()-startTime));
		 */
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//System.out.println("afterCompletion");
	}

}
