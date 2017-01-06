package fr.esigelec.quiz.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author Guillaume
 *
 */
public class Proposition implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "libelle", unique = true, nullable = false)
	private String libelle;
	
	@Column(name = "bonneReponse", unique = true, nullable = false)
	private int bonneReponse;

	public Proposition() {
		super();

	}

	public Proposition(int id, String libelle, int bonneReponse) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.bonneReponse = bonneReponse;
	}

	public int getId() {
		return id;
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

	public int getBonneReponse() {
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
