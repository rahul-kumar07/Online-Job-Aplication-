package com.nt;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadParameters;


public class RegisterServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		String resumePath="F:/store/resumes";
		String photoPath="F:/store/photo";
		PrintWriter out= res.getWriter();
		try {
			//get special request object given by java zoom api
			MultipartFormDataRequest nreq=new MultipartFormDataRequest(req);
			int eId=Integer.parseInt(nreq.getParameter("tid"));
			String eName=nreq.getParameter("tnmae");
			String eAdd=nreq.getParameter("tadd");
			//setting to upload resume
			
			UploadBean upb=new UploadBean();
			upb.setFolderstore(resumePath);
			upb.setOverwrite(false);
			upb.store(nreq,"tresume");
			
			//setting to upload photo
			
			upb.setFolderstore(photoPath);
			upb.setOverwrite(false);
			upb.store(nreq,"tphoto");//completes file uploading
			
			//get file name of the uploaded file
			
			Vector history=upb.getHistory();
			
		ArrayList<String>filesName=new ArrayList<String>();
		for(int i =0;i<history.size();i++) {
			UploadParameters up= (UploadParameters) history.elementAt(i);
			filesName.add(up.getFilename());
			
		}
		
		System.out.println("resume"+filesName.get(0));
		System.out.println("photo"+filesName.get(1));
		
		System.out.println("ename is===="+eName);
		//store the path of the uploaded file to F:\store folder
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		PreparedStatement ps=con.prepareStatement("insert into employeereg values(?,?,?,?,?)");
		ps.setInt(1,eId);
		ps.setString(2,eName);
		ps.setString(3,eAdd);
		ps.setString(4,resumePath+"/"+filesName.get(0));
		ps.setString(5,photoPath+"/"+filesName.get(1));
		
		int i=ps.executeUpdate();
		if(i==1)
			out.println("successfully upload and stored in database");
		else
			out.println("Failed in uploading");
		}//try
		
		catch(Exception e) {
			out.println(e);
		}//catch
		
	}//doPost
}//class
