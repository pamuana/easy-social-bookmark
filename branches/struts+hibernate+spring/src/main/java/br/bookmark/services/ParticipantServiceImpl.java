package br.bookmark.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import br.bookmark.models.Participant;

public class ParticipantServiceImpl implements ParticipantService {

	private EntityManagerFactory emf = EntityManagerFactoryService.getEmf();
	
	public Participant findById(String id) {
		EntityManager entityMgr = emf.createEntityManager();
        return entityMgr.find(Participant.class,Long.parseLong(id));
	}

	public void persist(Participant participant, String id) {
		EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = entityMgr.getTransaction();
            tx.begin();

            if( id == null || "".equals(id) ) {
                entityMgr.persist(participant);
            } else {
                entityMgr.merge(participant);
            }

            tx.commit();
        } catch (Exception e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
	}

	public void remove(Participant participant) {
		EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = entityMgr.getTransaction();
            tx.begin();

            entityMgr.remove(participant);

            tx.commit();
        } catch (Exception e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
	}

}
