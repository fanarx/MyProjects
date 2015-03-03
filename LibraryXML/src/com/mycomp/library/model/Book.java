package com.mycomp.library.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Book {
	
	private String name;
	@XmlElement(name="author")
	private List<Author> authors;
	private String ISBN;
	private int count;
	private int editionYear;
	private String pubCompany;
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public String getPubCompany() {
		return pubCompany;
	}
	public void setPubCompany(String pubCompany) {
		this.pubCompany = pubCompany;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public int getEditionYear() {
		return editionYear;
	}
	public void setEditionYear(int editionYear) {
		this.editionYear = editionYear;
	}
	@Override
	public String toString() {
		return "Book [name=" + name + ", authors=" + authors + ", ISBN=" + ISBN
				+ ", count=" + count + ", editionYear=" + editionYear
				+ ", pubCompany=" + pubCompany + "]";
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
