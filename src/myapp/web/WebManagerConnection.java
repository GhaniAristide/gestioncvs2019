package myapp.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import myapp.entity.Personne;
import myapp.inter.IActiviteManager;
import myapp.inter.IPersonneManager;

@ManagedBean(name="ConnectionManager")
@SessionScoped
public class WebManagerConnection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	IActiviteManager iac;

	@EJB
	IPersonneManager ipers;
	
	Personne personne = new Personne();
	Personne authenticatePersonne = new Personne();
    boolean isauthenticated= false;
    
    
	public boolean isIsauthenticated() {
		return isauthenticated;
	}
	public void setIsauthenticated(boolean isauthenticated) {
		this.isauthenticated = isauthenticated;
	}
	public Personne getAuthenticatePersonne() {
	return authenticatePersonne;
	}
	public void setAuthenticatePersonne(Personne authenticatePersonne) {
		this.authenticatePersonne = authenticatePersonne;
	}
	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	
	
	@PostConstruct
	public void init() {
		System.out.println("Logged user" + this);

	}
	
	
	/**
	 * fermer la connection
	 */
	@PreDestroy
	public void logout() {
      authenticatePersonne = ipers.logout();
	}
	
	
	public String connectedPerson() {
		   Personne pe = ipers.login(personne.getEmail(), personne.getMotdepasse());
		  if(ipers.login(personne.getEmail(), personne.getMotdepasse()) !=null)
		  {authenticatePersonne = pe ;
		  isauthenticated = true;
			return "edithPersonne?faces-redirect=true";
		  }
		  FacesMessage facemsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "identifiants incorrect","");
		  FacesContext.getCurrentInstance().addMessage(null, facemsg);
		  return "signin?faces-redirect=true";
	  }

}
