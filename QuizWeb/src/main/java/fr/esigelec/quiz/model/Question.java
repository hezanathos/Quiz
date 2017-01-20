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
@Table(name = "question")
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * ID en base de la question
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * libelle en base de la question
	 */
	@Column(name = "libelle", unique = false, nullable = false)
	private String libelle;

	public Question() {
		super();
	}

	public Question(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;

	}

	public Question(String libelle) {
		super();

		this.libelle = libelle;

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

	@Override
	public String toString() {
		return "Question [id=" + id + ", libelle=" + libelle + "]";
	}

}