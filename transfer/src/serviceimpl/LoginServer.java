package serviceimpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.enterprise.inject.New;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.userinfo;
import net.sf.json.JSONObject;

import service.LoginService;
import userDaoServer.UserDaoServer;
import userDaoService.UserDaoService;

public class LoginServer implements LoginService{
private UserDaoService dao=new UserDaoServer();
	@Override
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String content=GetString(request,response);
		userinfo user=new userinfo();
		user=JsonToUser(content);
		userinfo userinfo=dao.check(user.getUsername(), user.getUserpass());
		if(userinfo!=null){
			return "用户名已存在";
		}
			dao.regist(user.getUsername(), user.getUserpass());
			return "注册成功";
	}
	public userinfo JsonToUser(String str) {
		JSONObject userInfo=JSONObject.fromObject(str);
		JSONObject userJson=userInfo.getJSONObject("userInfo");
		userinfo user=new userinfo();
		user.setUsername(userJson.get("username").toString());
		user.setUserpass(userJson.get("password").toString());
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
