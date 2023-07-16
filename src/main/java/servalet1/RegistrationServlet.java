package servalet1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;

	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbcUrl = "jdbc:mysql://localhost:3306/dac";
			con = DriverManager.getConnection(jdbcUrl, "root", "root");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		
	

	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	
		
		String empno = request.getParameter("empno");
		String ename = request.getParameter("ename");
		String job = request.getParameter("job");
		String mgr = request.getParameter("mgr");
		String deptno = request.getParameter("deptno");
		
		PreparedStatement ps = null;
		
		try
		{
			ps = con.prepareStatement("insert into emp(empno,ename,job,mgr,deptno) values(?,?,?,?,?)");
			ps.setString(1, empno);
			ps.setString(2, ename);
			ps.setString(3, job);
			ps.setString(4, mgr);
			ps.setString(5, deptno);
			
			int n=ps.executeUpdate();
			if(n==1)
			{
				out.print("<h3> Registration Successful</h3>");
			}
			else
			{
				out.print("<h3> Registration Unsuccessful</h3>");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				
				ps.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
			  
	}

}
