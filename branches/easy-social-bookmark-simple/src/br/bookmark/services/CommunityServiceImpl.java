package br.bookmark.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.bookmark.models.Community;

public class CommunityServiceImpl extends GenericServiceImpl<Community> implements CommunityService {
	
	public CommunityServiceImpl() {
		super();
		this.type = Community.class;
	}

	@SuppressWarnings("unchecked")
	public List<Community> listCommunityWithOutSubscrition(String idUser) {
		List<Community> toReturn= new ArrayList<Community>();

		EntityManager entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			toReturn = (List<Community>) entityMgr.createNativeQuery("SELECT * FROM Community WHERE id NOT IN (SELECT idCommunity FROM participant WHERE idUser="+idUser+" )",Community.class).getResultList();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}

		return toReturn;
	}
	
	public void remove(String id) {
		entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			entityMgr.createNativeQuery("DELETE FROM participant WHERE idCommunity="+id).executeUpdate();
			entityMgr.createQuery("DELETE FROM "+type.getName()+"  WHERE id="+id).executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}
	}

	public void addParticant(String idCommunity, String idUser) {
		entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			entityMgr.createNativeQuery("INSERT INTO participant(idUser,idCommunity) VALUES ("+idUser+","+idCommunity+")").executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}
	}


	@SuppressWarnings("unchecked")
	public List<Community> listByIdUser(String idUser) {
		List<Community> toReturn= new ArrayList<Community>();

		EntityManager entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			toReturn = (List<Community>) entityMgr.createNativeQuery("SELECT * FROM Community WHERE id IN (SELECT idCommunity FROM participant WHERE idUser="+idUser+" )",Community.class).getResultList();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}

		return toReturn;
	}

	@Override
	public void removeParticipant(String idCommunity, String idUser) {
		entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			entityMgr.createNativeQuery("DELETE FROM participant WHERE idUser="+idUser+" AND idCommunity="+idCommunity).executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}
	}

	public String getCommunityListText(String idUser,String href) {
		
		String toReturn = "";
		List<Community> listCommunityUser = this.listByIdUser(idUser);
		for (Community community : listCommunityUser) {
			String add = "<li><a href=\""+href+community.getId()+"\" >"+community.getName()+"</a></li>";
			toReturn+=add;
		}
		
		return toReturn;
	}

}
