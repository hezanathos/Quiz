package fr.esigelec.quiz.dao;


import java.util.List;

import fr.esigelec.quiz.model.Quiz;

/**
 * 
 * @author Sahobau
 * 
 * */

public interface QuizDAO {
	
	public void ajouterQuiz(Quiz quiz);
	
	public List<Quiz> getListeQuizzes();
	
	public Quiz getQuiz(int id);
	
//	public void supprimerQuiz(int id);
	

}
