package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.model.Choisir;

/**
 * 
 * @author Sahobau
 * 
 * */

/**
 * 
 * Interface ChosirDAO
 *
 */

public interface ChoisirDAO {
	
	public void ajouterChoix(Choisir choix);
	
	public Choisir getChoix(int id);
	
	public List<Choisir> getListeChoix();
	
	public void supprimerChoix(int id);
	
	public int getNbBonnesReponses(int idQuiz,int idQuestion);
	
	public int getNbReponses(int idQuiz, int idQuestion);

	public int getNbBonnesReponseDunParticipantAuQuiz(int idQuiz, int idParticipant);
}
