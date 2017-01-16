package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.model.Choisir;
import fr.esigelec.quiz.model.Personne;

/**
 * 
 * @author Sahobau
 * 
 * */


 // Interface ChosirDAO


public interface ChoisirDAO {
	
	public void ajouterChoix(Choisir choix);
	
	public Choisir getChoix(int id);
	
	public List<Choisir> getListeChoix();
	
	public void supprimerChoix(int id);
	
	public int getNbBonnesReponses(int idQuiz,int idQuestion);
	
	public int getNbReponses(int idQuiz, int idQuestion);

	public int getNbBonnesReponseDunParticipantAuQuiz(int idQuiz, int idParticipant);
	
	public List<Personne> getParticipantsQuiz(int idQuiz);
	
	// get nombre de personne qui a choisi une proposition précis
	public int getNbChoixDunProposition(int idProposition);
}
