package com.project.ecommerce;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImpl implements User {
	Scanner sc = null;
	int pid;
	int qty;
	String view_cart;
	String first_name;
	String last_name;
	String user_name;
	String password;
	String city;
	String mail_id;
	String mobile_no;
	Connection con=null;
	DataBaseCon dbcon = new DataBaseCon();
	Product product = new Product();
	
	public void getUserInformation(String firstName, String lastName, String userName, String password, String city,
			String mail_id, String moble_no) {
		Connection con = dbcon.getConnection();
	}

	@Override
	public void getUserregistrationDetails(String firstname, String lastname, String username, String password,
			String city, String emailid, String mobilenumber) {
		try {

			con = dbcon.getConnection();
			// Create PrepareStatement
			PreparedStatement ps = con.prepareStatement(
					"insert into user(firstname,lastname,username,password,city,mail_id,mobile_number)values(?,?,?,?,?,?,?)");
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setString(5, city);
			ps.setString(6, emailid);
			ps.setString(7, mobilenumber);
			// Submit the SQL Statement to database
			int a = ps.executeUpdate();
			System.out.println("Your Registration Successfull!!!");
			// close resources
			con.close();
			ps.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// take input from user
	public void getUserInput() {
		System.out.println("Enter the firstname>>>");
		Scanner sc = new Scanner(System.in);
		String fname = sc.next();
		System.out.println("Enter the lastname>>>");
		String lname = sc.next();
		System.out.println("Enter the username>>>");
		String uname = sc.next();
		System.out.println("Enter the password>>>");
		String pword = sc.next();
		System.out.println("Enter the city>>>");
		String city = sc.next();
		System.out.println("Enter the email id>>>");
		String email = sc.next();
		System.out.println("Enter the mobile number>>>");
		String mbnumber = sc.next();
		if (Validations.isValidPhoneNo(mbnumber) && Validations.isValidEmail(email)) {
			// set all data to getUserregistrationDetails Method
			UserImpl userimpl = new UserImpl();
			userimpl.getUserregistrationDetails(fname, lname, uname, pword, city, email, mbnumber);
		} else {
			System.out.println("Mobile number Or Email not valid!!");
			System.exit(0);
		}
	}

	@Override
	public void getUserLoginDetails() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the user name");
			String uname = scanner.next();
			System.out.println("Enter the Password");
			String pword = scanner.next();

			// common connection code present into DataBaseCon class
			// create object and call getconnection() method
			 con = dbcon.getConnection();
			// use/create prepareStatement
			PreparedStatement ps = con.prepareStatement("select * from user where username=?");
			ps.setString(1, uname);
			// Submit the SQL Statement to database
			ResultSet rs = ps.executeQuery();
			//use while loop to check username & password is present into table

			while (rs.next()) {
				if (uname.equals(rs.getString(4)) && pword.equals(rs.getString(5))) {
					// user will login the system
					System.out.println("Login Successfull!!");
				} 
				else {
					System.out.println("Login Failed!!!");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public void viewProduct() {

		// common connection code present into user1 class
		// create object and call getconnection method from DataBaseCon class
		try {
			Connection con = dbcon.getConnection();
			// use/create prepareStatement
			PreparedStatement ps = con.prepareStatement("select * from mini_project_group_e.product ORDER BY name");
			// use result set to view product List
			ResultSet rs = ps.executeQuery();
			// while loop
			while (rs.next()) {
				System.out.println("Product id>>" + rs.getInt(1));
				System.out.println("Product Name>>" + rs.getString(2));
				System.out.println("Product Description>>" + rs.getString(3));
				System.out.println("Product Price>>" + rs.getInt(4));
				System.out.println("Product Quantity>>" + rs.getInt(5));

				/*
				 * al.add(rs.getInt(1)); al.add(rs.getString(2)); al.add(rs.getString(3));
				 * al.add(rs.getInt(4)); al.add(rs.getInt(5));
				 */
			}
			/*
			 * Collections.sort(al); Iterator itr=al.iterator(); while(itr.hasNext()) {
			 * System.out.println(itr.next()); }
			 */
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void getBuyProduct() {
		System.out.println("Enter Product id>>");
		sc = new Scanner(System.in);
		pid = sc.nextInt();
		System.out.println("Enter the Quantity>>");
		qty = sc.nextInt();
		System.out.println("Enter your user Name to add the item to the cart");
		String uname=sc.next();
		buyProduct(uname,pid, qty);
	}

	@Override
	public void buyProduct(String uname,int pid, int qty) {// add to cart
		try {
			con=dbcon.getConnection();
			int prod_id=0;
			String prod_name=null;
			String prod_dis=null;
			int prod_price=0;
			int prod_qty=0;
			PreparedStatement ps=con.prepareStatement("select id,name,discription,price,quantity from product where id=?");
			ps.setInt(1, pid);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				prod_id=rs.getInt(1);
				prod_name=rs.getString(2);
				prod_dis=rs.getString(3);
				prod_price=rs.getInt(4);
				prod_qty=rs.getInt(5);
			}
			if(prod_qty>=qty) {
			PreparedStatement pst=con.prepareStatement("insert into cart(user_name,product_id,product_name,product_discription,price,quantity)values(?,?,?,?,?,?)");
			pst.setString(1, uname);
			pst.setInt(2, prod_id);
			pst.setString(3, prod_name);
			pst.setString(4, prod_dis);
			pst.setInt(5, prod_price);
			pst.setInt(6, qty);
			int a=pst.executeUpdate();
			System.out.println("Do you want to view cart?");
			Scanner scanner = new Scanner(System.in);
			String view_cart = scanner.next();
			if (view_cart.equalsIgnoreCase("yes")) {
				UserImpl userimpl=new UserImpl();
				userimpl.viewCart();
			}
			else {
				System.out.println("Thank You!!");
				System.exit(0);
			}
			}else {
				System.out.println("Prouct quantity is not available please select less quantity!!!");
			}
			/*DataBaseCon dbcon = new DataBaseCon();
			con = dbcon.getConnection();
			PreparedStatement ps1 = con.prepareStatement("select * from cart where user_name=?");
			ps1.setString(1, uname);
			ResultSet rst = ps1.executeQuery();
			while (rs.next()) {
				System.out.println("product Id>>" + rs.getInt(2));
				System.out.println("product Name>>" + rs.getString(3));
				System.out.println("product Description>>" + rs.getString(4));
				System.out.println("product Price>>" + rs.getInt(5));
				System.out.println("product Quantity>>" + rs.getInt(6));
			}
			con.close();
			ps.close();
			rs.close();
		} */
	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void viewCart() {
		con = dbcon.getConnection();
		PreparedStatement ps;
		try {
			System.out.println("Enter user name>>>");
			sc=new Scanner(System.in);
			String uname=sc.next();
			ps = con.prepareStatement("select * from cart where user_name=?");
			ps.setString(1,uname);
			ResultSet rs = ps.executeQuery();
				System.out.println("Your Cart contain:");
				while (rs.next()) {
					System.out.println("Item no.>>>" + rs.getInt(1));
					System.out.println("Product id.>>>" + rs.getInt(3));
					System.out.println("Product name.>>>" + rs.getString(4));
					System.out.println("product Discription>>>" + rs.getString(5));
					System.out.println("Quantity" + rs.getInt(7));
					System.out.println("Price >>>" + rs.getInt(6));
			} 		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void purchaseTheItem(String uname) {
		
			AdminImpl admin=new AdminImpl();
			int total=admin.calculateBill();
			
			 if(total>0)
			 {
				//System.out.println("Your total is>>"+total);
				System.out.println("Purchase finalized. Thank you for shopping with us!");
			 }
			 else {
				 System.out.println("Your cart is empty!!!");
			 }
		      //System.out.println("Total amount to pay: " + totalAmount);
		UserImpl userimpl=new UserImpl();
		userimpl.savePurchaseHistory();
	}
	@Override
	public void buyProduct() {
		// TODO Auto-generated method stub

	}

	@Override
	public void savePurchaseHistory() {
	/*	con=dbcon.getConnection();
		try {

			PreparedStatement pst=con.prepareStatement("select * from cart where user_id=?");
					"insert into userpurchase(product_id,product_name,quantity,price,user_id) values(?,?,?,?,?)");
			pst.setInt(1,pid);
			pst.setString(2, name);
			pst.setInt(3, qty);
			pst.setInt(4,price);
			pst.setInt(5,uid);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

}