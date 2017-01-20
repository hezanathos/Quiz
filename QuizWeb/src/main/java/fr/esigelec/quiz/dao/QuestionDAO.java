package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.model.Proposition;
import fr.esigelec.quiz.model.Question;

/**
 * 
 * @author Sahobau
 * 
 * interface QuestionDAo
 * */

public interface QuestionDAO {
	
	/**
	 * méthode pour ajouter une question 
	 * @param question l'objet question à ajouter 
	 */
	public void ajouterQuestion(Question question);
	
	/**
	 * méthodes pour récupérer la liste des question
	 * @return la liste des questions
	 */
	public List<Question> getListeQuestions();
	
	/**
	 * Méthode de récupération d'une question
	 * @param id représente l'identifiant de la question recherchée
	 * return la question
	 */
	public Question getQuestion(int id);

	/**
	 * Méthode de récupération des listes des de propositions d'une question
	 * @param idQuestion  représente l'identifiant d'une question
	 * @return la liste des propositions
	 */
	public List<Proposition> getListePropositions(int idQuestion);
	
	// public void supprimerQuestion(int id);


}
