package com.imooc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.imooc.service.HelloService;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {

	@Autowired
	private HelloService HelloService;

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) 
	{
		System.out.println(HelloService.hello());
		System.out.println(value);
		return false;
	}

	@Override
	public void initialize(MyConstraint constraintAnnotation) 
	{
		System.out.println("chu shi hua!!!!!");
	}

}
