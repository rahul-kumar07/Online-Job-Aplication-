package test;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import test.RegBean;

public class RegServlet1 extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		PrintWriter pw=res.getWriter();
		res.setContentType("ytext/html");
		RegBean rb=new RegBean();
		ServletContext sct=req.getServletContext();
		sct.setAttribute("beanref", rb);
		String uName=req.getParameter("username");
		rb.setuName(uName);
		String pWord=req.getParameter("password");
		rb.setpWord(pWord);
		RequestDispatcher rd=req.getRequestDispatcher("Register2.html");
		rd.forward(req, res);
	}

}
