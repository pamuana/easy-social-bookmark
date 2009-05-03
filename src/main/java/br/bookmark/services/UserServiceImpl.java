package br.bookmark.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import br.bookmark.models.User;

public class UserServiceImpl implements UserService {

	private EntityManagerFactory emf = EntityManagerFactoryService.getEmf();
	
    public User findById(String id) {
		EntityManager entityMgr = emf.createEntityManager();
        return entityMgr.find(User.class,Long.parseLong(id));
	}

	public void persist(User user, String id) {
		EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = entityMgr.getTransaction();
            tx.begin();

            if( id == null || "".equals(id) ) {
                entityMgr.persist(user);
            } else {
                entityMgr.merge(user);
            }

            tx.commit();
        } catch (Exception e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
	}

	public User validateUser(String login, String password) {
		EntityManager entityMgr = emf.createEntityManager();
		return (User) entityMgr.createQuery("FROM User WHERE login = '"+login+"' AND password='"+password+"' ").getSingleResult();
	}

	

}
