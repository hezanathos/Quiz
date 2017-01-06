package fr.esigelec.quiz.model;

import java.io.Serializable;

/**
 * 
 * @author Guillaume SAUVAGE
 *
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Question")
public class Question implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "libelle", unique = true, nullable = false)
	private String libelle;
	
	
	public Question(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}

	public Question() {
		super();
	
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}