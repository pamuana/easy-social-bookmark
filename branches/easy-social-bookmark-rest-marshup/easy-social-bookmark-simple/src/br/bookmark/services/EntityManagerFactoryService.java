package br.bookmark.services;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EntityManagerFactoryService {
	
	private EntityManagerFactoryService(){
	}
	
	private static class SingletonHolder { 
	     private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookmarks");
	}
	 
	public static EntityManagerFactory getEmf() {
		return SingletonHolder.emf;
	}

}
