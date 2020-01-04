package myapp.web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import myapp.entity.Activites;
import myapp.entity.NatureCV;
import myapp.entity.Personne;

import myapp.inter.IActiviteManager;
import myapp.inter.IPersonneManager;

@ManagedBean(name="webmanageruserconnected")
@SessionScoped
public class WebManagerViewUserConnected implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	IActiviteManager iac;

	@EJB
	IPersonneManager ipers;

	Personne personne = new Personne();






	Activites actitves = new Activites();
	NatureCV typecv ;
	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	public Activites getActitves() {
		return actitves;
	}
	public void setActitves(Activites actitves) {
		this.actitves = actitves;
	}
	public NatureCV getTypecv() {
		return typecv;
	}
	public void setTypecv(NatureCV typecv) {
		this.typecv = typecv;
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

}


public Date EditDate(String date1 ) {

		Date aujourdhui = null;

    	SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
    	try {
			aujourdhui = formater.parse(date1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return aujourdhui;
	}

/**
 *
 * @return webpage for edit person
 */
public String newPersonne() {
	return "editCourse?faces-redirect=true";
}

/**
 * methode add personne
 * @return string value on page for application
 */
public String addPersonne() {
	if((personne.getDateNaissance() ==null) && (personne.getMotdepasse() ==null) && (personne.getEmail()==null) && (personne.getNom() ==null)
			 && (personne.getPrenoms() == null)) {
		FacesContext ct = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage("erreur sur l'une des valeur veuillez que tous les champs sont remplis");
		ct.addMessage("erreur saisi", msg);
		ct.validationFailed();
		return "edithPersonne";

	}
	 ipers.addPersonne(personne);
	 return "showPersonne";
}


}
