package model;

import java.io.Serializable;

public class UserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String username;
	private String password;
	//1ÎªÄÐ
	private String sex;
	private String urlusericon;
	
	
	
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUrlusericon() {
		return urlusericon;
	}
	public void setUrlusericon(String urlusericon) {
		this.urlusericon = urlusericon;
	}
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "userinfo [id=" + id + ", username=" + username + ", password="
				+ password + "]";
	}
	
}
