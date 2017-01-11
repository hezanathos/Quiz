package fr.esigelec.quiz.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Guillaume
 *
 */
//ss
@Entity
@Table(name = "proposition")
public class Proposition implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "libelle", unique = true, nullable = false)
	private String libelle;
	
	@Column(name = "bonneReponse", unique = true, nullable = false)
	private int bonneReponse;
	
	@Column(name = "idQuetion", unique = true, nullable = false)
	private int idQuetion;

	public Proposition() {
		super();

	}
	

	public Proposition(int id, String libelle, int bonneReponse , int idQuestion) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.idQuetion = idQuestion;
		this.bonneReponse = bonneReponse;
		
	}
	
	public Proposition(String libelle, int bonneReponse, int idQuestion) {
		super();
	
		this.libelle = libelle;
		this.idQuetion = idQuestion;
		this.bonneReponse = bonneReponse;
	}
	public int getId() {
		return id;
	}

	public int getIdQuetion() {
		return idQuetion;
	}


	public void setIdQuetion(int idQuetion) {
		this.idQuetion = idQuetion;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int isBonneReponse() {
		return bonneReponse;
	}

	public void setBonneReponse(int bonneReponse) {
		this.bonneReponse = bonneReponse;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Proposition [id=" + id + ", libelle=" + libelle + ", bonneReponse=" + bonneReponse + "]";
	}

}
