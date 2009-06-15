package br.bookmark.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.bookmark.models.Tag;
import br.bookmark.models.TagUser;
import br.bookmark.util.tagcloud.FontSizeComputationStrategy;
import br.bookmark.util.tagcloud.TagCloud;
import br.bookmark.util.tagcloud.TagCloudElement;
import br.bookmark.util.tagcloud.VisualizeTagCloudDecorator;
import br.bookmark.util.tagcloud.impl.HTMLTagCloudDecorator;
import br.bookmark.util.tagcloud.impl.LogFontSizeComputationStrategy;
import br.bookmark.util.tagcloud.impl.TagCloudElementImpl;
import br.bookmark.util.tagcloud.impl.TagCloudImpl;

public class TagUserServiceImpl extends GenericServiceImpl<TagUser> implements TagUserService {

	public TagUserServiceImpl() {
		super();
		this.type = TagUser.class;
	}

	private Map<String, Double> getTagFrecuency(List<TagUser> tagsUser) {
		Map<String, Double> tagFrecuency = new HashMap<String, Double>();
		for (TagUser tagUser : tagsUser) {
			Tag tag = tagUser.getTag();
			if (tagFrecuency.containsKey(tag.getName())) {
				tagFrecuency.put(tag.getName(), tagFrecuency.get(tag.getName())+1);
			}else {
				tagFrecuency.put(tag.getName(), 1.);
			}
		}
		return tagFrecuency;
	}

	private String makeCloud(Map<String, Double> tagFrecuency,String href,String head) {
		int numSizes = 3;
		String fontPrefix = "font-size: ";

		List<TagCloudElement> listTagName = new ArrayList<TagCloudElement>();
		for (String tagName : tagFrecuency.keySet()) {
			listTagName.add(new TagCloudElementImpl(tagName,tagFrecuency.get(tagName).doubleValue()));
		}

		FontSizeComputationStrategy strategy = new LogFontSizeComputationStrategy(numSizes,fontPrefix);
		TagCloud cloudLog = new TagCloudImpl(listTagName,strategy);
		VisualizeTagCloudDecorator decorator = new HTMLTagCloudDecorator();

		return decorator.decorateTagCloud(cloudLog,href,head);
	}


	public void persist(String idUser, String idTag, String idBookmark) {
		entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			entityMgr.createNativeQuery("INSERT INTO TagUser(idUser,idTag,idBookmark) VALUES ("+idUser+","+idTag+","+idBookmark+")" ).executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}
	}

	public void removeByBookmark(String idUser, String idBookmark) {
		entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			entityMgr.createQuery("DELETE FROM "+type.getName()+"  WHERE idBookmark="+idBookmark).executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}
	}


	public String getCloud(String idUser) {
		return this.getCloud(idUser, "");
	}


	public String getCloud(String idUser, String href) {

		List<TagUser> tagsUser = this.listByField("idUser", idUser);
		Map<String, Double> tagFrecuency = this.getTagFrecuency(tagsUser);

		return makeCloud(tagFrecuency,href,"<h3>Usuario</h3>");
	}



	public String getCloudShared(String href) {
		
		List<TagUser> tagsUser = this.listShared();
		Map<String, Double> tagFrecuency = this.getTagFrecuency(tagsUser);

		return makeCloud(tagFrecuency,href,"");
	}

	@SuppressWarnings("unchecked")
	public List<TagUser> listShared() {
		List<TagUser> toReturn= new ArrayList<TagUser>();

		EntityManager entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			toReturn = (List<TagUser>) entityMgr.createNativeQuery("SELECT * FROM TagUser WHERE idBookmark IN (SELECT id FROM Bookmark WHERE shared='true' ) ",TagUser.class).getResultList();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}

		return toReturn;
	}

	public String getCloudCommunity(String idCommunity, String href) {
		List<TagUser> tagsUser = this.listByIdCommunity(idCommunity);
		Map<String, Double> tagFrecuency = this.getTagFrecuency(tagsUser);

		return makeCloud(tagFrecuency,href,"<h3>Community</h3>");
	}

	@SuppressWarnings("unchecked")
	public List<TagUser> listByIdCommunity(String idCommunity) {
		List<TagUser> toReturn= new ArrayList<TagUser>();

		EntityManager entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			toReturn = (List<TagUser>) entityMgr.createNativeQuery("SELECT * FROM TagUser WHERE idBookmark IN (SELECT id FROM Bookmark WHERE shared='true' ) AND idUser IN (SELECT idUser FROM participant WHERE idCommunity="+idCommunity+" ) ",TagUser.class).getResultList();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}

		return toReturn;
	}

}
