package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserInfo;

public interface LoginService {
	public UserInfo login(HttpServletRequest request,HttpServletResponse response) throws Exception;

}
