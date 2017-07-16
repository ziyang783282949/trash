package serviceimpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import model.UserInfo;

import service.LoginService;
import userDaoServer.UserDaoServer;
import userDaoService.UserDaoService;

public class LoginServer implements LoginService{
private UserDaoService dao=new UserDaoServer();
	@Override
	public UserInfo login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String content=GetString(request,response);
		System.out.println(content);
		UserInfo user=new UserInfo();
		user=JsonToUser(content);
		UserInfo finaluserinfo=dao.login(user.getUsername(), user.getPassword());
		if(finaluserinfo!=null){
			return finaluserinfo;
		}
		if(finaluserinfo==null){
			//System.out.println(finaluserinfo);
			return null;
		}
		return null;
	}
	public UserInfo JsonToUser(String str) {
		JSONObject userInfo=JSONObject.fromObject(str);
		//JSONObject userJson=userInfo.getJSONObject("userInfo");
		UserInfo user=new UserInfo();
		user.setUsername(userInfo.get("username").toString());
		user.setPassword(userInfo.get("password").toString());
		return user;
	}
	public String GetString(HttpServletRequest request, HttpServletResponse response){
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			String line="";
			StringBuilder builder=new StringBuilder();
			BufferedReader br=request.getReader();
			while((line=br.readLine())!=null){
				builder.append(line);
			}
			return URLDecoder.decode(builder.toString(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
