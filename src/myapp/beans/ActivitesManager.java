package myapp.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import myapp.entity.Activites;
import myapp.entity.Personne;
import myapp.inter.IActiviteManager;

//@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class ActivitesManager implements IActiviteManager{

	
	@PersistenceContext(unitName = "myData")
    public EntityManager em;
	
	private Personne authPersonne = new Personne();
	
	public Personne getAuthPersonne() {
		return authPersonne;
	}

	public void setAuthPersonne(Personne authPersonne) {
		this.authPersonne = authPersonne;
	}
	

	/**
	 * Ajouter une activite
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public Activites addActivite(Activites ac) {
		em.persist(em.contains(ac)? ac:em.merge(ac));
		return  ac;
	}

	/**
	 * Supprimer un CV (une activite)
	 */
	public void deleteActivite(Activites acdelete) {
		em.remove(em.contains(acdelete) ? acdelete: em.merge(acdelete));
		
	}
	
	/**
	 * Modifier des cv version 1.0
	 */
	public Activites updateActivite(Activites acUpdate){
		acUpdate = em.merge(acUpdate);
		return null;
	}


	


	/**
	 * liste de tous les CV 
	 */
	@Lock(LockType.READ)
	public List<Activites> listeAllActivites() {
		TypedQuery<Activites> query= em.createQuery("FROM Activites",Activites.class);
		return query.getResultList();
	}	
	
	
	
	
	/** AUTRES METHODE A UTILISER PLUS TARD **/
	
	/**
	 * Modifier les CV (Activites ) avec authentification d'abord 
	 * Version 1.1
	 
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Activites updateActiviteWithAuth(Activites acUpdate) {
		if(authPersonne!=null) {
			acUpdate = em.merge(acUpdate);
			em.flush();
		}else {
			System.out.println("Erreur impossible de modifier les données sans authentification");
		}
		
		return acUpdate;
	} */

	/**
	 * recherche d'un CV par le titre (Activites )
	 
	public List<Activites> listeAllActivites(String myActivite) {
		TypedQuery<Activites> tQ = em.createQuery("FROM Activites ac WHERE ac.titre like '%" +myActivite+"%'",Activites.class);
		return tQ.getResultList();
	} */

}
