package org.nt67.h2dbtest.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.h2.tools.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public abstract class AbstractEntityTest {

	private static final String BASE_DIR = "db\\h2";
	private static Server tcpServer;
	private static EntityManagerFactory emf = null;
	protected static EntityManager em = null;

	@BeforeClass
	public static void runDb() throws Exception {
		tcpServer = org.h2.tools.Server.createTcpServer(
	            new String[] { "-baseDir", BASE_DIR, "-tcpPort", "9092" }).start();
		tcpServer.setOut(System.out);

	}

	@AfterClass
	public static void shutdownDb() {
	    tcpServer.shutdown();
	}

	@BeforeClass
	public static void createEntityManager() throws ClassNotFoundException {
		System.out.println("res:" + BookTest.class.getResource("META-INF/persistence.xml"));
		System.out.println("class:" + Class.forName("org.hibernate.ejb.HibernatePersistence"));
	
		emf = Persistence.createEntityManagerFactory("h2dbtest");
		em = emf.createEntityManager();
	}

	@AfterClass
	public static void closeEntityManager() {
		emf.close();
		em.close();
	}

}
