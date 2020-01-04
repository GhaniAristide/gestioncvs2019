package myapp.beans;

/**
 * @author Aristide , Haffid, Daouda
 * @version : 1.0
 * @Since : 13/11/2019
 */
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.openjpa.persistence.EntityExistsException;

import myapp.entity.Activites;
import myapp.entity.Personne;
import myapp.inter.IPersonneManager;



//@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Stateful
public class PersonneManager implements IPersonneManager{

	@PersistenceContext(unitName="myData")
    public EntityManager em;

	private Personne conectpersonne = new Personne();

	boolean isConnected = false;




	public Personne getConectpersonne() {
		return conectpersonne;
	}


	public void setConectpersonne(Personne conectpersonne) {
		this.conectpersonne = conectpersonne;
	}


	public boolean isConnected() {
		return isConnected;
	}


	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}


	@PostConstruct
    public void init() {
        System.out.println("init " + this + " with " + em);
        System.out.println("Hello");
    }


	/**
	 * Ajouter une personne
	 * @return
	 */

	@Lock(LockType.WRITE)
	public Personne addPersonne(Personne person) throws EntityExistsException {
		/*if(person.getId()==null) {
			em.persist(person);
			System.out.println("veuillez ajouter une personne avec un Id valide");
		}
		else {
			person = em.merge(person);
		}  */
		person = em.merge(person);
		//em.persist(em.contains(person) ? person : em.merge(person));
		return person;

	}


	/**
	 * suppression  d'une personne dans la base de données
	 */
	public void deletePersonne(Personne person) {

		em.remove(findPersonnebyId(person.getId()));
	}


	public Personne updatePersonne(Personne person) {

		person = em.merge(person);
		return person;
	}


	/**
	 * recherche sur L'ID d'une personne
	 */
	public Personne findPersonnebyId(Integer id) {
		List<Personne> pe = showAllPersonnes();
		for(Personne p : pe) {
			if(p.getId() == id) {
				return em.find(Personne.class, id);
			}

		}
		System.out.println("La personne recherchée n existe pas dans notre base de données");
		return null;
	}


	/**
	 * affichage de la liste de toutes les personnes
	 */
	@Lock(LockType.READ)
	public List<Personne> showAllPersonnes() {
		TypedQuery<Personne> tQ = em.createQuery("FROM Personne",Personne.class);
		return tQ.getResultList();
	}


	/**
	 * recherche sur une personne par le nom
	 */
	public List<Personne> findPersonByNom(String nom) {
		TypedQuery<Personne> tQ = em.createQuery("FROM Personne p where p.nom like '%" +nom+"%'",Personne.class);
		return tQ.getResultList();
	}

	/**
	 * recherche d'une personne par son prenoms
	 */
	public List<Personne> findPersonByPrenoms(String prenoms) {
		TypedQuery<Personne> tQ = em.createQuery("FROM Personne p where p.prenoms like '%" +prenoms+"%'",Personne.class);
		return tQ.getResultList();
	}

	/**
	 * recherche d'une personne par son adresse email
	 * return une ou plusieurs instance(s) de la classe Personne
	 */
	public List<Personne> showPersonByEmail(String email) {
		TypedQuery<Personne> tQ = em.createQuery("FROM Personne p where p.email like '%" +email+"%'",Personne.class);
		return tQ.getResultList();
	}



	/**
	 *
	 */
	public  List<Activites> showActivitiesPerson(Personne person) {
		TypedQuery<Activites> query;
		if(person.getActivites().isEmpty())
		{
			System.out.println("Aucune Activite pour la personne  "  + person.getNom() + "  que vous cherchez ");
			return null;
		}
		else if(person.getId()!=0) {
			query = em.createQuery("SELECT ac FROM Activites ac WHERE ac.personneactivite.id = :idpers",Activites.class);
			query.setParameter("idpers", person.getId());
			 return query.getResultList();
		}
		else {
			return null;
		}

		//return null;
	}


	/**
	 * Liste des activites d'une personne selon le titre de l'activite
	 */
	public List<Activites> showActivitesByTitre(String titre) {
		TypedQuery<Activites> query;
		query=   em.createQuery("SELECT ac FROM Activites ac ,Personne p WHERE ac.titre =:titre AND p.id=ac.personneactivite.id",Activites.class);
		query.setParameter("titre", titre);
		return query.getResultList();
	}


	/**
	 * Methode pour se connecter
	 */
	public Personne login(String email, String password) throws NoResultException {
		Query requete = null;
		requete = em.createQuery("SELECT myloginperson FROM Personne myloginperson WHERE myloginperson.email = :email AND myloginperson.motdepasse = :password")
				    .setParameter("email",email).setParameter("password", password);
		if(requete.getResultList().size()==1)
			{conectpersonne = (Personne) requete.getSingleResult();
			 System.out.println("connexion effectuée avec succes");

			 return conectpersonne;}

		else {
			logout();
		    return conectpersonne;}

	}


	public Personne logout() {
		conectpersonne = null;
		return conectpersonne;
	}

	@PreDestroy
	public void preDestroy() {
		em.close();
	}


	@Override
	public Personne findOneByEmail(String email) {
		List<Personne> pe = showAllPersonnes();
		for(Personne p: pe) {
			if(p.getEmail() ==email) {
				return em.find(Personne.class, email);
			}
		}
		return null;
	}

}
