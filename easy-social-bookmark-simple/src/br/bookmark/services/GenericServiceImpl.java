package br.bookmark.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public class GenericServiceImpl<T> implements GenericService<T> {

	protected EntityManagerFactory emf = EntityManagerFactoryService.getEmf();
	protected EntityManager entityMgr = emf.createEntityManager();
	protected Class<T> type = null;	

	public T findById(String id) {
		if (id == null || "".equals(id)) return null;
		T toReturn = null;
		try {
			toReturn = entityMgr.find(type,Long.parseLong(id));
		} catch (NoResultException e) { 
			return null;
		}
		return  type.cast(toReturn);
	}

	public T findByField(String field, String value)   {
		Object toReturn = null;
		try {
			toReturn = entityMgr.createQuery("FROM "+type.getName()+" WHERE "+field+" = :"+field+" ").setParameter(field, value).getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
		return type.cast(toReturn);
	}

	public T findByLikeField(String field, String value) {
		Object toReturn = null;
		try {
			toReturn = entityMgr.createQuery("FROM "+type.getName()+" WHERE "+field+" like :"+field+" ").setParameter(field, value).getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
		return type.cast(toReturn);
	}

	public List<T> listAll() {
		List<T> toReturn = new ArrayList<T>();
		toReturn = (List<T>) entityMgr.createQuery("FROM "+type.getName()).getResultList();
		return toReturn;
	}

	public List<T> listByField(String field, String value) {
		List<T> toReturn = new ArrayList<T>();
		toReturn = (List<T>) entityMgr.createQuery("FROM "+type.getName()+" WHERE "+field+" = :"+field+" ").setParameter(field, value).getResultList();
		return toReturn;
	}

	public List<T> listByLikeField(String field, String value) {
		List<T> toReturn = new ArrayList<T>();
		toReturn = (List<T>) entityMgr.createQuery("FROM "+type.getName()+" WHERE "+field+" like :"+field+" ").setParameter(field, value).getResultList();
		return toReturn;
	}

	public T persist(T obj, String id) {
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
		entityMgr.remove(obj);
	}

	public void remove(String id) {
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
