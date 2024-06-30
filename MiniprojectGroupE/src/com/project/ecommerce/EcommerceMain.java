package com.project.ecommerce;

import java.util.Scanner;


public class EcommerceMain {
	public static void main(String[] args) {
		System.out.println("Welcome to E-Commerce based application\r\n" + "User Operation\r\n"
				+ "1. User Registration\r\n" + "2. User Login\r\n" + "3. User view Product item as Sorted Order\r\n"
				+ "4. Buy Product\r\n" + "5. View Cart\r\n" + "6. Purchase the item\r\n" + "Admin Operation\r\n"
				+ "7. Add product item\r\n" + "8. Calculate Bill\r\n" + "9. Display amount to End User\r\n"
				+ "10.Check Quantity\r\n" + "11. Check registered user\r\n"
				+ "12. Check the particular user history\r\n" + "Guest Operation\r\n" + "13. View product item\r\n"
				+ "14. Not purchase item");
		System.out.println("Enter your choice :");
		Scanner sc = new Scanner(System.in);
		int ch = sc.nextInt();
		switch (ch) {
		case 1:
			
			break;
		case 2:
			

			break;
		case 3:

			break;

		/*
		 * case 4: buyProduct(); break;
		 * 
		 * case 5: viewCart();
		 * 
		 * 
		 * break; case 6: purchaseTheItem(); break;
		 * 
		 * case 7: addProductItem();
		 * 
		 * break;
		 * 
		 * case 8: calculateBill();
		 * 
		 * break;
		 * 
		 * case 9: DisplayAmountToEndUser(); break;
		 * 
		 * case 10: checkQuantity(); break;
		 * 
		 * case 11: checkRegisteredUser(); break;
		 * 
		 * case 12: checkPerticularUser();
		 * 
		 * break;
		 * 
		 * case 13: viewProductItem
		 * 
		 * break; case 14: notPurchaseItem();
		 */
		default:
			System.out.println("Please select valid choice");
		}
	}
}
