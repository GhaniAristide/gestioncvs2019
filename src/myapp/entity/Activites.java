package myapp.entity;

/**
 * @Version 1.0
 * @author Aristide KOFFI & HOUT & Daouda
 * @since 13/11/2019
 */
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import myapp.entity.NatureCV;

@Entity
@Table(name="activites")
public class Activites implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8438334641598922521L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idactivite;

	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date annee;
	
	@Enumerated(EnumType.STRING)
	private NatureCV nature;
	

	
	@Column(length = 200, nullable = true)
	private String titre;

	
	@Column(length = 500)
	private String descriptif;
	
	
	//@Column(length = 300)
		private String website;
		
		@Transient
		private boolean editable = false;


		@ManyToOne(optional = true ,cascade = {CascadeType.MERGE})
		//@JoinColumn(name = "persone")
		private Personne personneactivite;

	public Integer getIdactivite() {
		return idactivite;
	}


	public void setIdactivite(Integer idactivite) {
		this.idactivite = idactivite;
	}


	public Date getAnnee() {
		return annee;
	}


	public void setAnnee(Date annee) {
		this.annee = annee;
	}


	public NatureCV getNature() {
		return nature;
	}


	public void setNature(NatureCV nature) {
		this.nature = nature;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getDescriptif() {
		return descriptif;
	}


	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}


	public String getWebsite() {
		return website;
	}


	public void setWebsite(String website) {
		this.website = website;
	}


	public boolean isEditable() {
		return editable;
	}


	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Personne getPersonneactivite() {
		return personneactivite;
	}


	public void setPersonneactivite(Personne personneactivite) {
		this.personneactivite = personneactivite;
	}

	public Activites() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Activites(Date annee, NatureCV nature, String titre, String descriptif, String website, boolean editable,
			Personne personneactivite) {
		super();
		this.annee = annee;
		this.nature = nature;
		this.titre = titre;
		this.descriptif = descriptif;
		this.website = website;
		this.editable = editable;
		this.personneactivite = personneactivite;
	}
    

	
	
}
