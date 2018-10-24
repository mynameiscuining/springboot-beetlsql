package cn.njyazheng.demo.domain;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2018-10-09
*/
@Table(name="practice.user")
public class User   {
	
	private Integer id ;
	private Integer age ;
	/*
	用户角色
	*/
	private Integer roleid ;
	private String name ;
	/*
	用户名称
	*/
	private String username ;
	private Date createDate ;
	
	public User() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public Integer getAge(){
		return  age;
	}
	public void setAge(Integer age ){
		this.age = age;
	}
	
	/**
	* 用户角色
	*@return 
	*/
	public Integer getRoleid(){
		return  roleid;
	}
	/**
	* 用户角色
	*@param  roleid
	*/
	public void setRoleid(Integer roleid ){
		this.roleid = roleid;
	}
	
	public String getName(){
		return  name;
	}
	public void setName(String name ){
		this.name = name;
	}
	
	/**
	* 用户名称
	*@return 
	*/
	public String getUsername(){
		return  username;
	}
	/**
	* 用户名称
	*@param  username
	*/
	public void setUsername(String username ){
		this.username = username;
	}
	
	public Date getCreateDate(){
		return  createDate;
	}
	public void setCreateDate(Date createDate ){
		this.createDate = createDate;
	}
	

}
