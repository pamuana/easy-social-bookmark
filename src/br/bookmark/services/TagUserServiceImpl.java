package br.bookmark.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public String getCloud(String idUser) {
		return this.getCloud(idUser, "");
	}

	@Override
	public String getCloud(String idUser, String href) {
		Map<String, Double> tagFrecuency = new HashMap<String, Double>();
		List<TagUser> tagsUser = this.listByField("idUser", idUser);
		for (TagUser tagUser : tagsUser) {
			Tag tag = tagUser.getTag();
			if (tagFrecuency.containsKey(tag.getName())) {
				tagFrecuency.put(tag.getName(), tagFrecuency.get(tag.getName())+1);
			}else {
				tagFrecuency.put(tag.getName(), 1.);
			}
		}

		int numSizes = 3;
		String fontPrefix = "font-size: ";

		List<TagCloudElement> listTagName = new ArrayList<TagCloudElement>();
		for (String tagName : tagFrecuency.keySet()) {
			listTagName.add(new TagCloudElementImpl(tagName,tagFrecuency.get(tagName).doubleValue()));
		}

		FontSizeComputationStrategy strategy = new LogFontSizeComputationStrategy(numSizes,fontPrefix);
		TagCloud cloudLog = new TagCloudImpl(listTagName,strategy);
		VisualizeTagCloudDecorator decorator = new HTMLTagCloudDecorator();
		
		//System.out.println(decorator.decorateTagCloud(cloudLog));
		return decorator.decorateTagCloud(cloudLog,href);
	}

}
