package com.stupariu.Factory;

import java.util.ArrayList;
import java.util.Collection;

import com.stupariu.DAO.BookDAO;
import com.stupariu.Entity.Book;
import com.stupariu.Entity.BooksList;

public class ReportFactory {
	
	BooksList booksList;
	
	public ReportFactory(){
		BookDAO bookDAO = new BookDAO();
		booksList = bookDAO.getAllBooks();
	}
	
	public Collection<Book> getBooksOnStock(String type){
		if(type.contentEquals("outOfStock")){
			return this.outStock();
		} else if(type.contentEquals("onStock")){
			return this.onStock();
		} else {
			return null;
		}
		
	}
	
	private Collection<Book> onStock(){
		Collection<Book> list = new ArrayList<Book>();
		for (Book book : booksList.getBooksList()){
			if(book.getNrOfItems() >= 1){
				list.add(book);
			}
		}
		return list;
	}
	
	private Collection<Book> outStock(){
		Collection<Book> list = new ArrayList<Book>();
		for (Book book : booksList.getBooksList()){
			if(book.getNrOfItems() < 1){
				list.add(book);
			}
		}
		return list;
	}
	
	
}
