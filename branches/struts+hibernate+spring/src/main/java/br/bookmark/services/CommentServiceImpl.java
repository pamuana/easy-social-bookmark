package br.bookmark.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import br.bookmark.models.Comment;
import br.bookmark.models.User;

public class CommentServiceImpl implements CommentService {

	private EntityManagerFactory emf = EntityManagerFactoryService.getEmf();
	
    public Comment findById(String id) {
		EntityManager entityMgr = emf.createEntityManager();
        return entityMgr.find(Comment.class,Long.parseLong(id));
	}

	public void persist(Comment comment, String id) {
		EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = entityMgr.getTransaction();
            tx.begin();

            if( id == null || "".equals(id) ) {
                entityMgr.persist(comment);
            } else {
                entityMgr.merge(comment);
            }

            tx.commit();
        } catch (Exception e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
	}

	public void remove(Comment comment) {
		EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = entityMgr.getTransaction();
            tx.begin();

            entityMgr.remove(comment);

            tx.commit();
        } catch (Exception e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
	}

	public void persistNative(Comment comment, String idBookmark) {
		EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = entityMgr.getTransaction();
            tx.begin();
            
            entityMgr.persist(comment);
            
            tx.commit();
            
            tx = entityMgr.getTransaction();
            tx.begin();
            
            System.out.println("UPDATE Comment SET idBookmark="+idBookmark+" WHERE id="+comment.getId());
            //entityMgr.createNativeQuery("UPDATE Comment SET idBookmark="+idBookmark+" WHERE id="+comment.getId());
            
            tx.commit();
        } catch (Exception e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
	}

	

}
