package com.project.ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AdminImpl implements Admin {
	Scanner sc;
	Connection con;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	DataBaseCon dbcon = new DataBaseCon();

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
				if (adminUser.equals(rs.getString(1)) && adminPass.equals(rs.getString(2)))
					// System.out.println("Admin Login Successfull");
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
	public void addProductItem() {
		try (Connection conn = dbcon.getConnection()) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Product Id>> ");
			int id = scanner.nextInt();
			scanner.nextLine(); // Consume newline
			System.out.print("Product Name>> ");
			String name = scanner.nextLine();
			System.out.print("Product Description>> ");
			String description = scanner.nextLine();
			System.out.print("Price>> ");
			double price = scanner.nextDouble();
			System.out.print("Quantity>> ");
			int quantity = scanner.nextInt();

			String query = "INSERT INTO product (id, name, discription, price, quantity) VALUES (?, ?, ?, ?, ?)";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setInt(1, id);
				stmt.setString(2, name);
				stmt.setString(3, description);
				stmt.setDouble(4, price);
				stmt.setInt(5, quantity);
				stmt.executeUpdate();
				System.out.println("Product added successfully.");
			}
		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
		}
	}

	public int getTotal(String uname) {
		int total = 0;
		try (Connection con = dbcon.getConnection()) {
			PreparedStatement ps;

			int price = 0;
			int qty = 0;

			ps = con.prepareStatement("select * from cart where user_name=?");
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				price = rs.getInt(6);
				qty = rs.getInt(7);
				total = total + (price * qty);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public void calculateBill() {
		System.out.println("Enter user name>>>");
		sc = new Scanner(System.in);
		String uname = sc.next();
		AdminImpl adminimpl = new AdminImpl();
		int total = adminimpl.getTotal(uname);
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
		int amount=getTotal(uname);
		System.out.println("Cart Total is>>>"+amount);
	}

	@Override
	public void checkQauntity() throws SQLException {
		try {
			System.out.println("Enter the product id to check the quantity>>");
			sc = new Scanner(System.in);
			int pid = sc.nextInt();
			con = dbcon.getConnection();
			pst = con.prepareStatement("select quantity from product where id=?");
			pst.setInt(1, pid);
			rs = pst.executeQuery();
			while (rs.next()) {
				// System.out.println("Product ID>>>" + rs.getInt(1));
				System.out.println("Quantity>>>" + rs.getInt(1));

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
	public void checkRegisteredUser() throws SQLException {
		try {
			String query = "select * from user";
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
	public void checkUserHistory() {
		try {

			System.out.println("Enter the user id");
			int uid = sc.nextInt();
			con = dbcon.getConnection();
			pst = con.prepareStatement(
					"select purchase_id,product_id,product_name,quantity,price from userPurchase where user_id=?");
			pst.setInt(1, uid);
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
