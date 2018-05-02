package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;

/**
 * Servlet implementation class Change
 */
@WebServlet("/Change")
public class Change extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		   PrintWriter out=response.getWriter();
		   out.println("<center><h1 style=\"color:red; align:center;\">CHANDIGARH <span style=\"color:black\"> UNIVERSITY\r\n" + 
					"			</span><br/><span style=\"color:black\">VISITOR MANAGEMENT SYSTEM</span> \r\n" + 
					"		</h1>\r\n" + 
					"		<br/><br/>");
		   out.println("<h1> Today's List</h1><br/>");
		   String s=request.getParameter("newdate");
		   String s1=s.substring(0,s.indexOf('-'));
			String s2=s.substring(s.indexOf('-')+1,s.lastIndexOf('-'));
			String s3=s.substring(s.lastIndexOf('-')+1,s.length());
			
			ServletConfig conf=getServletConfig();
			ServletContext cxt=getServletContext();
			String uid=cxt.getInitParameter("uid");
			try {
			    Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javabasic","root","1234");
				Statement st1=con.createStatement();
				st1.executeUpdate("update cuvms set visitdate='"+s+"' where uid='"+uid+"'");
				response.sendRedirect("Homepage.html");
				con.close();
			   }
			   catch(Exception e)
			   {
				   out.print("Study more.");
			   }
			
	}

}
