package com.stupariu.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stupariu.Entity.Book;
import com.stupariu.Entity.Message;
import com.stupariu.Service.BookService;

@RestController
@RequestMapping("/books")
@CrossOrigin
public class BookController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<Book> getAllBooks() {
		return this.bookService.getAllBooks();
	}
		
	@RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
	@ResponseBody
	public Book getBookByISBN(@PathVariable("isbn") String isbn) {
		return this.bookService.getBookByISBN(isbn);
	}
	
	@RequestMapping(value = "/increase/{isbn}", method = RequestMethod.GET)
	@ResponseBody
	public void increaseStock(@PathVariable("isbn") String isbn) {
		this.bookService.increaseStock(isbn);
	}
	
	@RequestMapping(value = "/decrease/{isbn}", method = RequestMethod.GET)
	@ResponseBody
	public Message decreaseStock(@PathVariable("isbn") String isbn) {
		return this.bookService.decreaseStock(isbn);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void updateBook(@RequestBody Book book) {
		this.bookService.updateBook(book);
	}
	
	@RequestMapping(value = "/{isbn}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteBook(@PathVariable("isbn") String isbn) {
		this.bookService.deleteBook(isbn);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void insertBook(@RequestBody Book book) {
		this.bookService.insertBook(book);
	}
	
	@RequestMapping(value = "/checkstock/{isbn}", method = RequestMethod.GET)
	@ResponseBody
	public String getStockOfBook(@PathVariable("isbn") String isbn) {
		return this.bookService.getStockOfBook(isbn);
	}
	
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Book> getOutOfStockBooks() {
		return this.bookService.getOutOfStockBooks();
	}
	
	
}
