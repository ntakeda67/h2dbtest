package org.nt67.h2dbtest.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
public class ItemTest extends AbstractEntityTest{

    @Before
    public void createAnEntity() {
        Item item = new Item();
        item.setName("Hibernate");

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(item);
        tx.commit();
    }

    @Test
    public void testItem() {
        Item item = (Item) em.find(Item.class, 1);
        assertThat(item.getName(), is("Hibernate"));
    }
}