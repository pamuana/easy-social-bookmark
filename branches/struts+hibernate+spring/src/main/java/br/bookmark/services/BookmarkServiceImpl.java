package br.bookmark.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import br.bookmark.models.Bookmark;

public class BookmarkServiceImpl implements BookmarkService {

	private EntityManagerFactory emf = EntityManagerFactoryService.getEmf();
	
	public Bookmark findById(String id) {
		EntityManager entityMgr = emf.createEntityManager();
        return entityMgr.find(Bookmark.class,Long.parseLong(id));
	}

	public void persist(Bookmark bookmark, String id) {
		

		String[] tags = bookmark.getTags().split("[^a-zA-Záéíóúçñâêîôûãõß]");
		bookmark.setTags("");
		for (String tag:tags){
			if (!"".equals(tag)){
				bookmark.setTags(bookmark.getTags()+tag+",");
			}
		}
		
		EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = entityMgr.getTransaction();
            tx.begin();

            if( id == null || "".equals(id) ) {
                entityMgr.persist(bookmark);
            } else {
                entityMgr.merge(bookmark);
            }

            tx.commit();
        } catch (Exception e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
	}

	public void remove(Bookmark bookmark) {
		EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = entityMgr.getTransaction();
            tx.begin();
            entityMgr.remove(bookmark);

            tx.commit();
        } catch (Exception e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
	}
	
	public void remove(long idBookmark) {
		EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = entityMgr.getTransaction();
            tx.begin();
            
            entityMgr.createQuery("DELETE FROM Bookmark WHERE id="+idBookmark).executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
	}

	public void addUser(Bookmark bookmark, long idUser) {
		EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = entityMgr.getTransaction();
            tx.begin();

            entityMgr.createQuery("UPDATE Bookmark SET idUser="+idUser+" WHERE id = "+bookmark.getId()).executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
	}

	public void addCommunity(Bookmark bookmark, long idCommunity) {
		EntityManager entityMgr = emf.createEntityManager();
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
