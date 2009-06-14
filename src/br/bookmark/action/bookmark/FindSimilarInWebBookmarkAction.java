package br.bookmark.action.bookmark;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import br.bookmark.models.Bookmark;
import br.bookmark.models.TagUser;
import br.bookmark.util.webcrawler.CrawlerUrl;
import br.bookmark.util.webcrawler.NaiveCrawler;


public class FindSimilarInWebBookmarkAction extends BaseBookmarkAction {

	private static final long serialVersionUID = 1L;
	private List<Bookmark> bookmarks;

	public void setBookmarks(List<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}

	public List<Bookmark> getBookmarks() {
		return bookmarks;
	}

	public void prepare() throws Exception {
		if( this.idBookmark ==null || "".equals(this.idBookmark) ) {
			this.bookmark = new Bookmark();
		} else {
			this.bookmark = service.findById(this.idBookmark);
		}



		this.bookmarks = new ArrayList<Bookmark>();

		String url = this.bookmark.getUrl();
		int maxNumberUrls = 10;
		int maxDepth = 5;
		long delayBetweenUrls = 1000L;




		List<TagUser> tagsUser = bookmark.getTagsUser();
		List<CrawlerUrl> urls = new ArrayList<CrawlerUrl>();
		//NaiveCrawler crawler = null;
		for (TagUser tagUser : tagsUser) {

			Queue<CrawlerUrl> urlQueue = new LinkedList<CrawlerUrl>();
			urlQueue.add(new CrawlerUrl(url, 0));

			String regexp = tagUser.getTag().getName();
			NaiveCrawler crawler=new NaiveCrawler(urlQueue, maxNumberUrls,
					maxDepth, delayBetweenUrls, regexp);
			//crawler.setRegExpSearchPattern(regexp);
			//crawler.setUrlQueue(urlQueue);

			urls.addAll(crawler.crawl());
		}

		for (CrawlerUrl crawlerUrl : urls) {
			System.out.println(crawlerUrl.getUrlString());
		}


	}

	public String execute() throws Exception {



		return SUCCESS;
	}



}
