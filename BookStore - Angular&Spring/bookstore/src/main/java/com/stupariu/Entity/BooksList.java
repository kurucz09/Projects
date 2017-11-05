package com.stupariu.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BooksList", propOrder = { "booksList" })
@XmlRootElement(name = "BooksList")
public class BooksList {

	@XmlElement(name = "Book", required = true)
    private List<Book> booksList = new ArrayList<Book>();
	
	public BooksList(){
		
	}

	public List<Book> getBooksList() {
		return booksList;
	}

	public void setBooksList(List<Book> booksList) {
		this.booksList = booksList;
	}
	
	
	
	
	
}
