package br.bookmark.action.user;

import br.bookmark.action.BaseAction;
import br.bookmark.models.User;
import br.bookmark.services.UserService;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BaseUserAction extends BaseAction implements ModelDriven<User>, Preparable {

	private static final long serialVersionUID = 1L;
	
	protected User user;
    protected String idUser;
    protected UserService service;
    
    public User getModel() {
        return user;
    }
    
    public void setUserService(UserService service) {
        this.service = service;
    }
    
    public void prepare() throws Exception {
        if( idUser==null || "".equals(idUser) ) {
            user = new User();
        } else {
            user = service.findById(idUser);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdUser() {
		return idUser;
	}
	
}
