package myapp.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import myapp.entity.Activites;
import myapp.entity.NatureCV;
import myapp.entity.Personne;
import myapp.inter.IActiviteManager;
import myapp.inter.IPersonneManager;
public class TestActivitesManager {
	
	/*@EJB
	ActivitesManager acManager;
	*/
	@EJB
	IActiviteManager iact;
	
	@EJB
	IPersonneManager iperson;
	/*@EJB
	PersonneManager perManager;
	*/
	@Before
    public void setUp() throws Exception {
        EJBContainer.createEJBContainer().getContext().bind("inject", this);
    }

    @After
    public void tearDown() throws Exception {
        EJBContainer.createEJBContainer().close();
    }
    
    
    @Test
    @Ignore
    public void testAddactivites() {
    	
    	Date aujourdhui = null;

    	SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
    	String date1 ="08/11/2019";
    	try {
			aujourdhui = formater.parse(date1);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	//Personne p = perManager.findPersonnebyId(1);
    	Personne p = iperson.findPersonnebyId(1);
    	Activites ac = new Activites(aujourdhui, NatureCV.AUTRE, "CERTIFICAT DE QCMx", "reussitex au QCM", "edux.fe", true, p);
    	//acManager.addActivite(ac);
    	iact.addActivite(ac);
    	
    	List<Activites> activites = iact.listeAllActivites();
    	for(Activites at : activites) {
    		System.out.println(at.getDescriptif()  + " "  + at.getTitre() + " "  + at.getAnnee() +
    				" " + at.getNature());
    	}
    	
    }
    
    @Test
    public void testShowActivites() {
    	//List<Activites> activites = acManager.listeAllActivites();
    	List<Activites> activites = iact.listeAllActivites();
    	if(activites.isEmpty())System.out.println("Data Activity is Null in database");
    	else
	    	for(Activites at : activites) {
	    		System.out.println(at.getDescriptif()  + " "  + at.getTitre() + " "  + at.getAnnee() +
	    				" " + at.getNature());
	    	}
    }
    
    

}
