package br.edu.unoesc.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

	private static EntityManagerFactory entityManagerFactory = null;

	private static EntityManager entityManager = null;

	private static void createFactory() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence
					.createEntityManagerFactory("jsfUnit");
		}
	}

	public static EntityManager getEntityManager() {
		if (entityManager == null) {
			createFactory();
			entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManager;
	}

	
}
