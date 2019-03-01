package com.imooc.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Aspect
@Component
public class TimeAspect 
{
   @Around("execution(* com.imooc.controller.DemoController.*(..))")
   public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable 
   {
	   System.out.println("time aspect start");
	   Long start=new Date().getTime();
	   Object object = pjp.proceed();
	   Object[] args = pjp.getArgs();
	   for(Object obj:args) 
	   {
		   System.out.println("arg is:"+obj);
	   }
	   System.out.println("time aspect cost:"+(new Date().getTime()-start));
	   System.out.println("time aspect end");
	   return object;
   }
}
