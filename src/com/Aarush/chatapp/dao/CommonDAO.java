package com.Aarush.chatapp.dao;

import static com.Aarush.chatapp.utils.ConfigReader.getValue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Throw early and catch later
public interface CommonDAO {
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		//step-1 Load Driver
		Class.forName(getValue("DRIVER"));
		//step-2 Making a connection
		final String CONNECTION_STRING =getValue("CONNECTION_URL");
		final String USER_ID =getValue("USER_ID");
		final String PASSWORD =getValue("PASSWORD");
		Connection con = DriverManager.getConnection(CONNECTION_STRING,USER_ID,PASSWORD);
		if(con != null) {
			System.out.println("Connection Created....");
			//con.close();
		}
		return con;
	}
	
}


