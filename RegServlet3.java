package test;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import test.RegBean;

public class RegServlet3 extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		PrintWriter pw=res.getWriter();
		res.setContentType("ytext/html");
		ServletContext sct=req.getServletContext();
		RegBean rb=(RegBean) sct.getAttribute("beanref");
		String phNo=req.getParameter("phno");
		rb.setPhNo(phNo);
		String mailId=req.getParameter("mailid");
		rb.setMailId(mailId);
		RequestDispatcher rd=req.getRequestDispatcher("final.html");
		rd.forward(req, res);
	}

}