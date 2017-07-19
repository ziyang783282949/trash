package serviceimpl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.enterprise.inject.New;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import model.UserInfo;
import service.ModifyService;
import userDaoServer.UserDaoServer;
import userDaoService.UserDaoService;

public class ModyfySerser implements ModifyService{
	private UserDaoService dao=new UserDaoServer();

	@Override
	public UserInfo Modify(ServletConfig servletConfig,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		// TODO Auto-generated method stub
		
		UserInfo uInfo=GetUploadUserInfo(servletConfig,request,response);
		//uInfo=JsonToUser(UserJson);
		Boolean flag=dao.modify(uInfo);
		if(flag){
			return uInfo;
		}
		return null;
	}
	private UserInfo GetUploadUserInfo(ServletConfig servletConfig,HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		SmartUpload su=new SmartUpload();
		try {
			su.initialize(servletConfig, request, response);
			su.upload();
			
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String UserString=su.getRequest().getParameter("data");
		UserString=UserString.replace("\\","");
		UserString=UserString.substring(1,UserString.length()-1);
		try {
			UserString=URLDecoder.decode(UserString, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		UserInfo info=JsonToUser(UserString);
		info.setUrlusericon("E:\\software\\work\\upload\\"+info.getUrlusericon().substring(info.getUrlusericon().lastIndexOf("/")+1));
		System.out.println(new Gson().toJson(info).toString());
		String urlusericon="E:\\software\\work\\upload\\"+info.getUsername();
		File file=new File(urlusericon);
		if(!file.exists()){
			file.mkdir();
		}
		try {
			su.save(urlusericon);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
	
	public UserInfo JsonToUser(String str) {
		JSONObject userInfo=JSONObject.fromObject(str);
		//JSONObject userJson=userInfo.getJSONObject("userInfo");
		UserInfo user=new UserInfo();
		user.setUsername(userInfo.get("username").toString());
		//user.setPassword(userInfo.get("password").toString());
		user.setSex(userInfo.get("sex").toString());
		user.setUrlusericon("G:\\work\\upload\\"+userInfo.get("username").toString()+"\\"+userInfo.get("urlusericon").toString());
		return user;
	}

	

}
