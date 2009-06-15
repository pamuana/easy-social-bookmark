package br.bookmark.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.bookmark.models.Bookmark;
import br.bookmark.models.TagUser;
import br.bookmark.models.WebBookmark;
import br.bookmark.util.webcrawler.CrawlerUrl;
import br.bookmark.util.webcrawler.NaiveCrawler;

public class WebBookmarkServiceImpl extends GenericServiceImpl<WebBookmark> implements WebBookmarkService {
	
	public WebBookmarkServiceImpl() {
		super();
		this.type = WebBookmark.class;
	}

	@SuppressWarnings("unchecked")
	public void crawlerProcess(String maxNumberUrls, String maxDepth,
			String delayBetweenUrls) {
		
		this.removeAll();
		
		//.. Obter a lista de bookmarks
		List<Bookmark> bookmarks = new ArrayList<Bookmark>();
		EntityManager entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			bookmarks = (List<Bookmark>) entityMgr.createNativeQuery("SELECT * FROM Bookmark ",Bookmark.class).getResultList();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}
		
		// Addicionar Similares utilizando crawler
		for (Bookmark bookmark : bookmarks) {
			List<TagUser> tagsUser = bookmark.getTagsUser();
			
			String regExp = "";
			for (TagUser tagUser : tagsUser) {
				regExp+="|"+tagUser.getTag().getName();
			}
			regExp = regExp.substring(1);
			
			System.out.println(regExp);
			Queue<CrawlerUrl> urlQueue = new LinkedList<CrawlerUrl>();
			urlQueue.add(new CrawlerUrl(bookmark.getUrl(), 0));
			NaiveCrawler crawler;
			try {
				crawler = new NaiveCrawler(urlQueue, Integer.parseInt(maxNumberUrls),
						Integer.parseInt(maxDepth), Long.parseLong(delayBetweenUrls), regExp);
				List<CrawlerUrl> urls = crawler.crawl();
				for (CrawlerUrl resultUrl : urls) {
					//System.out.println(resultUrl.getTitle() + "|" + resultUrl.getUrlString());
					this.addWebBookmark(resultUrl.getTitle(),resultUrl.getUrlString(),bookmark.getId()+"");
				}	
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}		
			
		}

	}

	
	private void addWebBookmark(String title, String urlString,
			String idBookmark) {
		entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			entityMgr.createNativeQuery("INSERT INTO WebBookmark(url,name,description,tags,idBookmark) VALUES ('"+urlString+"','"+title+"','','',"+idBookmark+") ").executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}
	}

	public void removeAll() {
		entityMgr = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = entityMgr.getTransaction();
			tx.begin();

			entityMgr.createQuery("DELETE FROM "+type.getName()+" ").executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if ( tx != null && tx.isActive() )
				tx.rollback();
			throw (RuntimeException)e.getCause();
		}
	}

}
