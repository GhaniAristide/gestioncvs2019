package myapp.inter;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.openjpa.persistence.EntityExistsException;

import myapp.entity.Activites;
import myapp.entity.Personne;




public interface IPersonneManager{

Personne login(String email, String password) throws NoResultException;

	/**
	 * methode pour qu'une personne se deconnecte
	 * @return
	 */
	Personne logout();
	/**
	 * Ajouter une personne
	 * @param person
	 * @return
	 * @throws EntityExistsException
	 */
	Personne addPersonne(Personne person) throws EntityExistsException;
	/**
	 * Delete personne
	 * @param Person
	 * @return
	 */
    void deletePersonne(Personne person);

    /**
     * Modifier les informations sur une personne
     * @param person
     * @return
     */
    Personne updatePersonne(Personne person);
    /**
     * Voir la liste de toutes les personnes
     * @return
     */
	List<Personne> showAllPersonnes();


	/**
	 * rechercher une personne par son id
	 * @param p
	 * @return
	 */
	Personne findPersonnebyId(Integer id);

	Personne findOneByEmail(String email);

	/**
	 * Chercher une parsonne par son email
	 * @param email
	 * @return
	 */
	List<Personne> showPersonByEmail(String email);


	/**
	 * chercher une personne par nom
	 * @param nom
	 * @return
	 */
	List<Personne> findPersonByNom(String nom);

	/**
	 * chercher une personne par son Prenoms
	 * @param prenoms
	 * @return
	 */
	List<Personne> findPersonByPrenoms(String prenoms);




	/**
	 * Recherche des personnes en fonction de son CV ( Activite )
	 * @param titre
	 * @return
	 */
	List<Activites> showActivitiesPerson(Personne person);

	List<Activites> showActivitesByTitre(String titre);

}
