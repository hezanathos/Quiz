package fr.esigelec.quiz.model.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.esigelec.quiz.dao.PersonneDAO;
import fr.esigelec.quiz.dao.QuizDAO;
import fr.esigelec.quiz.model.Personne;
import fr.esigelec.quiz.model.Proposition;
import fr.esigelec.quiz.model.Question;
import fr.esigelec.quiz.model.Quiz;

/**
 * @author Saobane
 * 
 * Classe de tests des méthodes de QuizDAO
 * 
 */

public class QuizDAOTest {
	
	
	/**
	 * Test de Quiz()
	 */
	@SuppressWarnings("serial")
	@Test
	public void testsQuiz() {
		Timestamp timestampDatedebutQuiz = Timestamp.valueOf("2007-09-23 10:10:10.0");
		Timestamp timestampDatedebutQuestion = Timestamp.valueOf("2007-09-23 10:15:10.0");
		List<Question> listeQuestion = new ArrayList<Question>();
		
		listeQuestion.add(new Question("libelle"));
		listeQuestion.add(new Question("libelle2"));

	Quiz quiz = new Quiz("libelle",1,timestampDatedebutQuiz,1,1,timestampDatedebutQuestion,listeQuestion); 
	
	assertEquals(quiz.getLibelle(),"libelle");
	assertEquals(quiz.getEtat(),1);
	assertEquals(quiz.getDateDebutQuiz(),timestampDatedebutQuiz);
	assertEquals(quiz.getNoQuestionCourant(),1);
	assertEquals(quiz.getEtape(),1);
	assertEquals(quiz.getDateDebutQuestion(),timestampDatedebutQuestion);
	assertEquals(quiz.getListeQuestion(),listeQuestion);

	}

	/**
	 * Test de la méthode AjouterQuiz
	 */
	@Test
	public void testAjouterQuiz() {
		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		//**********************		
		Timestamp timestampDatedebutQuiz = Timestamp.valueOf("2007-09-23 10:10:10.0");
		Timestamp timestampDatedebutQuestion = Timestamp.valueOf("2007-09-23 10:15:10.0");
		ArrayList<Question> listeQuestion = new ArrayList<Question>();
		
		listeQuestion.add(new Question("libelle"));
		listeQuestion.add(new Question("libelle2"));

	Quiz quiz = new Quiz("libelleQ",1,timestampDatedebutQuiz,1,1,timestampDatedebutQuestion,listeQuestion);
		
		//**********************
		QuizDAO qdao = (QuizDAO) context.getBean("quizDAOImpl");
		qdao.ajouterQuiz(quiz);
		@SuppressWarnings("unused")
		Quiz quizget=qdao.getQuiz(1);
		
		assertEquals(quiz.getLibelle(),"libelleQ");

		
		
		
	
		/**
		 * Test de la méthode getQuestionCourante
		 */
	
	}
	@Test
	public void testgetQuestionCourrante() {
		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		//**********************		
		Timestamp timestampDatedebutQuiz = Timestamp.valueOf("2007-09-23 10:10:10.0");
		Timestamp timestampDatedebutQuestion = Timestamp.valueOf("2007-09-23 10:15:10.0");
		ArrayList<Question> listeQuestion = new ArrayList<Question>();
		
		listeQuestion.add(new Question("libelle"));
		listeQuestion.add(new Question("libelle2"));

	Quiz quiz = new Quiz("libelleQ",1,timestampDatedebutQuiz,0,1,timestampDatedebutQuestion,listeQuestion);
		
		//**********************
		QuizDAO qdao = (QuizDAO) context.getBean("quizDAOImpl");
		qdao.ajouterQuiz(quiz);
		@SuppressWarnings("unused")
		Quiz quizget=qdao.getQuiz(quiz.getId());
		
		assertEquals(quiz.getLibelle(),"libelleQ");
		assertEquals(quiz.getNoQuestionCourant(),0);
		assertEquals("libelle", qdao.getQuestionCourrante(quizget.getId()).getLibelle());
		quizget.setNoQuestionCourant(quizget.getNoQuestionCourant()+1);
		qdao.ajouterQuiz(quizget);
		assertEquals(1,quizget.getNoQuestionCourant());
		assertEquals("libelle2", qdao.getQuestionCourrante(quizget.getId()).getLibelle());
		

		
		
		
		
	
	}

}
