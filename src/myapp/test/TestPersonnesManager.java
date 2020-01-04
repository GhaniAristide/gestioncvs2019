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
import myapp.entity.Personne;
import myapp.inter.IActiviteManager;
import myapp.inter.IPersonneManager;

public class TestPersonnesManager {

	/*
	@EJB
	PersonneManager persoManager;
	*/
	/*@EJB
	ActivitesManager acManager;
	*/
	
	@EJB
	IPersonneManager ipersone;
	
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
	    public void testAddPersonne() {
	    	//Activites ac1 = new 
	    	Date aujourdhui = null;

	    	SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
	    	String date1 ="13/11/2019";
	    	try {
				aujourdhui = formater.parse(date1);
			} catch (Exception e) {
				// TODO: handle exception
			}
	        
	    	Personne p1 = new Personne("KOFFI", "JOOE", "joejoe@gmail.com", "test.com", aujourdhui, "azerty");
	    	System.out.println(p1.toString());
	    	//persoManager.addPersonne(p1);
	    	ipersone.addPersonne(p1);
	    	
	    }
	    
	    @Test
	    public void testshowPersonne() {
	    	System.out.println("------------------show all personnes ----------------");
	    	//List<Personne> personne =  persoManager.showAllPersonnes();
	    	List<Personne> personne =  ipersone.showAllPersonnes();
	    	if(personne.size() == 0 )System.out.println("DATA IS NULL");
	    	for(Personne  p:personne) {
	    		
	    		System.out.println(p.getId() + " " + p.getEmail() + " "  + p.getNom()  +"  "+p.getDateNaissance() + " " + p.getMotdepasse());
	    	}
	    }
	    
	    @Test
	    @Ignore
	    public void testSearchPersonneById() {
	    	//Personne p = persoManager.findPersonnebyId(3);
	    	Personne p = ipersone.findPersonnebyId(1);
	    	System.out.println(p.getEmail() +" "  + p.getNom() + " "  + p.getPrenoms() + " "
	    			 + p.getDateNaissance()); 
	    }
	    
	    @Test
	    @Ignore
	    public void testPersonneByActiviteTitre() {
	        //Personne p = persoManager.findPersonnebyId(3);
	    	Personne p = ipersone.findPersonnebyId(3);
	        //List<Activites> ac = persoManager.showActivitiesPerson(p);
	        List<Activites> ac = ipersone.showActivitiesPerson(p);
	        if(p.getActivites().isEmpty()) {
	        	System.out.println("No contain");
	        }
	        else {for(Activites a : ac)
	        	System.out.println("Le titre CV  de la personne "  +  p.getNom() + " " + " EST"+ " "
                        +a.getTitre() );
	}
	        
	    	
	    }
}
