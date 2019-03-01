/**
 * 
 */
package com.imooc.jto;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.validator.MyConstraint;

/**
 * @author user
 *
 */
 
public class User
{
	public interface Simple{};
	
	public interface Detail extends Simple{};
	
	private String id;
	@MyConstraint(message="   user name is name")
    private String username;
  
    @NotBlank(message="不能为空")
    private String password;
    
    @Past
    private Date birthday;

    public User() {}
     
    public User(String username,String password) 
    {
    	this.username=username;
    	this.password=password;
    }
    
    @JsonView(Simple.class)
    public String getId() 
    {
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	@JsonView(Simple.class)
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username) 
	{
		this.username = username;
	}
	@JsonView(Detail.class)
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}

	@JsonView(Simple.class)
	public Date getBirthday() 
	{
		return birthday;
	}

	public void setBirthday(Date birthday) 
	{
		this.birthday = birthday;
	}

	@Override
	public String toString() 
	{
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", birthday=" + birthday + "]";
	}
}
