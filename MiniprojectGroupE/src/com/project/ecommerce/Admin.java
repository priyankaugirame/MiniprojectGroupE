package com.project.ecommerce;

import java.sql.SQLException;

public interface Admin {
	public boolean getAdminLoginDetails(); // this method id used for admin login

	public void addProductItem(); // Admin can add Products into the product table

	public void calculateBill(); // Admin calculates the Bill

	public void displayAmount(); // Admin displays the amount to the end user

	public void checkQauntity() throws SQLException;// Admin can check the quantity of product by using product id

	public void checkRegisteredUser() throws SQLException; // Admin can check registered users

	public void checkUserHistory(); // Admin can check user Historys

}
