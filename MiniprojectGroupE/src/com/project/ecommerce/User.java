package com.project.ecommerce;

//Interface to define a method for getting user login credentials
public interface User {
	public void getUserregistrationDetails(String firstname, String lastname, String username, String password,
			String city, String emailid, String mobilenumber); // Method for User registration

	public void getUserLoginDetails(); // Method for user login

	public void viewProduct(); // Method to view all products

	public void buyProduct(String uname, int id, int qty); // Method for adding the items to the cart

	public void viewCart(); // this method will display the items which user has added to the cart

	public void purchaseTheItem(String name); // Method to purchase the items from cart

	public void savePurchaseHistory(String uname); // This method is used to save the history of user for later use

}
