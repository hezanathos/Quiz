package fr.esigelec.quiz.model;

import java.sql.Timestamp;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Guillaume Sauvage
 */

@Entity
@Table(name = "choisir")
public class Choisir implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * La date de génération du Choix
	 */
	@Column(name = "date", unique = true, nullable = false)
	private Timestamp date;

	/**
	 * La personne du choix
	 */
	@Id
	@ManyToOne
	@JoinColumn(name = "idpersonne")
	private Personne personne;

	/**
	 * le quiz du Choix
	 */
	@Id
	@ManyToOne
	@JoinColumn(name = "idquiz")
	private Quiz quiz;

	/**
	 * La proposition du choix
	 */
	@Id
	@ManyToOne
	@JoinColumn(name = "idproposition")
	private Proposition proposition;

	public Choisir() {
		super();
	}

	public Choisir(Timestamp date, Personne personne, Quiz quiz, Proposition proposition) {
		super();
		this.date = date;
		this.personne = personne;
		this.quiz = quiz;
		this.proposition = proposition;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Proposition getProposition() {
		return proposition;
	}

	public void setProposition(Proposition proposition) {
		this.proposition = proposition;
	}

	@Override
	public String toString() {
		return "Choisir [date=" + date + ", personne=" + personne + ", quiz=" + quiz + ", proposition=" + proposition
				+ "]";
	}

}
