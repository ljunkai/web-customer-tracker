package com.testJdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class testJdbc
 */
@WebServlet("/testJdbc")
public class testJdbc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//set up connection variables
		String user = "teamIMS";
		String pass = "Password!";
		
		String jdbcUrl = "jdbc:sqlserver://127.0.0.1:1433;databaseName=web_customer_tracker";
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		
		//getting the connection
		
		try {
			
			PrintWriter out = response.getWriter();
			
			out.println("Connecting to database: " + jdbcUrl);
			
			Class.forName(driver);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			out.println("Success!!");
			
			myConn.close();
			
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			throw new ServletException(ex);
		}
		
		
	}

}
