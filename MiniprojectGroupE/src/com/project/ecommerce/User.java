package com.project.ecommerce;

//Interface to define a method for getting user login credentials
public interface User {
	public void getUserregistrationDetails(String firstname, String lastname, String username, String password, String city,
			String emailid, String mobilenumber);
	public void getUserLoginDetails();
	public void viewProduct();
	public void buyProduct(String uname,int id,int qty);
	public void viewCart();
	public void purchaseTheItem(String name);
	void buyProduct();
	public void savePurchaseHistory();

}
