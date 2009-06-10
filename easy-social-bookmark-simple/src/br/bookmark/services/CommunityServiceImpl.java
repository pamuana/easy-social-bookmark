package br.bookmark.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.bookmark.models.Community;


// TODO refactorar para usar com Community
public class CommunityServiceImpl extends GenericServiceImpl<Community> implements CommunityService {
	
	public CommunityServiceImpl() {
		super();
		this.type = Community.class;
	}

	public void persist(Community community, String id, String idUser) {
				
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();
			
			entityMgr.persist(community);
			entityMgr.createNativeQuery("INSERT INTO Participant(role,idCommunity,idUser) VALUES ('ADMIN',"+community.getId()+","+idUser+")").executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		} 
	}

	public void remove(String idCommunity) {
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			entityMgr.createQuery("DELETE FROM Participant WHERE idCommunity="+idCommunity).executeUpdate();
			entityMgr.createNativeQuery("DELETE FROM Community_Bookmark WHERE Community_id="+idCommunity).executeUpdate();
			entityMgr.createQuery("DELETE FROM Community WHERE id="+idCommunity).executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}
	}

	public List<Community> listCommunityWithOutSubscrition(String idUser) {
		List<Community> toReturn= new ArrayList<Community>();

		EntityManager entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			toReturn = (List<Community>) entityMgr.createNativeQuery("SELECT * FROM Community WHERE id NOT IN (SELECT idCommunity FROM Participant WHERE idUser="+idUser+" )",Community.class).getResultList();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}

		return toReturn;
	}

	public void subscribeUser(Community community, String idUser) {
		EntityManager entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			entityMgr = emf.createEntityManager();
			tx = entityMgr.getTransaction();
			tx.begin();

			entityMgr.createNativeQuery("INSERT INTO Participant(role,idCommunity,idUser) VALUES ('USER',"+community.getId()+","+idUser+")").executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}
	}

	public void unsubscribeUser(Community community, String idUser) {
		EntityManager entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			entityMgr = emf.createEntityManager();
			tx = entityMgr.getTransaction();
			tx.begin();

			entityMgr.createNativeQuery("DELETE FROM Participant WHERE idUser="+idUser+" AND idCommunity ="+community.getId()).executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}
	}

	public void addBookmark(Community community, String idBookmark) {
		EntityTransaction tx = null;
		try {
			entityMgr = emf.createEntityManager();
			tx = entityMgr.getTransaction();
			tx.begin();

			entityMgr.createNativeQuery("INSERT INTO Community_Bookmark(Community_id,bookmarks_id) VALUES ("+community.getId()+","+idBookmark+")").executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}
	}
	
}
