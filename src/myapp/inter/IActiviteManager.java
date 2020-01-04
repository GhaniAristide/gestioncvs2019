package myapp.inter;

import java.util.List;

import javax.ejb.Local;

import myapp.entity.Activites;
@Local
public interface IActiviteManager {
	
	
	/**
	 * Ajouter une activite
	 * @param ac
	 * @return
	 */
	Activites addActivite(Activites ac);
	
	/**
	 * Supprimer une activites
	 * @param acdelete
	 * @return
	 */
	void deleteActivite(Activites acdelete);
	
	/**
	 * Modifier une activite
	 * @param acUpdate
	 * @return
	 */
	Activites updateActivite(Activites acUpdate);
	
	/**
	 * 
	 * @param acUpdate
	 * @return
	 */
	//public Activites updateActiviteWithAuth(Activites acUpdate);
	/**
	 * voir la liste de tous les cv selon un critère defini
	 * @param myActivite
	 * @return
	 */
	//List<Activites> listeAllActivites(String myActivite);
	
	/**
	 * 
	 * @return List<Activites>
	 */
	List<Activites> listeAllActivites();
	

}
