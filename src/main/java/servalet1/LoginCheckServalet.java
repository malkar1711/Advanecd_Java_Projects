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


@WebServlet("/LoginCheckServalet")
public class LoginCheckServalet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	@Override
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
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String ename = request.getParameter("ename");
		String job = request.getParameter("job");
		
		
		PreparedStatement ps = null;
		ResultSet rs= null;
		try
		{
			ps = con.prepareStatement("select * from emp where ename=? and job = ?");
			ps.setString(1, ename);
			ps.setString(2, job);
			rs = ps.executeQuery();   
			if(rs.next())   
			{
				
				if(rs.getString(2).equals(ename) && rs.getString(3).equals(job))
				{
					out.print("<h3> Success login </h3>");
				}
			}
			else
			{
				out.print("<h3> Failed login </h3>");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	
}
    


