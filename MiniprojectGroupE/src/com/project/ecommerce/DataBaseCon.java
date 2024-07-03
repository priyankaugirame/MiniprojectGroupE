package com.project.ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseCon {
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:/mini_project_group_e?autoReconnect=true&useSSL=false", "root", "redhat");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
