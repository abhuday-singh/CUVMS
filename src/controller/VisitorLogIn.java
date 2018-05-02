package controller;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VisitorLogIn
 */
@WebServlet("/VisitorLogIn")
public class VisitorLogIn extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		   response.setContentType("text/html");
		   PrintWriter out=response.getWriter();
		   int uid=Integer.parseInt(request.getParameter("uid"));
		   String dob;//=request.getParameter("dob");
		   String ss=request.getParameter("dob");
		   String s1=ss.substring(0,ss.indexOf('-'));
		   String s2=ss.substring(ss.indexOf('-')+1,ss.lastIndexOf('-'));
		   String s3=ss.substring(ss.lastIndexOf('-')+1,ss.length());
			dob=s3+"-"+s2+"-"+s1;
		   System.out.println(ss);
		   boolean flag=false;

		   try 																									//
		   {
			    Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javabasic","root","1234");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select uid from cuvms");
				while(rs.next())
				{
					if(rs.getInt(1)==uid)
					{
						Statement st2=con.createStatement();
						ResultSet rs2=st2.executeQuery("select dob from cuvms");
						while(rs2.next())
						{
							if(rs2.getString(1).equals(ss))
								flag=true;
						}
						
					}
				}
		   	}
		   catch(Exception e)
		   {
			   System.out.println("Error on level 1");
		   }
		   
		   if(flag==true)
		   {

				out.println("<center><h1 style=\"color:red; align:center;\">CHANDIGARH <span style=\"color:black\"> UNIVERSITY\r\n" + 
						"			</span><br/><span style=\"color:black\">VISITOR MANAGEMENT SYSTEM</span> \r\n" + 
						"		</h1>\r\n" + 
						"		<br/><br/>");	
				out.println("<h1> Visitor Details:</h1><br/> <table border=1> ");
				out.print("<tr><th>UID</th><th>Name</th> <th>Gender</th> <th>Date Of Birth </th> <th>E-mail</th> <th>Address</th>");
				out.print("<th>Mobile No.</th> <th>Department</th> <th>Purpose</th> <th>Visiting Date</th></tr>");
				
				try 
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javabasic","root","1234");
					Statement st1=con.createStatement();
					ResultSet rs1=st1.executeQuery("select * from cuvms where uid="+uid);
					
					while(rs1.next())
					{
						out.print("<tr><td>"+rs1.getInt(11)+"</td><td>"+rs1.getString(2)+"</td><td>"+rs1.getString(3)+"</td><td>"+rs1.getString(4)+"</td><td>"+rs1.getString(5)+"</td><td>");
						out.print(rs1.getString(6)+"</td><td>"+rs1.getString(7)+"</td><td>"+rs1.getString(8)+"</td><td>"+rs1.getString(9)+"</td><td>"+rs1.getString(10)+"</td>");
						out.print("</tr></center>");
					}
					
					 out.println("<br/> <center>" +
							   "<form action=\"Change\" method=\"post\">"+				//Department Wise List
							   "<input type=\"date\" name=\"newdate\">"+
							   "<input type=\"submit\" value=\"Change Date\" onclick=\"alert('Visiting date changed successfully.')\" >"+"</form>"+
							    "</center><br/>");
					   

				   con.close();
				}
				catch(Exception e)		
				{
				       out.print("Error on level 2");
				}
				
		   }
			else
			{   
			   
					response.sendRedirect("Login.html");
			}		
	  }   	}

