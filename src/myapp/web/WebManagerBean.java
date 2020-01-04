package myapp.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.openejb.config.sys.ConnectionManager;

import myapp.entity.Activites;
import myapp.entity.NatureCV;
import myapp.entity.Personne;
import myapp.inter.IconnectManager;

@ManagedBean(name="webmanager")
@SessionScoped
public class WebManagerBean{
	

	@EJB
	IconnectManager webconnector;
	//ConnectionManager managerConnect = new ConnectionManager();
	
	Personne personne = new Personne();
	
	Activites activites = new Activites();
	
	NatureCV naturecv;
	
	List<Personne> listPersonne = null;
	
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
	
	@PostConstruct
	public void init() {
		// création d'une instance de personne et activité si notre db est vide 
		System.out.println("Create " + this);
		//webconnector.showAllPersonne().isEmpty() &&
		if( webconnector.showAllActivites().isEmpty())
		{
			/* Création des Personnes */
			/*for(int i = 0 ; i<100; i++) {
				
				Personne p1 = new Personne("user"+i, "prenomsUser"+i, "myemail"+i+"@gmail.com", "test"+i+".com", EditDate("11/11/2019"), "azerty"+i);
		    	System.out.println(p1.toString());
		    	webconnector.addPersonne(p1);
			} */
			
		   /* Creation des activites  */
			for(int j=0 ; j<100;j++) {
				Personne p = webconnector.findPersonnebyId(j);
				Activites ac = new Activites(EditDate("11/11/2019"), NatureCV.AUTRE, "CERTIFICAT DE QCMx", "reussitex au QCM", "edux.fe", true, p);
				   webconnector.addActivite(ac);
			}

		   /*List<Personne> listP  = findAllPersonne();
		   for(Personne p : listP)
		   System.out.println(p.getEmail() + " "  + p.getNom());
		   
		   List<Activites> lactivies = findAllActivites();
		   for(Activites es :lactivies)System.out.println(es.getDescriptif() + " " + es.getTitre() + " " + es.getNature() + es.getPersonneactivite().getNom()); */
		} 
		
		
	}
	
	public List<Personne> findAllPersonne(){
		return webconnector.showAllPersonne();
	}
	
	
	public List<Activites>findAllActivites(){
		return webconnector.showAllActivites();
	}
	
	public Personne addPersonne(Personne p) {
		return webconnector.addPersonne(p);
	}
	
	public String newPersonne() {
		return "editCourse?faces-redirect=true";
	}
	
	/* public String show(Long n) {
	        List<Personne >personne = webconnector.showPersonneByEmail("test");
	        return "showCourse";
	    } */

}
