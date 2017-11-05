package com.stupariu.DAO;

import static com.stupariu.Constants.BooksURL;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Repository;

import com.stupariu.Entity.Book;
import com.stupariu.Entity.BooksList;
import com.stupariu.Enums.Genre;

@Repository("bookXML")
public class BookDAO {

	public BookDAO(){
		
	}
	
	private void writeBooksToXML(BooksList bookList){
		try{
			File books = new File(BooksURL);
			JAXBContext jaxbContext = JAXBContext.newInstance(BooksList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(bookList, books);
		}catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	private BooksList readBooksFromXML(){
		BooksList bookList = new BooksList();
		try {

			File file = new File(BooksURL);
			JAXBContext jaxbContext = JAXBContext.newInstance(BooksList.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			bookList = (BooksList) jaxbUnmarshaller.unmarshal(file);

		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
		return bookList;
	}

	public BooksList getAllBooks() {
		BooksList booksList = readBooksFromXML();
		return booksList;
	}

	public Book getBookByISBN(String isbn) {
		BooksList booksList = readBooksFromXML();
		for(Book book : booksList.getBooksList()){
			if(book.getISBN().contentEquals(isbn)){
				return book;
			}
		}
		return null;
	}

	public void updateBook(Book newBook) {
		BooksList booksList = readBooksFromXML();
		for(Book book : booksList.getBooksList()){
			if(book.getISBN().contentEquals(newBook.getISBN())){
				book.setImage(newBook.getImage());
				book.setNrOfItems(newBook.getNrOfItems());
				book.setPrice(newBook.getPrice());
				book.setReview(newBook.getReview());
				book.setTitle(newBook.getTitle());
				book.setYear(newBook.getYear());
				book.setAuthors(newBook.getAuthors());
				book.getGenres().removeAll(book.getGenres());
				for(Genre genre : newBook.getGenres()){
					book.getGenres().add(genre);
				}
			}
		}
		writeBooksToXML(booksList);
	}

	public void deleteBook(String isbn) {
		BooksList booksList = readBooksFromXML();
		Book bookToDelete = new Book();
		for(Book book : booksList.getBooksList()){
			if(book.getISBN().contentEquals(isbn)){
				bookToDelete = book;
			}
		}
		booksList.getBooksList().remove(bookToDelete);
		writeBooksToXML(booksList);
	}

	public void insertBook(Book book) {
		BooksList booksList = readBooksFromXML();
		booksList.getBooksList().add(book);
		writeBooksToXML(booksList);
	}

	public String getStockOfBook(String isbn) {
		BooksList booksList = readBooksFromXML();
		for(Book book : booksList.getBooksList()){
			if(book.getISBN().contentEquals(isbn)){
				return String.valueOf(book.getNrOfItems());
			}
		}
		return "BookNotFound";
	}
	
}
