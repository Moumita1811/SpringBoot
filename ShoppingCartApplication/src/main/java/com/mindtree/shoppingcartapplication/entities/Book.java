package com.mindtree.shoppingcartapplication.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book extends Product{
	
	@Column(name = "book_genre")
	private String bookgenre;
	
	@Column(name = "book_author")
	private String bookauthor;
	
	@Column(name = "book_pubication")
	private String bookpublication;

	public Book() {
		super();
	}

	public Book(Integer prodId, String prodName, float prodPrice, String bookgenre, String bookauthor, String bookpublication) {
		super(prodId, prodName, prodPrice);
		this.bookgenre = bookgenre;
		this.bookauthor = bookauthor;
		this.bookpublication = bookpublication;
	}

	public String getBookgenre() {
		return bookgenre;
	}

	public void setBookgenre(String bookgenre) {
		this.bookgenre = bookgenre;
	}

	public String getBookauthor() {
		return bookauthor;
	}

	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}

	public String getBookpublication() {
		return bookpublication;
	}

	public void setBookpublication(String bookpublication) {
		this.bookpublication = bookpublication;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bookauthor == null) ? 0 : bookauthor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookauthor == null) {
			if (other.bookauthor != null)
				return false;
		} else if (!bookauthor.equals(other.bookauthor))
			return false;
		return true;
	}
	
}
