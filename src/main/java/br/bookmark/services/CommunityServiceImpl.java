package br.bookmark.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import br.bookmark.models.Community;

public class CommunityServiceImpl implements CommunityService {

	private EntityManagerFactory emf = EntityManagerFactoryService.getEmf();
	
    public Community findById(String id) {
		EntityManager entityMgr = emf.createEntityManager();
        return entityMgr.find(Community.class,Long.parseLong(id));
	}

	public void persist(Community community, String id) {
		EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = entityMgr.getTransaction();
            tx.begin();

            if( id == null || "".equals(id) ) {
                entityMgr.persist(community);
            } else {
                entityMgr.merge(community);
            }

            tx.commit();
        } catch (Exception e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
	}
	
	public void persist(Community community, String id, String idUser) {
		EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = entityMgr.getTransaction();
            tx.begin();

            if( id == null || "".equals(id) ) {
                entityMgr.persist(community);
            } else {
                entityMgr.merge(community);
            }

            tx.commit();
        } catch (Exception e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        } finally{
        	
        	entityMgr = emf.createEntityManager();
        	tx = entityMgr.getTransaction();
            tx.begin();

            if( id == null || "".equals(id) ) {
                entityMgr.createNativeQuery("INSERT INTO Participant(role,idCommunity,idUser) VALUES ('ADMIN',"+community.getId()+","+idUser+")").executeUpdate();
            }

            tx.commit();
        }
	}

	public void remove(Community community) {
		EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = entityMgr.getTransaction();
            tx.begin();

            entityMgr.remove(community);

            tx.commit();
        } catch (Exception e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
	}

	public List<Community> listAll() {
		List<Community> toReturn= new ArrayList<Community>();
		
		EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = entityMgr.getTransaction();
            tx.begin();

            toReturn = (List<Community>) entityMgr.createQuery("FROM Community").getResultList();
            //entityMgr.remove(community);

            tx.commit();
        } catch (Exception e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
        
        return toReturn;
	}

	public void remove(long idCommunity) {
		EntityManager entityMgr = emf.createEntityManager();
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

	public void subscribeUser(Community community, long idUser) {
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

	public void unsubscribeUser(Community community, long idUser) {
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
		EntityManager entityMgr = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
        	entityMgr = emf.createEntityManager();
        	tx = entityMgr.getTransaction();
            tx.begin();

            //System.out.println("INSERT INTO Community_Bookmark(Community_id,bookmarks_id) VALUES ("+community.getId()+","+idBookmark+")");
            entityMgr.createNativeQuery("INSERT INTO Community_Bookmark(Community_id,bookmarks_id) VALUES ("+community.getId()+","+idBookmark+")").executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if ( tx != null && tx.isActive() )
                tx.rollback();
            throw (RuntimeException)e.getCause();
        }
	}


}
