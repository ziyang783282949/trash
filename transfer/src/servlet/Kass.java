package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import model.BaseEntity;
import model.UserInfo;

public class Kass extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Kass() {
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
		
		PrintWriter writer=response.getWriter();
		request.setCharacterEncoding("utf-8");
		//String name=request.getParameter("name");
		//String pass=request.getParameter("password");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		SmartUpload su=new SmartUpload();
		su.initialize(this.getServletConfig(), request, response);
		try {
			su.upload();
			su.save("G:\\work\\upload");
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fuck=su.getRequest().getParameter("data");
		fuck=fuck.replace("\\","");
		fuck=fuck.substring(1,fuck.length()-1);
		System.out.println(fuck);
		
		String aString="{\"check\":\"1\"}";
		BaseEntity<UserInfo> baseEntity=new BaseEntity<UserInfo>();
		baseEntity.setCode(1);
		Gson gson=new Gson();
		System.out.println(gson.toJson(baseEntity).toString());
		writer.write(gson.toJson(baseEntity).toString());
		writer.flush();
		writer.close();
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
