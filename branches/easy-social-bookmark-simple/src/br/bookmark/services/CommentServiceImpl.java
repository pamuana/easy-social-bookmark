package br.bookmark.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.bookmark.models.Comment;

// TODO revisar o método deve funcionar adicionando um idBookmark ao bookmark 
public class CommentServiceImpl extends GenericServiceImpl<Comment> implements CommentService {
	
	public CommentServiceImpl() {
		super();
		this.type = Comment.class;
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
