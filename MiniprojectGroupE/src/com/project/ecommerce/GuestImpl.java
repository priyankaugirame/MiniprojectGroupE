package com.project.ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GuestImpl implements Guest{

	@Override
	public void viewProduct() {
		Statement st=null;
		ResultSet rs=null;
		
		try {
			DataBaseCon dbcon=new DataBaseCon();
			Connection con=dbcon.getConnection();
			st=con.createStatement();
			rs=st.executeQuery("select * from product");
			while(rs.next())
			{
				System.out.println("Product id>> "+rs.getInt(1));
				System.out.println("Product name>> "+rs.getString(2));
				System.out.println("Product Discription>> "+rs.getString(3));
				System.out.println("Product Quantity>> "+rs.getInt(4));
				System.out.println("Product Price>> "+rs.getInt(5));
			}
			System.out.println("You cant buy any product!!! \n Want to buy something Register First!!!");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
