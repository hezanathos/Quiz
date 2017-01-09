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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "libelle", unique = true, nullable = false)
	private String libelle;

	@Column(name = "dateDebutQuiz", unique = true, nullable = false)
	private Timestamp dateDebutQuiz;

	@Column(name = "noQuestionCourant", unique = true, nullable = false)
	private int noQuestionCourant;

	@Column(name = "etape", unique = true, nullable = false)
	private int etape;

	@Column(name = "dateDebutQuestion", unique = true, nullable = false)
	private Timestamp dateDebutQuestion;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "questionduquizz", joinColumns = { @JoinColumn(name = "idQuizz") }, inverseJoinColumns = {
			@JoinColumn(name = "idQuestion") })
	private List<Question> listeQuestion = new ArrayList<Question>();

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

	public Quiz(String libelle, Timestamp dateDebutQuiz, int noQuestionCourant, int etape, Timestamp dateDebutQuestion,
			ArrayList<Question> listeQuestion) {
		super();

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
