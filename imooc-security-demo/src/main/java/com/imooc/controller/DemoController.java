package com.imooc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.jto.User;
import com.imooc.jto.UserQueryCondition;


@RestController
public class DemoController 
{
  @GetMapping("/me")
  public Authentication getCurrentUser(Authentication authentication) 
  {
	return authentication;
  }
  @GetMapping("/me2")
  public UserDetails getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) 
  {
	return userDetails;
  }
  
  @GetMapping("/me3")
  public Authentication getCurrentUser3() 
  {
	return SecurityContextHolder.getContext().getAuthentication();
  }
	
   @GetMapping("/hello/{id}")
   public String hello(@PathVariable String id) 
   {
	   //throw new UserNotExistException("1");
	   System.out.println("execute hello method");
	   return "hello";
   }
   
   @GetMapping("/hello")
   public String he() 
   {
	   //throw new UserNotExistException("1");
	   System.out.println("execute hello method");
	   return "hello";
   }
   
   @GetMapping("/hello/world/{id}")
   public String hello11(@PathVariable String id) 
   {
	   //throw new UserNotExistException("1");
	   System.out.println("execute hello method");
	   return "hello";
   }
   
   
   @PostMapping(value="/user")
   public User create(@Valid @RequestBody User user,BindingResult errors) 
   {
	   if(errors.hasErrors()) 
	   {
		   errors.getAllErrors().stream().forEach(error->{System.out.println(error.getDefaultMessage());});
	   }
	   System.out.println(user.getId());
	   System.out.println(user.getUsername());
	   System.out.println(user.getPassword());
	   System.out.println(user.getBirthday());
	   user.setId("1");
	   return user;
   }
   @RequestMapping(value="/user",method=RequestMethod.GET)
   @JsonView(User.Simple.class)
   public List<User> list(
		   //@RequestParam String username
		   UserQueryCondition condition,
		   @PageableDefault(size=18) Pageable pageable
		   )
   {
	   System.out.println("request param"+ReflectionToStringBuilder.toString(condition,ToStringStyle.MULTI_LINE_STYLE));
	   System.out.println(pageable.getPageSize());
	   System.out.println(pageable.getPageNumber());
	   System.out.println(pageable.getSort());
	   List<User> list=new ArrayList<User>();
	   list.add(new User("aaa","1111"));
	   list.add(new User("bbb","2222"));
	   list.add(new User("ccc","3333"));
	   return list;
   }
   @RequestMapping(value="/user/{id:\\d+}",method=RequestMethod.GET)
   @JsonView(User.Detail.class)
   public User getUser(@PathVariable Integer id) 
   {
	   User user=new User();
	   user.setUsername("tom");
	   user.setPassword("ffff");
	   return user;
   }
   
   @RequestMapping(value="/user/{id:\\d+}",method=RequestMethod.PUT)
   @JsonView(User.Detail.class)
   public User updateUser(@Valid @RequestBody User user,BindingResult errors) 
   {
	   if(errors.hasErrors()) 
	   {
		   errors.getAllErrors().stream().forEach(error->{
			   FieldError fieldError=(FieldError) error;
			   String message=fieldError.getField()+error.getDefaultMessage();
			   System.out.println(message);
			   });
	   }	  
	   user.setId("1");
	   user.setUsername("tom");
	   user.setPassword("ffff");
	   return user;
   }
   
   @RequestMapping(value="/user/{id:\\d+}",method=RequestMethod.DELETE)
   @JsonView(User.Detail.class)
   public void deleteUser(@Valid @RequestBody User user,BindingResult errors) 
   {
	   if(errors.hasErrors()) 
	   {
		   errors.getAllErrors().stream().forEach(error->{
			   FieldError fieldError=(FieldError) error;
			   String message=fieldError.getField()+error.getDefaultMessage();
			   System.out.println(message);
			   });
	   }	  
	   user.setId("1");
	   user.setUsername("tom");
	   user.setPassword("ffff");
   }
   
   
}
