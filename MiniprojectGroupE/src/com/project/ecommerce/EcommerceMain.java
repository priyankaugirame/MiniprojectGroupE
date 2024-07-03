package com.project.ecommerce;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Scanner;

public class EcommerceMain { // This is main class
	public static void main(String[] args) throws SQLException {
		ArrayList<String> userOperation = new ArrayList<String>(); // Creating arraylist to add user operations
		userOperation.add("1. User Registration");
		userOperation.add("2. User Login");
		userOperation.add("3. User View Product Item as Sorted Order");
		userOperation.add("4. Buy Product");
		userOperation.add("5. View Cart");
		userOperation.add("6. Purchase the iem");
		ArrayList<String> adminOperation = new ArrayList<String>();// creating arraylist to add admin operations
		adminOperation.add("7.Add Product item");
		adminOperation.add("8.Calculate Bill");
		adminOperation.add("9. Display amount to End User");
		adminOperation.add("10. Check Quantity");
		adminOperation.add("11. Check registered User");
		adminOperation.add("12. Check perticular User History");
		ArrayList<String> guestOperation = new ArrayList<String>();// creating arraylist to add guest opeartions
		guestOperation.add("13. View Product item");
		guestOperation.add("14. Not purchase item");
		LinkedHashMap<String, ArrayList<String>> roles = new LinkedHashMap<String, ArrayList<String>>();// Creating
																										// LinkedHashMap
																										// to add 3
																										// roles
		roles.put("User Operation", userOperation);// it will add haeding user operations and an arraylist that contains
													// user oerations
		roles.put("Admin Operation", adminOperation);// it will add haeding Admin operations and an arraylist that
														// contains Admin oerations
		roles.put("Guest Operation", guestOperation);// it will add haeding Guest operations and an arraylist that
														// contains Guest oerations
		// creating a hashmap for heading of shop
		HashMap<String, HashMap<String, ArrayList<String>>> ecom = new HashMap<String, HashMap<String, ArrayList<String>>>();
		ecom.put("Welcome to E-Commerece based Application", roles);
		// Printing the Menu
		Set<String> s = ecom.keySet();
		for (String st : s) {
			System.out.println(st + "\n");// It will print the key i.e a heading
			System.out.println(ecom.get(st) + "\n");// It will print 3 roles with their operations
		}

		boolean adminlogin;
		UserImpl userimpl = new UserImpl();
		AdminImpl adminimpl = new AdminImpl();
		System.out.println("Enter your choice :");
		Scanner sc = new Scanner(System.in);
		int ch = sc.nextInt();
		switch (ch) {
		case 1:
			// Calling the method to register the user
			userimpl.getUserInput();
			// userimpl.getUserregistrationDetails(null, null, null, null, null, null,
			// null);

			break;
		case 2:
			// callung the method to login the user
			userimpl.getUserLoginDetails();

			break;
		case 3:
			// calling the method to view the products in sorted order
			System.out.println("Login First!!!");
			userimpl.getUserLoginDetails();
			userimpl.viewProduct();
			break;

		case 4:
			System.out.println("Login First!!!");
			userimpl.getUserLoginDetails();
			// calling the method to add the products into the cart
			/*System.out.println("Enter Product id>>");
			sc = new Scanner(System.in);
			int pid = sc.nextInt();
			System.out.println("Enter the Quantity>>");
			int qty = sc.nextInt();
			*/
			userimpl.getBuyProduct();
			break;
		case 5:
			System.out.println("Login First!!!");
			userimpl.getUserLoginDetails();
			// Calling the method to view the cart
			userimpl.viewCart();
			break;
		case 6:
			System.out.println("Login First!!!");
			userimpl.getUserLoginDetails();
			System.out.println("Enter your user name");
			String name=sc.next();
			// calling the method to purchase the items in the cart
			userimpl.purchaseTheItem(name);
			break;

		case 7:
			System.out.println("If you are Admin.. Login First!!!");
			// Admin can add products into product table
			// Before that admin should login first
			adminlogin = adminimpl.getAdminLoginDetails();
			if (adminlogin) {
				System.out.println("Login as Admin!!");
				adminimpl.addProductItem();
			} else {
				System.out.println("Login failed!!!");
			}

			break;

		case 8:
			System.out.println("If you are Admin.. Login First!!!");
			// Admin can calculate the bill
			adminlogin = adminimpl.getAdminLoginDetails();
			if (adminlogin)
				adminimpl.calculateBill();
			break;

		case 9:
			System.out.println("If you are Admin.. Login First!!!");
			// Admin will display the total amount to the end user
			adminlogin = adminimpl.getAdminLoginDetails();
			if (adminlogin)
				adminimpl.displayAmount();
			break;
		case 10:
			System.out.println("If you are Admin.. Login First!!!");
			// admin can check the quantity of product by product id
			adminlogin = adminimpl.getAdminLoginDetails();
			if (adminlogin)
				adminimpl.checkQauntity();
			break;
		case 11:
			System.out.println("If you are Admin.. Login First!!!");
			// admin can check the registered users
			adminlogin = adminimpl.getAdminLoginDetails();
			if (adminlogin)
				adminimpl.checkRegisteredUser();
			break;
		case 12:
			System.out.println("If you are Admin.. Login First!!!");
			// admin can check the perticular user history
			adminlogin = adminimpl.getAdminLoginDetails();
			if (adminlogin)
				adminimpl.checkUserHistory();
			break;
		case 13:
			// Gest can Only View the products
			GuestImpl guestimpl = new GuestImpl();
			guestimpl.viewProduct();

			break;
		case 14:
			// Guest cannot buy any product
			System.out.println("You can't buy any product!!! \n If you want to buy something Register first!!");
			break;

		default:
			System.out.println("Please select valid choice");
		}
	}
}
