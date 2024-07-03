package com.project.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo {
	public static void main(String args[]) {
		DataBaseCon dbcon=new DataBaseCon();
		Connection con=dbcon.getConnection();
		Product p=new Product();
		try {
			PreparedStatement pst=con.prepareStatement("select * from product");
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				p.setProductid(rs.getInt(1));
				System.out.println("Product Id>>>"+p.getProductid());
				p.setProductName(rs.getString(2));
				System.out.println("Product Name>>>"+p.getProductName());
				p.setProductDiscription(rs.getString(3));
				System.out.println("Product Discription>>>"+p.getProductDiscription());
				p.setQuantity(rs.getInt(5));
				System.out.println("Product quantity>>>"+p.getQuantity());
				p.setPrice(rs.getFloat(4));
				System.out.println("Product Price>>"+p.getPrice());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

}
