package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {
	public String login(HttpServletRequest request,HttpServletResponse response) throws Exception;

}
