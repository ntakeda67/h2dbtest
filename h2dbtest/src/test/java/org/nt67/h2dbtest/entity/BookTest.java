package org.nt67.h2dbtest.entity;



import javax.persistence.EntityTransaction;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class BookTest extends AbstractEntityTest{

	@Before
	public void createAnEntity(){
		Book book = new Book();
		book.setBookTitle("New Book Title");
		book.setName("New Name");

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(book);
		tx.commit();
	}

	@Test
	public void canGetAnEntity(){
		Book book = (Book) em.find(Book.class, 1);
		System.out.println(book);
		assertThat(book, is(not(null)));
	}


}
