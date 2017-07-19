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
	private Integer credits;
	private Integer change_credits;
	private Integer contribution_credits;
	
	
	
	
	public Integer getCredits() {
		return credits;
	}
	public void setCredits(Integer credits) {
		this.credits = credits;
	}
	public Integer getChange_credits() {
		return change_credits;
	}
	public void setChange_credits(Integer change_credits) {
		this.change_credits = change_credits;
	}
	public Integer getContribution_credits() {
		return contribution_credits;
	}
	public void setContribution_credits(Integer contribution_credits) {
		this.contribution_credits = contribution_credits;
	}
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
