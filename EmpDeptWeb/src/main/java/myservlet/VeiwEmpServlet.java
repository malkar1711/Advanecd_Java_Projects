package myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/viewemp")
public class VeiwEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con ;

	
	public void init(ServletConfig config) throws ServletException {
		con = (Connection)config.getServletContext().getAttribute("jdbccon");
		System.out.println("XXXXXXXXXX");
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = con.prepareStatement("select * from emp where deptno = ?");
			ps.setInt(1, deptno);
			rs = ps.executeQuery();
			while(rs.next())
			{
				out.print("rs.getString(2)");
			}
			
		}catch(Exception e)
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
