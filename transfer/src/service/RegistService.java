package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RegistService {
	public String regist(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
