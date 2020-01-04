package myapp.beans;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import myapp.entity.Activites;
import myapp.entity.Personne;
import myapp.inter.IActiviteManager;
import myapp.inter.IPersonneManager;
import myapp.inter.IconnectManager;

@Stateful
public class ConnectManager implements IconnectManager {

	@PersistenceContext(unitName="myData")
    public EntityManager em;
	
	private Personne conectpersonne = new Personne();
	
	
	@EJB
	private IPersonneManager perManager;
	
	@EJB
	private IActiviteManager actManager;
	

	

	
	
	/* Methode pour le manager Personne */

	
	public Personne addPersonne(Personne personne) {
		return perManager.addPersonne(personne);
		
	}

	
	public  List<Personne> showP(){
		return perManager.showAllPersonnes();
	}
	public List<Personne> showAllPersonne() {
		return perManager.showAllPersonnes();
	}
	
	public List<Personne> showPersonneByNom(Personne personne) {
		return perManager.findPersonByNom(personne.getNom());
		
	}
	
	public Personne findPersonnebyId(int id) {
		return perManager.findPersonnebyId(id);
	}

	
	public List<Personne> showPersonneByEmail(String email) {
		return perManager.showPersonByEmail(email);
		
	}

	
	public void updatePersonne(Personne personne) {
		perManager.updatePersonne(personne);
		
	}
	
	
	public void updateMyProfilePersonne(Personne personne) {
		   
		   conectpersonne.setNom(personne.getNom());
		   conectpersonne.setPrenoms(personne.getPrenoms());
		   conectpersonne.setDateNaissance(personne.getDateNaissance());
		   conectpersonne.setEmail(personne.getEmail());
		   conectpersonne.setMotdepasse(personne.getMotdepasse());
		   conectpersonne.setWebsite(personne.getWebsite());
		   
		   perManager.updatePersonne(conectpersonne);
	}

	
	public void deletePersonne(Personne personne) {
		perManager.deletePersonne(personne);
		
	}

	
	
	/** 
	 * Methode pour le manager Activites**/
	
	public Activites addActivite(Activites activites) {
		activites.setPersonneactivite(conectpersonne);
		return actManager.addActivite(activites);
		
	}

	
	
	
	public Activites updateActivite(Activites activites) {
		
		return actManager.updateActivite(activites);
		
	}
	
	public List<Activites> showAllActivites(){
		return actManager.listeAllActivites();
	}

	@Override
	public void deleteActivite(Activites activites) {
		actManager.deleteActivite(activites);
		
	}

	@Override
	public void deleteActivitebyId(Integer id) {
		Set<Activites> ac = conectpersonne.getActivites();
		
		for(Activites a : ac)
			  if(a.getIdactivite() ==id)
		        actManager.deleteActivite(a);
		      
	}



	@Override
	public Personne getLogin() {
		
		return conectpersonne;
	}
	
	
	public Personne getConectpersonne() {
		return conectpersonne;
	}

	public void setConectpersonne(Personne conectpersonne) {
		this.conectpersonne = conectpersonne;
	}



	@Override
	public Activites updateMyActivite(Activites activites) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Personne login(String email, String password) throws NoResultException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Personne logout() {
		// TODO Auto-generated method stub
		return null;
	}

}
