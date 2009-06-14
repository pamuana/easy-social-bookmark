package br.bookmark.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.bookmark.models.Bookmark;

public class BookmarkServiceImpl extends GenericServiceImpl<Bookmark> implements BookmarkService {

	public BookmarkServiceImpl() {
		super();
		this.type = Bookmark.class;
	}

	@SuppressWarnings("unchecked")
	public List<Bookmark> listByIdCommunity(String idCommunity) {

		List<Bookmark> toReturn= new ArrayList<Bookmark>();

		EntityManager entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			toReturn = (List<Bookmark>) entityMgr.createNativeQuery("SELECT * FROM Bookmark WHERE shared='true' AND idUser IN (SELECT idUser FROM participant WHERE idCommunity="+idCommunity+" )",Bookmark.class).getResultList();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}

		return toReturn;
	}


	@SuppressWarnings("unchecked")
	public List<Bookmark> listByIdCommunity(String idCommunity, String idTag) {

		List<Bookmark> toReturn= new ArrayList<Bookmark>();

		EntityManager entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			toReturn = (List<Bookmark>) entityMgr.createNativeQuery("SELECT * FROM Bookmark WHERE shared='true' AND id IN (SELECT idBookmark FROM TagUser WHERE idTag="+idTag+" AND idUser IN (SELECT idUser FROM participant WHERE idCommunity="+idCommunity+" ))",Bookmark.class).getResultList();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}

		return toReturn;
	}

}
