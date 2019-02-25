package test;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import test.RegBean;

public class RegServlet2 extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		PrintWriter pw=res.getWriter();
		res.setContentType("ytext/html");
		ServletContext sct=req.getServletContext();
		RegBean rb=(RegBean) sct.getAttribute("beanref");
		String fName=req.getParameter("firstname");
		rb.setfName(fName);
		String lName=req.getParameter("lastname");
		rb.setlName(lName);
		String addr=req.getParameter("address");
		rb.setAddr(addr);
		RequestDispatcher rd=req.getRequestDispatcher("Register3.html");
		rd.forward(req, res);
	}

}
