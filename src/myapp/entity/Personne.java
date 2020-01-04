/**
 * @author Aristide , Haffid , Daouda
 * @version : 1.0 
 * Classe entity Personne
 */
package myapp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="personnes")
public class Personne implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String nom;
	
	private String prenoms;
	
	@Column(name = "email",unique = true)
	private String email;
	
	@Column(name = "website")
	private String website;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date dateNaissance;
	
	@Column(name = "password", length = 15,nullable = false)
	private String motdepasse;
	
	
	
	@OneToMany(mappedBy = "personneactivite",cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	private Set<Activites> activites= new HashSet<Activites>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenoms() {
		return prenoms;
	}

	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getMotdepasse() {
		return motdepasse;
	}

	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

	public Set<Activites> getActivites() {
		return activites;
	}

	public void setActivites(Set<Activites> activites) {
		this.activites = activites;
	}
	
	

	
	


	public Personne() {
		super();
		
	}

	public Personne(String nom, String prenoms, String email, String website, Date dateNaissance, String motdepasse) {
		super();
		this.nom = nom;
		this.prenoms = prenoms;
		this.email = email;
		this.website = website;
		this.dateNaissance = dateNaissance;
		this.motdepasse = motdepasse;
	}

	public Personne(String nom, String prenoms, String email, String website, Date dateNaissance, String motdepasse,
			Set<Activites> activites) {
		super();
		this.nom = nom;
		this.prenoms = prenoms;
		this.email = email;
		this.website = website;
		this.dateNaissance = dateNaissance;
		this.motdepasse = motdepasse;
		this.activites = activites;
	}

	

	

	public Personne(Integer id, String nom, String prenoms, String email, String website, Date dateNaissance,
			String motdepasse, Set<Activites> activites) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenoms = prenoms;
		this.email = email;
		this.website = website;
		this.dateNaissance = dateNaissance;
		this.motdepasse = motdepasse;
		this.activites = activites;
	}

	public Personne(Integer id, String nom, String prenoms, String email, String website, Date dateNaissance,
			String motdepasse) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenoms = prenoms;
		this.email = email;
		this.website = website;
		this.dateNaissance = dateNaissance;
		this.motdepasse = motdepasse;
	}

	@Override
	public String toString() {
		return "Personne [id=" + id + ", nom=" + nom + ", prenoms=" + prenoms + ", email=" + email + ", website="
				+ website + ", dateNaissance=" + dateNaissance + ", motdepasse=" + motdepasse + ", activites=" + activites + "]";
	}




	
	
	
	
	
	
	
	
	
	

}
