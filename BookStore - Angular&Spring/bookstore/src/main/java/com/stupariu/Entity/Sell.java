package com.stupariu.Entity;

public class Sell {

	private Book book;
	private String quantity;
	private String date;
	private String user;
	
	public Sell(){
		
	}
	
	public Sell(Book book, String quantity, String date, String user) {
		super();
		this.book = book;
		this.quantity = quantity;
		this.date = date;
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	
	
}
