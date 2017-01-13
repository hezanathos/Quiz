package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.model.Proposition;
import fr.esigelec.quiz.model.Question;

/**
 * 
 * @author Sahobau
 * 
 * */

public interface QuestionDAO {
	
	public void ajouterQuestion(Question question);
	
	public List<Question> getListeQuestions();
	
	public Question getQuestion(int id);

	public List<Proposition> getListePropositions(int idQuestion);
	
	// public void supprimerQuestion(int id);
<<<<<<< HEAD
=======
	

>>>>>>> 3c7298f148d5be529bf959ac4fbc411e05057518

}
