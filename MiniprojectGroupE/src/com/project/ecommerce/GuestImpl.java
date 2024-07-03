package com.project.ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GuestImpl implements Guest {

	@Override
	public void viewProduct() {
		UserImpl user = new UserImpl();
		user.viewProduct();
		System.out.println("\n You cant buy any product!!! \n Want to buy something Register First!!!");

	}

}
