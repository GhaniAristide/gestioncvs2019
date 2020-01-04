package myapp.inter;

import java.util.List;

import javax.persistence.NoResultException;

import myapp.entity.Activites;
import myapp.entity.Personne;
public interface IconnectManager {
	
	/**
	 * Login Personne pour qu'une personne se connecte
	 * @param email
	 * @param password
	 * @return
	 * @throws NoResultException
	 */
	Personne login(String email, String password) throws NoResultException;
	
	/**
	 * methode pour qu'une personne se deconnecte
	 * @return
	 */
	Personne logout();
	Personne addPersonne(Personne personne);
	List<Personne> showAllPersonne();
	List<Personne> showPersonneByNom(Personne personne);
	List<Personne> showPersonneByEmail(String email);
	void updatePersonne(Personne personne);
	void updateMyProfilePersonne(Personne personne);
	void deletePersonne(Personne personne);
	
	/***Methode d'interface pour les activites  ***/
	
	Activites addActivite(Activites activites);
	Activites updateActivite(Activites activites);
	Activites updateMyActivite(Activites activites);
	void deleteActivite(Activites activites);
	void deleteActivitebyId(Integer id);
   
    
	/**
	 * Liste des Activites d'une personne concernée
	 * @param p
	 * @return
	 */
	List<Activites> showAllActivites();
	
	/* Toutes les methodes pour l'interface Personne */
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Personne findPersonnebyId(int id);
	
	/**
	 * 
	 * @return
	 */
	 List<Personne> showP();
	/**
	 * Methode pour savoir la personne qui est connectée
	 * @return
	 */
	Personne getLogin();

}
