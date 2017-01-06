package fr.esigelec.quiz.model;

import java.io.Serializable;

/**
 * 
 * @author Guillaume
 *
 */
public class Proposition implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String libelle;
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
