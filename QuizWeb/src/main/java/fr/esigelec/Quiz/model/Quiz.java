package fr.esigelec.Quiz.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Guilaume
 *
 */

@Entity
@Table(name = "XXXXX")
public class Quiz implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "SSO_ID", unique = true, nullable = false)
	private String libelle;

	@Column(name = "SSO_ID", unique = true, nullable = false)
	private Timestamp dateDebutQuiz;

	@Column(name = "SSO_ID", unique = true, nullable = false)
	private int noQuestionCourant;

	@Column(name = "SSO_ID", unique = true, nullable = false)
	private int etape;

	@Column(name = "SSO_ID", unique = true, nullable = false)
	private Timestamp dateDebutQuestion;

	@Column(name = "SSO_ID", unique = true, nullable = false)
	private ArrayList<Question> listeQuestion = new ArrayList<Question>();

	public Quiz() {
		super();
	}

	public Quiz(int id, String libelle, Timestamp dateDebutQuiz, int noQuestionCourant, int etape,
			Timestamp dateDebutQuestion, ArrayList<Question> listeQuestion) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.dateDebutQuiz = dateDebutQuiz;
		this.noQuestionCourant = noQuestionCourant;
		this.etape = etape;
		this.dateDebutQuestion = dateDebutQuestion;
		this.listeQuestion = listeQuestion;
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

	public Timestamp getDateDebutQuiz() {
		return dateDebutQuiz;
	}

	public void setDateDebutQuiz(Timestamp dateDebutQuiz) {
		this.dateDebutQuiz = dateDebutQuiz;
	}

	public int getNoQuestionCourant() {
		return noQuestionCourant;
	}

	public void setNoQuestionCourant(int noQuestionCourant) {
		this.noQuestionCourant = noQuestionCourant;
	}

	public int getEtape() {
		return etape;
	}

	public void setEtape(int etape) {
		this.etape = etape;
	}

	public Timestamp getDateDebutQuestion() {
		return dateDebutQuestion;
	}

	public void setDateDebutQuestion(Timestamp dateDebutQuestion) {
		this.dateDebutQuestion = dateDebutQuestion;
	}

	public ArrayList<Question> getListeQuestion() {
		return listeQuestion;
	}

	public void setListeQuestion(ArrayList<Question> listeQuestion) {
		this.listeQuestion = listeQuestion;
	}

	@Override
	public String toString() {
		return "QuizDTO [id=" + id + ", libelle=" + libelle + ", dateDebutQuiz=" + dateDebutQuiz
				+ ", noQuestionCourant=" + noQuestionCourant + ", etape=" + etape + ", dateDebutQuestion="
				+ dateDebutQuestion + ", listeQuestion=" + listeQuestion + "]";
	}

}
