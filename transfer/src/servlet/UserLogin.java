package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.BaseEntity;
import model.UserInfo;
import net.sf.json.JSONObject;

import service.LoginService;
import service.RegistService;
import serviceimpl.LoginServer;
import serviceimpl.RegistServer;

public class UserLogin extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserLogin() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LoginService service=new LoginServer();
		UserInfo user=null;
		try {
			user = service.login(request, response);
			//System.out.println(user.toString()+"");
			returnClient(user, request, response);
			//System.out.println(user+"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void returnClient(UserInfo user, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		Gson gson=new Gson();
		BaseEntity<UserInfo> entity=new BaseEntity<UserInfo>();
		UserInfo uuser=user;
		if(user==null){
			System.out.println("1");
			entity.setCode(1);
			entity.setMessage("用户名或密码错误");
			entity.setData(null);
		}
		else {
			System.out.println("0");
			System.out.println(uuser.toString());
			entity.setCode(0);
			entity.setMessage("登录成功");
			entity.setData(uuser);
		}
		try {
			PrintWriter out=response.getWriter();
			System.out.println("返回客户端:    "+gson.toJson(entity));
			out.print(gson.toJson(entity));
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
