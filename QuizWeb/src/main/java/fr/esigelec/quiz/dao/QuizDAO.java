package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.model.Question;
import fr.esigelec.quiz.model.Quiz;

/**
 * 
 * @author Sahobau
 * 
 */

public interface QuizDAO {

	/**
	 * méthode permettant d'ajouter un quiz 
	 * @param quiz représente le quiz à ajouter 
	 */
	public void ajouterQuiz(Quiz quiz);

	/**
	 * méthode permettant de récupérer la liste des quizzes
	 * @return  la liste des quizzes
	 */
	public List<Quiz> getListeQuizzes();

	/**
	 * méthode permettant de récupérer un quiz 
	 * @param id représente l'identifiant du quiz à récupérer
	 * @return le quiz 
	 */
	public Quiz getQuiz(int id);

	// public void supprimerQuiz(int id);

	/**
	 * méthode permettant de récupérer la question courante 
	 * @param idQuiz l'identifiant du Quiz à récupérer
	 * @return la question désirée
	 */
	public Question getQuestionCourrante(int idQuiz);
	}
