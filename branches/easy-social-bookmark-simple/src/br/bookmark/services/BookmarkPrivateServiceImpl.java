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
	
	/**
	 * Returns the correlation existent between 2 objects.
	 * @param objectId1 ID of first object
	 * @param objectId2 ID of second object
	 */
	public double getCorrelation(int objectId1, int objectId2) throws SQLException {
		Map<Integer, Double> dataOfObject1 = new HashMap<Integer, Double>();
		Map<Integer, Double> dataOfObject2 = new HashMap<Integer, Double>();
		
		for (UserTag tag : this.getTagsForObject(objectId1)) {
			Double previousValue = dataOfObject1.get(tag.getTagId());
			if (previousValue == null) {
				dataOfObject1.put(tag.getTagId(), 1.0);
			} else {
				dataOfObject1.put(tag.getTagId(), previousValue + 1);
			}
		}
		for (UserTag tag : this.getTagsForObject(objectId2)) {
			Double previousValue = dataOfObject2.get(tag.getTagId());
			if (previousValue == null) {
				dataOfObject2.put(tag.getTagId(), 1.0);
			} else {
				dataOfObject2.put(tag.getTagId(), previousValue + 1);
			}
		}
		
		double normalizer1 = 0.0;
		double normalizer2 = 0.0;
		for (Double value : dataOfObject1.values()) {
			normalizer1 += value * value;
		}
		for (Double value : dataOfObject2.values()) {
			normalizer2 += value * value;
		}
		normalizer1 = Math.sqrt(normalizer1);
		normalizer2 = Math.sqrt(normalizer2);
		
		for (Integer tagId : dataOfObject1.keySet()) {
			dataOfObject1.put(tagId, dataOfObject1.get(tagId) / normalizer1);
		}
		for (Integer tagId : dataOfObject2.keySet()) {
			dataOfObject2.put(tagId, dataOfObject2.get(tagId) / normalizer2);
		}
		
		double correlation = 0.0;
		for (Integer tagId : dataOfObject1.keySet()) {
			if (dataOfObject2.keySet().contains(tagId)) {
				correlation += dataOfObject1.get(tagId) * dataOfObject2.get(tagId);
			}
		}
		
		return correlation;
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
