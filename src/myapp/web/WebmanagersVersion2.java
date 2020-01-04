package myapp.web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import myapp.entity.Activites;
import myapp.entity.NatureCV;
import myapp.entity.Personne;
import myapp.inter.IActiviteManager;
import myapp.inter.IPersonneManager;

@ManagedBean(name="webmanagervers2")
//@SessionScoped
@ApplicationScoped
public class WebmanagersVersion2  implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	IActiviteManager iac;

	@EJB
	IPersonneManager ipers;

	Personne personne = new Personne();



	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Activites getActivites() {
		return activites;
	}

	public void setActivites(Activites activites) {
		this.activites = activites;
	}

	public NatureCV getNaturecv() {
		return naturecv;
	}

	public void setNaturecv(NatureCV naturecv) {
		this.naturecv = naturecv;
	}



	Activites activites = new Activites();

	NatureCV naturecv;

	List<Personne> listPersonne = null;

	/* init process */

	@PostConstruct
	public void init() {

		System.out.println("Create " + this);
		
		
		if(ipers.showAllPersonnes().isEmpty()) {
			System.out.println("PERSONNE IS EMPTY IN DATABASE");
			System.out.println("NOW ADD PERSONN IN DATABASE");

			/* Création des Personnes */
			for(int i = 0 ; i<100; i++) {

				Personne p1 = new Personne("user"+i, "prenomsUser"+i, "myemail"+i+"@gmail.com", "test"+i+".com", EditDate("11/11/2019"), "azerty"+i);
				
		    	//System.out.println(p1.toString());
		    	ipers.addPersonne(p1);
			}
			
			
			
		}

		if( iac.listeAllActivites().isEmpty()) {
			System.out.println("DATA BASE ACTIVITE VIDE NOUS ALLONS PROCEDER A L INSERTION DES DONNEES");
			/* Creation des activites  */
			for(int j=0 ; j<100;j++) {
				Personne p = ipers.findPersonnebyId(j);
				Activites ac = new Activites(EditDate("11/11/2019"), NatureCV.AUTRE, "CERTIFICAT DE QCMx", "reussitex au QCM", "edux.fe", true, p);
				 iac.addActivite(ac);
			}

		}

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


	/* Autres methodes pour les VUES */

	/* POUR LES VUES PERSONNES */
	public List<Personne> findAllPersonne(){
		return ipers.showAllPersonnes();
	}





	public String viewPersonne(int id) {
		personne = ipers.findPersonnebyId(id);
		System.out.println(personne.toString());
		return "showPersonne";
	}



	/* POUR LES ACTIVITES */

	public List<Activites>findAllActivites(){
		return iac.listeAllActivites();
	}





}
