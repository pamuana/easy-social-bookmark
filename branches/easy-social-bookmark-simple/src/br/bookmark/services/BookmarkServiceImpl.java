package br.bookmark.services;

import javax.persistence.EntityTransaction;

import br.bookmark.models.Bookmark;

public class BookmarkServiceImpl extends GenericServiceImpl<Bookmark> implements BookmarkService {
	
	public BookmarkServiceImpl() {
		super();
		this.type = Bookmark.class;
	}

	public void addUser(Bookmark bookmark, String idUser) {
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			entityMgr.createQuery("UPDATE "+type.getName()+" SET idUser="+idUser+" WHERE id = "+bookmark.getId()).executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}
	}

	public void addCommunity(Bookmark bookmark, String idCommunity) {
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			entityMgr.createQuery("INSERT INTO Community_Bookmark(Community_id,bookmarks_id) values ("+idCommunity+","+bookmark.getId()+")").executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}
	}

}
