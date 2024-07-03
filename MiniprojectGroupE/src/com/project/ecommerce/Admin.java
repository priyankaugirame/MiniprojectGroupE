package com.project.ecommerce;

import java.sql.SQLException;

public interface Admin {
	public boolean getAdminLoginDetails();
	public void addProductItem();
	public void calculateBill();
	public void displayAmount();
	public void checkQauntity() throws SQLException;
	public void checkRegisteredUser() throws SQLException;
	public void checkUserHistory();

}
