package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		try {
			String path = service.login(request,response);
			returnClient(path, request, response);
			System.out.println(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void returnClient(String path, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		try {
			JSONObject js = new JSONObject();
			PrintWriter out=response.getWriter();
			if (path.equals("µÇÂ¼Ê§°Ü")) {
				js.put("check", URLEncoder.encode("fail", "utf-8"));
			}
			if (path.equals("µÇÂ¼³É¹¦")) {
				js.put("check", URLEncoder.encode("success", "utf-8"));
			}
			out.print(js.toString());
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
