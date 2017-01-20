package fr.esigelec.quiz.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.JoinColumn;

/**
 * 
 * @author Guillaume Sauvage
 *
 */

@Entity
@Table(name = "quiz")
public class Quiz implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id en base du quiz
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * libelle en base du quiz
	 */
	@Column(name = "libelle", unique = false, nullable = false)
	private String libelle;

	/**
	 * etat en base du quiz
	 */
	@Column(name = "etat", unique = false, nullable = false)
	private int etat;
	/**
	 * date du debut en base du quiz
	 */
	@Column(name = "dateDebutQuiz", unique = true, nullable = false)
	private Timestamp dateDebutQuiz;
	/**
	 * numero de la question en base du quiz
	 */
	@Column(name = "noQuestionCourant", unique = false, nullable = false)
	private int noQuestionCourant;

	/**
	 * etape en base du quiz
	 */
	@Column(name = "etape", unique = false, nullable = false)
	private int etape;

	/**
	 * date de la question en base du quiz
	 */
	@Column(name = "dateDebutQuestion", unique = false, nullable = false)
	private Timestamp dateDebutQuestion;

	/**
	 * liste des question en base du quiz
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "questionduquiz", joinColumns = { @JoinColumn(name = "idQuiz") }, inverseJoinColumns = {
			@JoinColumn(name = "idQuestion") })
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<Question> listeQuestion = new ArrayList<Question>();

	public Quiz() {
		super();
	}

	public Quiz(int id, String libelle, int etat, Timestamp dateDebutQuiz, int noQuestionCourant, int etape,
			Timestamp dateDebutQuestion, List<Question> listeQuestion) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.etat = etat;
		this.dateDebutQuiz = dateDebutQuiz;
		this.noQuestionCourant = noQuestionCourant;
		this.etape = etape;
		this.dateDebutQuestion = dateDebutQuestion;
		this.listeQuestion = listeQuestion;
	}

	public Quiz(String libelle, int etat, Timestamp dateDebutQuiz, int noQuestionCourant, int etape,
			Timestamp dateDebutQuestion, List<Question> listeQuestion) {
		super();

		this.libelle = libelle;
		this.etat = etat;
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

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
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

	public List<Question> getListeQuestion() {
		return listeQuestion;
	}

	public void setListeQuestion(ArrayList<Question> listeQuestion) {
		this.listeQuestion = listeQuestion;
	}

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", libelle=" + libelle + ", dateDebutQuiz=" + dateDebutQuiz + ", noQuestionCourant="
				+ noQuestionCourant + ", etape=" + etape + ", dateDebutQuestion=" + dateDebutQuestion
				+ ", listeQuestion=" + listeQuestion + "]";
	}

}
