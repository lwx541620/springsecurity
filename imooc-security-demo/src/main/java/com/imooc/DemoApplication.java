package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.DispatcherServlet;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableSwagger2
public class DemoApplication 
{
	DispatcherServlet ss=null;
    public static void main( String[] args )
    {
    	SpringApplication.run(DemoApplication.class, args);
    }
}
