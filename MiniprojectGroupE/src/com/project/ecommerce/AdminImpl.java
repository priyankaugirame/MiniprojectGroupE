package com.project.ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AdminImpl implements Admin {
	Scanner sc; // Scanner object for accepting inputs from user
	Connection con; // Connection class object for Database connection
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	DataBaseCon dbcon = new DataBaseCon(); // Craeting the object of DataBaseCon class which contain common code for
											// connection to the database

	@Override
	public boolean getAdminLoginDetails() { // This method is used to get login details of admin
		int flag = 0;
		sc = new Scanner(System.in);
		System.out.println("Enter User name>> ");
		String adminUser = sc.next(); // admin will enter his username
		System.out.println("Enter Password>> ");
		String adminPass = sc.next(); // admin will enter his password
		try {
			con = dbcon.getConnection();
			pst = con.prepareStatement("select username,password from admin");
			rs = pst.executeQuery();
			while (rs.next()) {
				if (adminUser.equals(rs.getString(1)) && adminPass.equals(rs.getString(2))) // If the username and
																							// password matches with
																							// username and password in
																							// admin table then admin
																							// can lofin to the system
					flag = 1;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag == 1)
			return true;
		else
			return false;

	}

	@Override
	public void addProductItem() { // Method to add products into the product table
		try (Connection conn = dbcon.getConnection()) { // connection the database
			sc = new Scanner(System.in);// Scanner Object
			System.out.print("Product Id>> ");
			int id = sc.nextInt(); // accepting the product id
			sc.nextLine(); // Consume newline
			System.out.print("Product Name>> ");
			String name = sc.nextLine(); // accepting the product name
			System.out.print("Product Description>> ");
			String description = sc.nextLine(); // accepting the product description
			System.out.print("Price>> ");
			int price = sc.nextInt(); // accepting the product price
			System.out.print("Quantity>> ");
			int quantity = sc.nextInt(); // accepting the product quantity

			String query = "INSERT INTO product (id, name, discription, price, quantity) VALUES (?, ?, ?, ?, ?)"; // query
																													// to
																													// insert
																													// the
																													// product
																													// into
																													// the
																													// table
			try (PreparedStatement stmt = conn.prepareStatement(query)) { // prepare the SQL statement
				stmt.setInt(1, id);
				stmt.setString(2, name);
				stmt.setString(3, description);
				stmt.setDouble(4, price);
				stmt.setInt(5, quantity);
				stmt.executeUpdate(); // Submitting the SQL to the database
				System.out.println("Product added successfully.");
			}
		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
		}
	}

	public int getTotal(String uname) {// This method will calculate the total
		int total = 0;
		try (Connection con = dbcon.getConnection()) { // Establishing the connection with database
			PreparedStatement ps;
			int price = 0;
			int qty = 0;
			ps = con.prepareStatement("select * from cart where user_name=?"); // Getting the cart items of user
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery(); // Sumitting the query to the database
			while (rs.next()) {
				price = rs.getInt(6); // getting the price from cart table
				qty = rs.getInt(7); // getting ht quantity from cart table
				total = total + (price * qty); // calculating the total
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total; // return this total to calculate the bill
	}

	@Override
	public void calculateBill() {
		System.out.println("Enter user name>>>");
		sc = new Scanner(System.in);
		String uname = sc.next();
		AdminImpl adminimpl = new AdminImpl();
		int total = adminimpl.getTotal(uname); // getting the total from getTotal method
		if (total > 0) {
			System.out.println("Your Cart total is>>" + total);

		} else {
			System.out.println("Your cart is empty!!!");
		}
	}

	@Override
	public void displayAmount() {
		System.out.println("Enter user name>>>");
		sc = new Scanner(System.in);
		String uname = sc.next();
		int amount = getTotal(uname); // Getting the Total from getTotal method amd display it to the user
		System.out.println("Cart Total is>>>" + amount);
	}

	@Override
	public void checkQauntity() throws SQLException { // Method for checking the quantity of product
		try (Connection con = dbcon.getConnection()) {
			System.out.println("Enter the product id to check the quantity>>");
			sc = new Scanner(System.in);
			int pid = sc.nextInt();//// accepting the product_id
			pst = con.prepareStatement("select quantity from product where id=?"); // fetching the quantity
			pst.setInt(1, pid);
			rs = pst.executeQuery(); // submitting sql query to the database
			while (rs.next()) {
				System.out.println("Quantity>>>" + rs.getInt(1));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // closing the resources in finally block
			con.close();
			pst.close();
			rs.close();

		}
	}

	@Override
	public void checkRegisteredUser() throws SQLException { // admin can check the registered user
		try {
			String query = "select * from user"; // getting the user details
			con = dbcon.getConnection();
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				System.out.println("User Id>>> " + rs.getInt(1));
				System.out.println("First Name>>> " + rs.getString(2));
				System.out.println("Last Name>>> " + rs.getString(3));
				System.out.println("User Name>>> " + rs.getString(4));
				System.out.println("Password>>> " + rs.getString(5));
				System.out.println("city>>> " + rs.getString(6));
				System.out.println("Mail Id>>> " + rs.getString(7));
				System.out.println("Mobile no.>>> " + rs.getString(8));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			pst.close();
			rs.close();
		}

	}

	@Override
	public void checkUserHistory() { // admin can check perticular user history
		try {
			String uname = null;
			System.out.println("Enter the user id");
			int uid = sc.nextInt();
			con = dbcon.getConnection();
			pst = con.prepareStatement("select user_name from user where uid=?");
			pst.setInt(1, uid);
			rs = pst.executeQuery();
			while (rs.next()) {
				uname = rs.getString("username");
			}
			pst = con.prepareStatement("select * from userPurchase where user_name=?");
			pst.setString(1, uname);
			rs = pst.executeQuery();
			while (rs.next()) {
				System.out.println("Product id>> " + rs.getInt(1));
				System.out.println("Product name>> " + rs.getString(2));
				System.out.println("Quantity>> " + rs.getInt(3));
				System.out.println("Price>>>" + rs.getInt(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
