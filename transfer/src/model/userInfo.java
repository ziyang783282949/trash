package model;

import java.io.Serializable;

public class userinfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String username;
	private String userpass;
	
	public userinfo() {
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
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	@Override
	public String toString() {
		return "userinfo [id=" + id + ", username=" + username + ", userpass="
				+ userpass + "]";
	}
}
