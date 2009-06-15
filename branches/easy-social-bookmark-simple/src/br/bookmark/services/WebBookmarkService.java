package br.bookmark.services;

import br.bookmark.models.WebBookmark;

public interface WebBookmarkService extends GenericService<WebBookmark> {

	public void removeAll();
	
	public void crawlerProcess(String maxNumberUrls, String maxDepth,
			String delayBetweenUrls);

}
