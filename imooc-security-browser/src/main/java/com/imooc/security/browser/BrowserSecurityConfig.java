package com.imooc.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.imooc.security.browser.authentication.ImoocAuthenticationFailureHandler;
import com.imooc.security.browser.authentication.ImoocAuthenticationSuccessHandler;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private ImoocAuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
	
	@Autowired
	private ImoocAuthenticationFailureHandler imoocAuthenticationFailureHandler;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() 
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() 
	{
		JdbcTokenRepositoryImpl tokenRepository=new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		//tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}
	
	/*
	 * @Bean public ObjectMapper objectMapper() { return new ObjectMapper(); }
	 */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		ValidateCodeFilter validateCodeFilter=new ValidateCodeFilter();
		validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
		validateCodeFilter.setSecurityProperties(securityProperties);
		validateCodeFilter.afterPropertiesSet();
		http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
		.formLogin()
	  //.loginPage("/imooc-signIn.html")
	  .loginPage("/authentication/require")
	  .loginProcessingUrl("/authentication/form")
	  .successHandler(imoocAuthenticationSuccessHandler)
	  .failureHandler(imoocAuthenticationFailureHandler)
	  .and()
	  .rememberMe()
	  .tokenRepository(persistentTokenRepository())
	  .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
	  .userDetailsService(userDetailsService)
	  //http.httpBasic()
	  .and()
	  .authorizeRequests()
	  //.antMatchers("/imooc-signIn.html").permitAll()
	  .antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage(),"/code/image").permitAll()
	  .anyRequest()
	  .authenticated()
	  .and().csrf().disable();
	  
	  UsernamePasswordAuthenticationFilter sss=null;
	  BasicAuthenticationFilter nn=null;
	  ExceptionTranslationFilter filterExceptin=null;
	  FilterSecurityInterceptor interceptor=null;
	  
	  UserDetailsService user=null;
	}
}
