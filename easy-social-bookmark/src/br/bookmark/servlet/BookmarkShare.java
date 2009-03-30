/* Edita autor */
package br.bookmark.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.bookmark.project.*;
import br.bookmark.db.BookmarkDAO;
import br.bookmark.db.CommentDAO;
import br.bookmark.db.CommunityDAO;
import br.bookmark.db.TagDAO;
import br.bookmark.models.*;

/**
 * Servlet implementation class AutorEdit
 */
public class BookmarkShare extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookmarkShare() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bookmarkShare(request,response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bookmarkShare(request,response);		
	}

	private void bookmarkShare(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String url ="bookmarkList.jsp";		
		
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 

		BookmarkDAO bookmarkDAO =bookmarkInit.getBookmarkDAO();
		TagDAO tagDAO = bookmarkInit.getTagDAO();
		String idUser = session.getAttribute("idUser").toString();
		
		String operation=(request.getParameter("operation")!=null?request.getParameter("operation"):"");
		
		CommunityDAO communityDAO=bookmarkInit.getCommunityDAO();
		Collection<Community> communities=communityDAO.findCommunitiesByIdUser(Long.parseLong(session.getAttribute("idUser").toString()));
		for (Community community : communities){
			if (request.getParameter("community"+community.getId())!=null){
				Bookmark bookmark= new Bookmark();
				bookmark.setIdUser(Long.parseLong(idUser));
				bookmark.setName(request.getParameter("name"));
				bookmark.setUrl(request.getParameter("url"));
				bookmark.setDescription(request.getParameter("description"));
				bookmark.setIdCommunity(community.getId());
				bookmarkDAO.save(bookmark);
			
				String[] nameTags = request.getParameter("tags").split(",");
				Collection<Bookmark> communityBookmarks=bookmarkDAO.findBookmarksByIdCommunity(community.getId());
				for (Bookmark communityBookmark:communityBookmarks){
					if (communityBookmark.getName().equals(bookmark.getName())&&communityBookmark.getUrl().equals(bookmark.getUrl())){
						tagDAO.assignBookmarkWithIdCommunity(nameTags,communityBookmark.getId(),community.getId());
					}
				}
			}
		
		if(request.getParameter("send")!=null){
			if (operation.equals("new")){
				comment.setText(request.getParameter("text"));
				((Comment) comment).setIdBookmark(Long.parseLong(idBookmark));
				comment.setIdUser(Long.parseLong(idUser));
			}
			
		try { 

			url="bookmarkCommunityList.jsp?idCommunity="+bookmarkDAO.findById(Long.parseLong(idBookmark)).getIdCommunity();	   
 
			response.sendRedirect(url); 
		} catch (ClassNotFoundException e) {   
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	

}
