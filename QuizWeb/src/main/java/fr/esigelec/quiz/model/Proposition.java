package fr.esigelec.quiz.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
	
	@ManyToOne
	@JoinColumn(name="idquestion")
	@Cascade(CascadeType.SAVE_UPDATE)
	private Question question;

	public Proposition() {
		super();

	}
	

	public Proposition(int id, String libelle, int bonneReponse , Question question) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.question = question;
		this.bonneReponse = bonneReponse;
		
	}
	
	public Proposition(String libelle, int bonneReponse, Question question) {
		super();
	
		this.libelle = libelle;
		this.question = question;
		this.bonneReponse = bonneReponse;
	}
	public int getId() {
		return id;
	}

	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
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
