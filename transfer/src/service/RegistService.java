package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserInfo;

public interface RegistService {
	public UserInfo regist(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
