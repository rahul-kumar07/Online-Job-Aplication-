package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisServlet extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		ServletContext sct=req.getServletContext();
		RegBean rb=(RegBean) sct.getAttribute("beanref");
		String sub=req.getParameter("preview");
		if(sub.equals("preview")){
			pw.println("UserName:"+rb.getuName()+"<br>");
			pw.print("Password:"+rb.getpWord()+"<br>");
			pw.println("First Nmae:"+rb.getfName()+"<br>");
			pw.println("Last Name:"+rb.getlName()+"<br>");
			pw.println("address"+rb.getAddr()+"<br>");
			pw.println("Phone No:"+rb.getPhNo()+"<br>");
			pw.println("Mail Id:"+rb.getMailId()+"<br>");
			
			RequestDispatcher rd=req.getRequestDispatcher("final.html");
			rd.forward(req, res);
		}
		else {
			try {
				Connection con=DBConnection.getCon();
				PreparedStatement ps=con.prepareStatement("insert into cookiereg values(?,?,?,?,?,?,?)");
				ps.setString(1,rb.getuName());
				ps.setString(2,rb.getpWord());
				ps.setString(3,rb.getfName());
				ps.setString(4, rb.getlName());
				ps.setString(5,rb.getAddr());
				ps.setString(6,rb.getPhNo());
				ps.setString(7,rb.getMailId());
				
				int k=ps.executeUpdate();
				if(k==1) {
					pw.println("user registration successfully");
					RequestDispatcher rd= req.getRequestDispatcher("login.html");
					rd.forward(req, res);
				}
			}
			catch(Exception e) {
				
			}
		}
	}

}
