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

/**
 * Servlet implementation class DeptWise
 */
@WebServlet("/DeptWise")
public class DeptWise extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("text/html");
		   PrintWriter out=response.getWriter();
		   out.println("<center><h1 style=\"color:red; align:center;\">CHANDIGARH <span style=\"color:black\"> UNIVERSITY\r\n" + 
					"			</span><br/><span style=\"color:black\">VISITOR MANAGEMENT SYSTEM</span> \r\n" + 
					"		</h1>\r\n" + 
					"		<br/><br/>");
		   out.println("<h1> Today's List</h1><br/>");
		   String dept=request.getParameter("department");
		   System.out.println(dept);
		   try {
			    Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javabasic","root","1234");
				Statement st1=con.createStatement();
				String Q="select * from cuvms where department='"+dept+"' and visitdate=curdate()";
				System.out.println(Q);
				ResultSet rs=st1.executeQuery(Q);
				
			//	if(!rs.next())
			//		out.print("<h3> No records found. </h3>");
				
				if(true)
				{
					out.print(" <table border=1> <tr><th>S.No</th> <th>Name</th> <th>Gender</th> <th>Date Of Birth </th> <th>E-mail</th> <th>Address</th>");
					out.print("<th>Mobile No.</th> <th>Department</th> <th>Purpose</th> <th>Visiting Date</th><th>UID</th></tr>");
					while(rs.next())
					{
						out.print("<tr><td>  "+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>");
						out.print(rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+"<td>"+rs.getInt(11)+"</td>");
						out.print("</tr></center>");
					}
					
			    }
				con.close();
				}
			   catch(Exception e)
			   {
				   out.print("Study more.");
			   }
		   
	}

}
