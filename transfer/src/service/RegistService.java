package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.userinfo;

public interface RegistService {
	public userinfo regist(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
