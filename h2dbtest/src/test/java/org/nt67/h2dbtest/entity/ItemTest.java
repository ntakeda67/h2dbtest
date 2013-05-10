package org.nt67.h2dbtest.entity;

import org.h2.tools.Server;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
public class ItemTest {

    //private static final String BASE_DIR = "db\\h2;MODE=ORACLE";
    private static final String BASE_DIR = "db\\h2";
    private static Server tcpServer;

    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void beforeSuite() throws Exception {
    	tcpServer = org.h2.tools.Server.createTcpServer(
                new String[] { "-baseDir", BASE_DIR, "-tcpPort", "9092" }).start();
    	tcpServer.setOut(System.out);

        Configuration config = new AnnotationConfiguration().configure();
        sessionFactory = config.buildSessionFactory();
    }

    @AfterClass
    public static void afterSuite() {
        tcpServer.shutdown();
    }

    @Before
    public void beforeMethod() {
        Item item = new Item();
        item.setName("Hibernate");

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(item);
        transaction.commit();
        session.close();
    }

    @Test
    public void testItem() {
        Session session = sessionFactory.openSession();
        Item item = (Item) session.get(Item.class, 1);
        assertThat(item.getName(), is("Hibernate"));
    }
}