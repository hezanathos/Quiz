package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.model.Choisir;
import fr.esigelec.quiz.model.Personne;

/**
 * 
 * @author Sahobau
 * 
 * Classe interface ChosirDAO
 * 
 * */




public interface ChoisirDAO {
	
	/**
	 * méthode Ajouter un choix
	 * 
	 * @param choix  représente le choix à enregistrer
	 *
	 */
	public void ajouterChoix(Choisir choix);
	
	/**
	 * méthode pour récupérer un choix 
	 * @param id représente l'identitifiant du choix à récupérer 
	 * @return le choix désirer
	 */
	public Choisir getChoix(int id);
	
	/**
	 * méthode pour récupérer une liste des choix 
	 * @return la liste des choix dans la bdd
	 */
	public List<Choisir> getListeChoix();
	
	/**
	 * méthode de suppression d'un choix
	 * @param id représente l'identifiant du choix à supprimer 
	 */
	public void supprimerChoix(int id);
	
	/**
	 * Méthode de récupération du nombre de bonnes réponses à une question d'un quiz
	 * 
	 * @param idQuiz représente l'identifiant du Quiz dans lequel se trouve la question
	 * @param idQuestion représente l'idention de la question dont on veut le nombre de bonnes réponses
	 * 
	 * @return le nombre de bonnes réponses correspondant à la question d'un quiz précis 
	 */
	public int getNbBonnesReponses(int idQuiz,int idQuestion);
	
	/**
	 * méthode de récupération du nombre de réponses d'une question d'un Quiz 
	 * @param idQuiz représente l'identifiant du Quiz dans lequel se trouve la question
	 * @param idQuestion représente l'idention de la question dont on veut le nombre de bonnes réponses
	 * @return le nombre de réponses à la question d'un quiz précis 
	 */
	public int getNbReponses(int idQuiz, int idQuestion);

	/**
	 * méthode de récupération du nombre de bonnes réponses d'un participant  dans un Quiz
	 * @param idQuiz représente l'identifiant du Quiz 
	 * @param idParticipant représente l'identifiant de l'utilisateur pour lequel on cherche le nombre de bonnes réponses	
	 * @return le nombre de bonnes réponses du participant. 
	 */
	public int getNbBonnesReponseDunParticipantAuQuiz(int idQuiz, int idParticipant);
	
	/**
	 * méthode pour récupérer le nombre de participant d'un quiz 
	 * @param idQuiz représente l'identifiant du Quiz 
	 * @return la liste des personnes
	 */
	public List<Personne> getParticipantsQuiz(int idQuiz);
	
	/**
	 * methode pour récupérer le nombre de choix d'un participant
	 * @param idProposition représente l'identifiant de la proposition 
	 * @return le nombre de choix d'un proposition
	 */
	public int getNbChoixDunProposition(int idProposition);
}
