package com.bac.config.cxf.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Order")
public class Order {
	
	private String customerID;
	private String itemID;
	private int qunatity;
	private double price;
	
	public Order() {
		
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public int getQunatity() {
		return qunatity;
	}

	public void setQunatity(int qunatity) {
		this.qunatity = qunatity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [customerID=");
		builder.append(customerID);
		builder.append(", itemID=");
		builder.append(itemID);
		builder.append(", qunatity=");
		builder.append(qunatity);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}
	
}