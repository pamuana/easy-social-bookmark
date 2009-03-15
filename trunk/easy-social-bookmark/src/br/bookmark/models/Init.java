package br.bookmark.models;
import br.bookmark.db.Database;


public class Init {
	public static Database database = null;
    public static String urlCon;
    public static UserDAO userDAO = null;
    public static BookmarkDAO bookmarkDAO = null;
    public static CommunityDAO communityDAO = null;
    public static CommentDAO commentDAO = null;
    public static MessageDAO messageDAO = null;
    public static TagDAO tagDAO=null;
    
    
    public static void init(String pUrlCon) throws Exception{
    	urlCon = pUrlCon;
    	database = new Database("MySQL","com.mysql.jdbc.Driver", urlCon);
    	userDAO = new UserDAO("",database);
    	bookmarkDAO = new BookmarkDAO("",database);
    	communityDAO = new CommunityDAO("",database);
    	commentDAO = new CommentDAO("",database);
    	messageDAO = new MessageDAO("",database);
    	tagDAO = new TagDAO("",database);
    }
}
