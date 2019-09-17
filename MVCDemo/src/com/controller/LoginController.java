package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ConnectionUtil;
import com.model.LoginBean;

public class LoginController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter outPrintWriter = resp.getWriter();
		String nameString = req.getParameter("username");
		String ageString = req.getParameter("age");
		String passwordString = req.getParameter("password");
		
		LoginBean bean=new LoginBean();  
        bean.setUsername(nameString); 
        bean.setAge(ageString);
        bean.setPassword(passwordString);  
        
        
        //get details from model class
        String nm= bean.getUsername();
        String ag= bean.getAge();
        
        HashMap map = new HashMap();
        map.put("username",nm);
        map.put("age",ag);
        
        HttpSession session = req.getSession();
        
//        session.setAttribute("bean",bean);  
//        session.setAttribute("uname", nameString);
        
        
        session.setAttribute("details", map);
        req.setAttribute("bean", bean);
          
     // call the database layer
        
        try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cccswnvlzm9u.us-east-2.rds.amazonaws.com:1521:orcl","admin","12345678");
		connection.setAutoCommit(false);
        String query ="insert into adminlogin(name,password) values(?,?)";
//        Connection connection = ConnectionUtil.getConnection();
        
        
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, nameString);
			statement.setString(2, passwordString);
			ResultSet resultSet = statement.executeQuery();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
        if(nameString.equals("admin") && passwordString.equals("admin")){  
            RequestDispatcher rd=req.getRequestDispatcher("welcome.jsp");  
            rd.forward(req, resp);  
        }  
        else{  
            RequestDispatcher rd=req.getRequestDispatcher("welcome.jsp");  
            rd.forward(req, resp);  
        }  
	}

	
}
