package org.nt67.h2dbtest.entity;

import javax.persistence.Entity;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Book extends Item {
	private String bookTitle;

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
