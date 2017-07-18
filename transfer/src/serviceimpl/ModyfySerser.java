package serviceimpl;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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
		String UserJson=GetUploadUserInfo(servletConfig,request,response);
		UserInfo uInfo=JsonToUser(UserJson);
		return null;
	}
	private String GetUploadUserInfo(ServletConfig servletConfig,HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		SmartUpload su=new SmartUpload();
		try {
			su.initialize(servletConfig, request, response);
			su.upload();
			su.save("G:\\work\\upload");
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
		System.out.println(UserString);
		return UserString;
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
