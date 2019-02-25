package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		Cookie ck[]=req.getCookies();
		if(ck!=null) {
			String name =ck[0].getValue();
			String pass=ck[1].getValue();
			if(!name.equals("")||name!=null) {
				out.println("Welcome to Profile<b>");
				out.println("<br>Welcome"+name);
				try {
					Connection con=DBConnection.getCon();
					PreparedStatement ps=con.prepareStatement("select * from cookiereg");
					ResultSet rs=ps.executeQuery();
					if(rs.next()) {
						out.println("<br>First Name"+rs.getString(3));
						out.println("<br>Last Name"+rs.getString(4));
						out.println("<br>Address:"+rs.getString(5));
						out.println("<br>Phone No."+rs.getString(6));
						out.println("<br>MailId:"+rs.getString(7));
					}
				}
				catch(Exception e) {}
			}
			
		}
		else {
			out.println(" please Login first");
			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.include(req, res);
		}
		out.close();
	}

}
