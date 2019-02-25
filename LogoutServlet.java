package test;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;

public class LogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		Cookie ck1=new Cookie("name","");
		ck1.setMaxAge(0);
		res.addCookie(ck1);
		Cookie ck2=new Cookie("pass","");
		ck2.setMaxAge(0);
		res.addCookie(ck2);
		out.println("you are successfully loggedout");
	}

}
