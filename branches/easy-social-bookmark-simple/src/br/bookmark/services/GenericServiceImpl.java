package br.bookmark.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class GenericServiceImpl<T> implements GenericService<T> {

	protected EntityManagerFactory emf;
	protected EntityManager entityMgr;
	protected Class<T> type = null;

	public GenericServiceImpl(){
		emf = Persistence.createEntityManagerFactory("bookmarks");
		entityMgr = emf.createEntityManager();
	}
	

	public T findById(String id) {
		entityMgr = emf.createEntityManager();
		if (id == null || "".equals(id)) return null;
		T toReturn = null;
		try {
			toReturn = entityMgr.find(type,Long.parseLong(id));
			return  type.cast(toReturn);
		} catch (NoResultException e) { 
			return null;
		}
	}

	public T findByField(String field, String value)   {
		entityMgr = emf.createEntityManager();
		Object toReturn = null;
		try {
			toReturn = entityMgr.createQuery("FROM "+type.getName()+" WHERE "+field+" = :"+field+" ").setParameter(field, value).getSingleResult();
			return type.cast(toReturn);
		}catch (NoResultException e) {
			return null;
		}
	}

	public T findByLikeField(String field, String value) {
		entityMgr = emf.createEntityManager();
		Object toReturn = null;
		try {
			toReturn = entityMgr.createQuery("FROM "+type.getName()+" WHERE "+field+" like :"+field+" ").setParameter(field, value).getSingleResult();
			return type.cast(toReturn);
		}catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> listAll() {
		entityMgr = emf.createEntityManager();
		List<T> toReturn = new ArrayList<T>();
		toReturn = (List<T>) entityMgr.createQuery("FROM "+type.getName()).getResultList();
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	public List<T> listByField(String field, String value) {
		entityMgr = emf.createEntityManager();
		List<T> toReturn = new ArrayList<T>();
		toReturn = (List<T>) entityMgr.createQuery("FROM "+type.getName()+" WHERE "+field+" = :"+field+" ").setParameter(field, value).getResultList();
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	public List<T> listByLikeField(String field, String value) {
		entityMgr = emf.createEntityManager();
		List<T> toReturn = new ArrayList<T>();
		toReturn = (List<T>) entityMgr.createQuery("FROM "+type.getName()+" WHERE "+field+" like :"+field+" ").setParameter(field, value).getResultList();
		return toReturn;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listByCriteria(String criteria) {
		entityMgr = emf.createEntityManager();
		List<T> toReturn = new ArrayList<T>();
		toReturn = (List<T>) entityMgr.createQuery("FROM "+type.getName()+" WHERE "+criteria+" ").getResultList();
		return toReturn;
	}

	public T persist(T obj, String id) {
		entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			if( id == null || "".equals(id) ) {
				entityMgr.persist(type.cast(obj));
			} else {
				entityMgr.merge(type.cast(obj));
			}

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}
		
		return this.findById(id);
	}

	public void remove(T obj) {
		entityMgr = emf.createEntityManager();
		entityMgr.remove(obj);
	}

	public void remove(String id) {
		
		entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			entityMgr.createQuery("DELETE FROM "+type.getName()+"  WHERE id="+id).executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}
	}


}
