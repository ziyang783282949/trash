package service;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserInfo;

public interface ModifyService {
	public UserInfo Modify(ServletConfig servletConfig,HttpServletRequest request,HttpServletResponse response)throws Exception;
}
