package myapp.test;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import myapp.entity.Activites;
import myapp.entity.Personne;
import myapp.inter.IActiviteManager;
import myapp.inter.IPersonneManager;
import myapp.inter.IconnectManager;
import myapp.web.WebManagerBean;

public class TestConnectManager {

	@EJB
	IconnectManager myconnect;
	
	@EJB
	IPersonneManager iperson;
	
	@EJB
	IActiviteManager iact;
	
	
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
	    public void testShowAllPerson() {
	    	myconnect.showP();
	    	for(Personne pe : myconnect.showP())
	    		System.out.println(pe.getEmail());
	    }
	    
	   
	    
	    
	    @Test
	    @Ignore
	    public void testLogin() {
	    	myconnect.login("jeanxxx.koffi@outlook.fr", "test23");
	    	Personne personneConnected = myconnect.getLogin();
	    	System.out.println(personneConnected.getEmail() + " " +  personneConnected.getNom());
	    	
	    	List<Activites> acall = myconnect.showAllActivites();
	    	System.out.println("---------------- LISTE DES CVS ------------------------");
	    	for(Activites at : acall) {
	    		System.out.println(at.getDescriptif()  + " "  + at.getTitre() + " "  + at.getAnnee() +
	    				" " + at.getNature());
	    	}
	    }
}
