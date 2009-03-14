package bookmark.br;
import commons.db.Database;


public class Init {
	private static Database database = null;
    private static String basePath;
    private static UserDAO user = null;
    
    public static void init(String path) throws Exception{
    	basePath = path;
    	database = new Database("MySQL","com.mysql.jdbc.Driver", path);
    	user = new UserDAO("",database);
    }
}
