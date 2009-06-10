package br.bookmark.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.persistence.EntityTransaction;

import br.bookmark.models.BookmarkPrivate;

public class BookmarkPrivateServiceImpl extends GenericServiceImpl<BookmarkPrivate> implements BookmarkPrivateService {

	public BookmarkPrivateServiceImpl() {
		super();
		this.type = BookmarkPrivate.class;
	}

	private String tagsToString(String[] tags) {
		String toReturn="";
		for(String tagName : tags){
			tagName=tagName.replaceAll(" ","");
			if (!"".equals(tagName) && !toReturn.contains(tagName)){
				toReturn += ","+tagName;
			}
		}
		return toReturn.substring(1);
	}
	
	public Map<String,Long> getUserCloud(String idUser){
		Map<String, Long> toReturn = new HashMap<String, Long>();
		List<BookmarkPrivate> bookmarks= this.listByField("idUser", idUser);
		for (BookmarkPrivate bookmarkPrivate : bookmarks) {
			String[] tags =bookmarkPrivate.getTags().split(",");
			for (String tagName : tags) {
				if (!toReturn.containsKey(tagName)) {
					toReturn.put(tagName, 1L);
				}else {
					toReturn.put(tagName, toReturn.get(tagName)+1);
				}
			}
		}
		return toReturn;
	}
	
	//..overriding method persist to include separator between tags
	public BookmarkPrivate persist(BookmarkPrivate bookmark, String id) {

		Pattern p = Pattern.compile(",+|\n+| +");
		String[] tags =p.split(bookmark.getTags());
		bookmark.setTags(tagsToString(tags));

		entityMgr = emf.createEntityManager();
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

		return this.findById(id);
	}
	
	

}
