package com.stupariu.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.stupariu.Enums.Genre;
import com.sun.xml.internal.txw2.annotation.XmlElement;

@XmlType(propOrder={"ISBN", "title","authors","year","genres","image","review","nrOfItems","price"})
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlRootElement(name = "Book")
public class Book {
	
	private String ISBN;
	private String title;
	private String authors;
	private String year;
	private List<Genre> genres = new ArrayList<Genre>();
	private String image;
	private int review;
	private int nrOfItems;
	private float price;
	
	public Book(){
		
	}

	public Book(String iSBN, String title, String authors, String year, List<Genre> genres, String image,
			int review, int nrOfItems, float price) {
		super();
		ISBN = iSBN;
		this.title = title;
		this.authors = authors;
		this.year = year;
		this.genres = genres;
		this.image = image;
		this.review = review;
		this.nrOfItems = nrOfItems;
		this.price = price;
	}

	public String getISBN() {
		return ISBN;
	}

	@XmlElement
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	@XmlElement
	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getYear() {
		return year;
	}

	@XmlElement
	public void setYear(String year) {
		this.year = year;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	@XmlElementWrapper(name="genres")
	@XmlElement
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public String getImage() {
		return image;
	}

	@XmlElement
	public void setImage(String image) {
		this.image = image;
	}

	public int getReview() {
		return review;
	}

	@XmlElement
	public void setReview(int review) {
		this.review = review;
	}

	public int getNrOfItems() {
		return nrOfItems;
	}

	@XmlElement
	public void setNrOfItems(int nrOfItems) {
		this.nrOfItems = nrOfItems;
	}

	public float getPrice() {
		return price;
	}

	@XmlElement
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	
	
}
