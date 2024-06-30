package com.project.ecommerce;

import java.util.Scanner;

public class UserImpl implements User {
	Scanner sc = null;
	int pid;
	int qty;
	String view_cart;

	@Override
	public void buyProduct() {
		System.out.println("Enter Product id>>");
		sc = new Scanner(System.in);
		pid = sc.nextInt();
		System.out.println("Enter the Quantity>>");
		qty = sc.nextInt();
		System.out.println("Do you want to view cart?");
		view_cart = sc.next();
	}

	@Override
	public void getUserregistrationDetails() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getUserLoginDetails() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewProduct() {
		Product product = new Product();
		System.out.println("Product Id>>" + product.getProductid());
		System.out.println("Product name>>" + product.getProductName());
		System.out.println("Product Discription>>" + product.getProductDiscription());
		System.out.println("Product Quantity>>" + product.getQuantity());
		System.out.println("Product Price>>" + product.getPrice());
		
	}

	@Override
	public void viewCart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void purchaseTheItem() {
		// TODO Auto-generated method stub
		
	}



}
