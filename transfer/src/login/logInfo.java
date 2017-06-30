package login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.userInfo;
import net.sf.json.JSONObject;

public class logInfo extends HttpServlet {
	private String message;
	/**
	 * Constructor of the object.
	 */
	public logInfo() {
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

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String line="";
		StringBuilder builder=new StringBuilder();
		BufferedReader br=request.getReader();
		while((line=br.readLine())!=null){
			builder.append(line);
		}
		String content=URLDecoder.decode(builder.toString(), "utf-8");
		
		userInfo user=new userInfo();
		user=JsonToUser(content);
		System.out.println(user);
	}
	public userInfo JsonToUser(String str) {
		JSONObject userInfo=JSONObject.fromObject(str);
		JSONObject userJson=userInfo.getJSONObject("userInfo");
		System.out.println(userJson.get("username"));
		userInfo user=new userInfo();
		user.setUsername(userJson.get("username").toString());
		user.setPassword(userJson.get("password").toString());
		return user;
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
