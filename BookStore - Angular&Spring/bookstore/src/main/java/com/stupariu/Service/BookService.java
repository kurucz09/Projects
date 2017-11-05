package com.stupariu.Service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stupariu.DAO.BookDAO;
import com.stupariu.Entity.Book;
import com.stupariu.Entity.Message;
import com.stupariu.Factory.ReportFactory;

@Service
public class BookService {

	@Autowired
	@Qualifier("bookXML")
	private BookDAO bookDAO;
	
	public Collection<Book> getAllBooks() {
		Collection<Book> collect = this.bookDAO.getAllBooks().getBooksList();
		return collect;
	}

	public Book getBookByISBN(String isbn) {
		return this.bookDAO.getBookByISBN(isbn);
	}

	public void updateBook(Book book) {
		this.bookDAO.updateBook(book);
	}

	public void deleteBook(String isbn) {
		this.bookDAO.deleteBook(isbn);
	}

	public void insertBook(Book book) {
		this.bookDAO.insertBook(book);
	}

	public String getStockOfBook(String isbn) {
		return this.bookDAO.getStockOfBook(isbn);
	}

	public void increaseStock(String isbn) {
		Book book = this.bookDAO.getBookByISBN(isbn);
		Book newBook = book;
		newBook.setNrOfItems(book.getNrOfItems()+1);
		this.bookDAO.updateBook(newBook);
	}

	public Message decreaseStock(String isbn) {
		Book book = this.bookDAO.getBookByISBN(isbn);
		Book newBook = book;
		int items = book.getNrOfItems();
		Message msg = new Message();
		if(items<=0){
			msg.setMessage("failed");
		} else{
			newBook.setNrOfItems(book.getNrOfItems()-1);
			this.bookDAO.updateBook(newBook);
			msg.setMessage("success");
		}
		return msg;
	}

	public Message sellBooks(String isbn, String items) {
		Book book = this.bookDAO.getBookByISBN(isbn);
		Book newBook = book;
		int items2 = book.getNrOfItems();
		Message msg = new Message();
		if(items2<=0){
			msg.setMessage("failed");
		} else if(items2 < Integer.parseInt(items)){
			msg.setMessage("failed");
		} else{
			newBook.setNrOfItems(book.getNrOfItems()-Integer.parseInt(items));
			this.bookDAO.updateBook(newBook);
			msg.setMessage("success");
		}
		return msg;
	}

	public Collection<Book> getOutOfStockBooks() {
		ReportFactory reportFactory = new ReportFactory();
		return reportFactory.getBooksOnStock("outOfStock");
	}

}
