package br.bookmark.services;

import java.util.List;

public interface GenericService<T> {
		
	public T findById(String id);
	
	public T findByField(String field, String value);
	
	public T findByLikeField(String field, String value);
	
	public List<T> listAll();
	
	public List<T> listByField(String field, String value);

	public List<T> listByLikeField(String field, String value);

	public T persist(T obj, String id);

	public void remove(T obj);

	public void remove(String id);

}
