package br.bookmark.util.webcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class NaiveCrawler {
	private static final String USER_AGENT = "User-agent:";
	private static final String DISALLOW = "Disallow:";
	public static final String REGEXP_HTTP = "<a href=\"http://(.)*\">";
	public static final String REGEXP_RELATIVE = "<a href=\"(.)*\">";
	private int maxNumberUrls;
	private long delayBetweenUrls;
	private int maxDepth;
	private Pattern regexpSearchPattern;
	private Pattern httpRegexp;
	private Pattern relativeRegexp;
	private Map<String, CrawlerUrl> visitedUrls = null;
	private Map<String, Collection<String>> sitePermissions = null;
	private Queue<CrawlerUrl> urlQueue = null;
	private List<CrawlerUrl> crawlOutput = null;
	private int numberItemsSaved = 0;

	public NaiveCrawler(Queue<CrawlerUrl> urlQueue, int maxNumberUrls,
			int maxDepth, long delayBetweenUrls, String regexpSearchPattern)
			throws Exception {
		this.urlQueue = urlQueue;
		this.maxNumberUrls = maxNumberUrls;
		this.delayBetweenUrls = delayBetweenUrls;
		this.maxDepth = maxDepth;
		this.regexpSearchPattern = Pattern.compile(regexpSearchPattern);
		this.visitedUrls = new HashMap<String, CrawlerUrl>();
		this.sitePermissions = new HashMap<String, Collection<String>>();
		this.httpRegexp = Pattern.compile(REGEXP_HTTP);
		this.relativeRegexp = Pattern.compile(REGEXP_RELATIVE);
		crawlOutput = new ArrayList<CrawlerUrl>();
	}

	public List<CrawlerUrl> crawl() throws Exception {
		while (continueCrawling()) {
			CrawlerUrl url = getNextUrl();
			if (url != null) {
				printCrawlInfo();
				String content = getContent(url);
				if (isContentRelevant(content, regexpSearchPattern)) {
					saveContent(url, content);
					Collection<String> urlStrings = extractUrls(content, url);
					addUrlsToUrlQueue(url, urlStrings);
				} else {
					System.out.println(url + " is not relevant ignoring ...");
				}
				Thread.sleep(this.delayBetweenUrls);
			}
		}
		return crawlOutput;
	}

	private boolean continueCrawling() {
		return ((!urlQueue.isEmpty()) && (getNumberOfUrlsVisited() < this.maxNumberUrls));
	}

	private CrawlerUrl getNextUrl() {

		CrawlerUrl nextUrl = null;
		while ((nextUrl == null) && (!urlQueue.isEmpty())) {
			CrawlerUrl crawlerUrl = this.urlQueue.remove();
			if (doWeHavePermissionToVisit(crawlerUrl)
					&& (!isUrlAlreadyVisited(crawlerUrl))
					&& isDepthAcceptable(crawlerUrl)) {
				nextUrl = crawlerUrl;
			}
		}
		return nextUrl;
	}

	private void printCrawlInfo() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("Queue length = ").append(this.urlQueue.size()).append(
				" visited urls=").append(getNumberOfUrlsVisited()).append(
				" site permissions=").append(this.sitePermissions.size());
		System.out.println(sb.toString());
	}

	private int getNumberOfUrlsVisited() {
		return this.visitedUrls.size();
	}

	private boolean isDepthAcceptable(CrawlerUrl crawlerUrl) {
		return crawlerUrl.getDepth() <= this.maxDepth;
	}

	private boolean isUrlAlreadyVisited(CrawlerUrl crawlerUrl) {
		if ((crawlerUrl.isVisited())
				|| (this.visitedUrls.containsKey(crawlerUrl.getUrlString()))) {
			return true;
		}
		return false;
	}

	public boolean doWeHavePermissionToVisit(CrawlerUrl crawlerUrl) {
		if (crawlerUrl == null) {
			return false;
		}
		if (!crawlerUrl.isCheckedForPermission()) {
			crawlerUrl
					.setAllowedToVisit(computePermissionForVisiting(crawlerUrl));
		}
		return crawlerUrl.isAllowedToVisit();
	}

	private boolean computePermissionForVisiting(CrawlerUrl crawlerUrl) {
		URL url = crawlerUrl.getURL();
		boolean retValue = (url != null);
		if (retValue) {
			String host = url.getHost();
			Collection<String> disallowedPaths = this.sitePermissions.get(host);
			if (disallowedPaths == null) {
				disallowedPaths = parseRobotsTxtFileToGetDisallowedPaths(host);
			}
			String path = url.getPath();
			for (String disallowedPath : disallowedPaths) {
				if (path.contains(disallowedPath)) {
					retValue = false;
				}
			}
		}
		return retValue;
	}

	private Collection<String> parseRobotsTxtFileToGetDisallowedPaths(
			String host) {
		String robotFilePath = getContent("http://" + host + "/robots.txt");
		Collection<String> disallowedPaths = new ArrayList<String>();
		if (robotFilePath != null) {
			Pattern p = Pattern.compile(USER_AGENT);
			String[] permissionSets = p.split(robotFilePath);
			String permissionString = "";
			for (String permission : permissionSets) {
				if (permission.trim().startsWith("*")) {
					permissionString = permission.substring(1);
				}
			}
			p = Pattern.compile(DISALLOW);
			String[] items = p.split(permissionString);
			for (String s : items) {
				disallowedPaths.add(s.trim());
			}
		}
		this.sitePermissions.put(host, disallowedPaths);
		return disallowedPaths;
	}

	private String getContent(String urlString) {
		return getContent(new CrawlerUrl(urlString, 0));
	}

	private String getContent(CrawlerUrl url) {
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url.getUrlString());
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));
		String text = null;
		try {
			int statusCode = client.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK) {
			
				text = readContentsFromStream(new InputStreamReader(method
						.getResponseBodyAsStream(), method.getResponseCharSet()));
			}
		} catch (Throwable t) {
			System.out.println(t.toString());
			t.printStackTrace();
		} finally {
			method.releaseConnection();
		}

		markUrlAsVisited(url);
		return text;
	}

	private static String readContentsFromStream(Reader input)
			throws IOException {
		BufferedReader bufferedReader = null;
		if (input instanceof BufferedReader) {
			bufferedReader = (BufferedReader) input;
		} else {
			bufferedReader = new BufferedReader(input);
		}
		StringBuilder sb = new StringBuilder();
		char[] buffer = new char[4 * 1024];
		int charsRead;
		while ((charsRead = bufferedReader.read(buffer)) != -1) {
			sb.append(buffer, 0, charsRead);
		}
		return sb.toString();
	}

	private void markUrlAsVisited(CrawlerUrl url) {
		this.visitedUrls.put(url.getUrlString(), url);
		url.setIsVisited();
	}

	public List<String> extractUrls(String text, CrawlerUrl crawlerUrl) {
		Map<String, String> urlMap = new HashMap<String, String>();
		extractHttpUrls(urlMap, text);
		extractRelativeUrls(urlMap, text, crawlerUrl);
		return new ArrayList<String>(urlMap.keySet());
	}

	private void extractHttpUrls(Map<String, String> urlMap, String text) {
		Matcher m = httpRegexp.matcher(text);
		while (m.find()) {
			String url = m.group();
			String[] terms = url.split("a href=\"");
			for (String term : terms) {
				if (term.startsWith("http")) {
					int index = term.indexOf("\"");
					if (index > 0) {
						term = term.substring(0, index);
					}
					urlMap.put(term, term);
				}
			}
		}
	}

	private void extractRelativeUrls(Map<String, String> urlMap, String text,
			CrawlerUrl crawlerUrl) {
		Matcher m = relativeRegexp.matcher(text);
		URL textURL = crawlerUrl.getURL();
		String host = textURL.getHost();
		while (m.find()) {
			String url = m.group();
			String[] terms = url.split("a href=\"");
			for (String term : terms) {
				if (term.startsWith("/")) {
					int index = term.indexOf("\"");
					if (index > 0) {
						term = term.substring(0, index);
					}
					String s = "http://" + host + term;
					urlMap.put(s, s);
				}
			}
		}
	}

	private void addUrlsToUrlQueue(CrawlerUrl url, Collection<String> urlStrings) {
		int depth = url.getDepth() + 1;
		for (String urlString : urlStrings) {
			if (!this.visitedUrls.containsKey(urlString)) {
				this.urlQueue.add(new CrawlerUrl(urlString, depth));
			}
		}
	}

	public static boolean isContentRelevant(String content,
			Pattern regexpPattern) {
		boolean retValue = false;
		if (content != null) {
			Matcher m = regexpPattern.matcher(content.toLowerCase());
			retValue = m.find();
		}
		return retValue;
	}

	private void saveContent(CrawlerUrl url, String content) throws Exception {
		url.setTitle(extractTitle(content));
		this.crawlOutput.add(url);
		numberItemsSaved++;
	}

	private static String extractTitle(String content) {
		Pattern pattern = Pattern.compile("<title>.*</title>");
		Matcher matcher = pattern.matcher(content);
		String title = "Sem titulo";
		if (matcher.find()) {
			title = matcher.group(0).replaceAll("</?(?i:title)>", "");
		}
		return title;
	}
}
