package br.bookmark.project;
import br.bookmark.db.BookmarkDAO;
import br.bookmark.db.CommentDAO;
import br.bookmark.db.CommunityDAO;
import br.bookmark.db.MessageDAO;
import br.bookmark.db.TagDAO;
import br.bookmark.db.UserDAO;
import br.bookmark.db.util.Database;


public class Init {
	public static Database database = null;
    public static String urlCon="";
    public static String prefixoTable="";
    private UserDAO userDAO = null;
    private BookmarkDAO bookmarkDAO = null;
    private CommunityDAO communityDAO = null;
    private CommentDAO commentDAO = null;
    private MessageDAO messageDAO = null;
    private TagDAO tagDAO=null;
    
    
    public static void init(String pUrlCon,String pPrefixoTable) throws Exception{
    	urlCon = pUrlCon;
    	prefixoTable = pPrefixoTable;
    	database = new Database("MySQL","com.mysql.jdbc.Driver", urlCon);
    }
    
    public Init() throws Exception{
    	this.setUserDAO(new UserDAO(prefixoTable,database));
    	this.setBookmarkDAO(new BookmarkDAO(prefixoTable,database));
    	this.setCommunityDAO(new CommunityDAO(prefixoTable,database));
    	this.setCommentDAO(new CommentDAO(prefixoTable,database));
    	this.setMessageDAO(new MessageDAO(prefixoTable,database));
    	this.setTagDAO(new TagDAO(prefixoTable,database));
    }

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setBookmarkDAO(BookmarkDAO bookmarkDAO) {
		this.bookmarkDAO = bookmarkDAO;
	}

	public BookmarkDAO getBookmarkDAO() {
		return bookmarkDAO;
	}

	public void setCommunityDAO(CommunityDAO communityDAO) {
		this.communityDAO = communityDAO;
	}

	public CommunityDAO getCommunityDAO() {
		return communityDAO;
	}

	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}

	public MessageDAO getMessageDAO() {
		return messageDAO;
	}

	public void setTagDAO(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}

	public TagDAO getTagDAO() {
		return tagDAO;
	}
}
