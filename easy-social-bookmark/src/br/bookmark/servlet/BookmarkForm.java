/* Edita autor */
package br.bookmark.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.bookmark.project.*;
import br.bookmark.db.BookmarkDAO;
import br.bookmark.db.CommunityDAO;
import br.bookmark.db.TagDAO;
import br.bookmark.models.*;


/**
 * Servlet implementation class AutorEdit
 */
public class BookmarkForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookmarkForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			bookmarkForm(request,response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			bookmarkForm(request,response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void bookmarkForm(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, Exception {
		String operation=request.getParameter("operation");
		if ("new".equals(operation)){
			bookmarkNew(request, response);
		}else if ("edit".equals(operation)){
			bookmarkEdit(request, response);
		}else if ("share".equals(operation)){
			bookmarkShare(request, response);
		}
	}
	
	private void bookmarkEdit(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String idUser = session.getAttribute("idUser").toString();
		String url ="bookmarkList.jsp";
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 

		BookmarkDAO bookmarkDAO = bookmarkInit.getBookmarkDAO();
		TagDAO tagDAO = bookmarkInit.getTagDAO();
		Bookmark bookmark=bookmarkDAO.findById(Long.parseLong(request.getParameter("idBookmark")));
		bookmark.setName(request.getParameter("name"));
		bookmark.setUrl(request.getParameter("url"));
		bookmark.setDescription(request.getParameter("description"));
		
		
		String[] nameTags = request.getParameter("tags").split(",");
		Collection<String> collectionNameTags=new ArrayList<String>();
		for (String editNameTag:nameTags){
			collectionNameTags.add(editNameTag);
		}
		
		Collection<Tag> currentTags=tagDAO.findTagsByIdBookmark(bookmark.getId());
		for (Tag currentTag:currentTags){
			if (!collectionNameTags.contains(currentTag.getName())){
				tagDAO.deassignBookmark(currentTag.getId(),bookmark.getId());
			}
		}
		
		tagDAO.assignBookmarkWithIdUser(nameTags,bookmark.getId(),Long.parseLong(idUser));

		try { 
			bookmarkDAO.save(bookmark);
			url="bookmarkList.jsp";	    
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
	
	private void bookmarkNew(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String idUser = session.getAttribute("idUser").toString();
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 
		
		Bookmark bookmark= new Bookmark();
		BookmarkDAO bookmarkDAO =bookmarkInit.getBookmarkDAO();
		TagDAO tagDAO = bookmarkInit.getTagDAO();
		bookmark.setIdUser(Long.parseLong(idUser));
		bookmark.setName(request.getParameter("name"));
		bookmark.setUrl(request.getParameter("url"));
		bookmark.setDescription(request.getParameter("description"));
		bookmarkDAO.save(bookmark);
		
		String[] nameTags = request.getParameter("tags").split(",");
		Collection<Bookmark> userBookmarks=bookmarkDAO.findBookmarksByIdUser(Long.parseLong(idUser));
		for (Bookmark userBookmark:userBookmarks){
			if (userBookmark.getName().equals(bookmark.getName())&&userBookmark.getUrl().equals(bookmark.getUrl())){
				tagDAO.assignBookmarkWithIdUser(nameTags,userBookmark.getId(),Long.parseLong(idUser));
			}
		}

		try { 
			bookmarkDAO.save(bookmark);    
			response.sendRedirect("bookmarkList.jsp"); 
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

	private void bookmarkShare(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String url ="bookmarkList.jsp";		
		
		Init bookmarkInit = (Init) session.getAttribute("bookmarkInit"); 

		BookmarkDAO bookmarkDAO =bookmarkInit.getBookmarkDAO();
		TagDAO tagDAO = bookmarkInit.getTagDAO();
		String idUser = session.getAttribute("idUser").toString();
		
		//String operation=(request.getParameter("operation")!=null?request.getParameter("operation"):"");
		
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
						tagDAO.assignBookmarkWithIdCommunity(nameTags,communityBookmark.getId(),community.getId(),tagDAO.findTagsByIdCommunity(community.getId()));
					}
				}
			}
		}
			
		try { 

			url="bookmarkList.jsp";	   
			response.sendRedirect(url); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
