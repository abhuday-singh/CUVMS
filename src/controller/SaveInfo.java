package controller;

import java.io.*;
import java.sql.*;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.*;
@WebServlet("/SaveInfo")
public class SaveInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 response.setContentType("text/html");
		   PrintWriter out=response.getWriter();
		   String name="",gen="",dob="",email="",add="",mob="",dept="",purpose="",vdate="";
		 
		   name=request.getParameter("name");
		   gen=request.getParameter("gender");
		   System.out.println(gen);
		   String s=request.getParameter("dob");
			String s1=s.substring(0,s.indexOf('-'));
			String s2=s.substring(s.indexOf('-')+1,s.lastIndexOf('-'));
			String s3=s.substring(s.lastIndexOf('-')+1,s.length());
			dob=s3+"-"+s2+"-"+s1;
			System.out.println(s); 
		  
			System.out.println("1 clear");
		   email=request.getParameter("email");
		   add=request.getParameter("address");
		   mob=request.getParameter("mobno");
		   dept=request.getParameter("department");
		   purpose=request.getParameter("purpose");
		   
		   String ss=request.getParameter("vdate");
			s1=ss.substring(0,ss.indexOf('-'));
			s2=ss.substring(ss.indexOf('-')+1,ss.lastIndexOf('-'));
			s3=ss.substring(ss.lastIndexOf('-')+1,ss.length());
			vdate=s3+"-"+s2+"-"+s1;
			System.out.println(ss); 
		   System.out.println("2 clear");
		   System.out.println(dept);
		  try {
			   Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javabasic","root","1234");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select uid from cuvms");
				System.out.println("3 clear");
				Random r=new Random();
				long n=0;boolean flag=true;
				while(flag)
				{	
				  flag=false;
				  n=r.nextInt(9999)+1111;
				  if(n>=1111 && n<=9999)
				  {
					while(rs.next())
					{
						if(rs.getInt(1)==n)
						{ flag=true; break;}
						else
						{
							continue;
						}
					}
				  }			 
				}
				
				String query="insert into cuvms (Name,Gendr,DOB,Email,Address,MobNO,Department,Purpose,VisitDate,uid) values('"+name+"', '"+gen+"', '"+s+"', '"+email+"', '"+add+"', '"+mob+"', '"+dept+"', '"+purpose+"', '"+ss+"', "+n+")";
				System.out.println(query);
				st.executeUpdate(query);
				con.close();
				response.sendRedirect("Homepage.html");
				
		   }
		   catch(Exception e)
		   {
			   out.print("Study more.");
		   }
		
	}

}
