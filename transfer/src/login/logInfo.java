package login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		/*String userName=new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
		String passWord=new String(request.getParameter("password").getBytes("ISO-8859-1"),"UTF-8");
		if(userName!=null && !"".equals(userName) && passWord!=null && !"".equals(passWord)){
			
		}
		System.out.println("–’√˚:"+userName+"√‹¬Î"+passWord);*/
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
		//String name=new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
		//out.print(name);
		//System.out.println("zy");
		String line="";
		StringBuilder builder=new StringBuilder();
		BufferedReader br=request.getReader();
		while((line=br.readLine())!=null){
			builder.append(line);
		}
		//response.getWriter().write(builder.toString());
		System.out.println(builder.toString());
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
