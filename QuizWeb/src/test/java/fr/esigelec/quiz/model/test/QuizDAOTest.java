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
 */

public class QuizDAOTest {
	
	
	@SuppressWarnings("serial")
	@Test
	public void testsQuiz() {
		Timestamp timestampDatedebutQuiz = Timestamp.valueOf("2007-09-23 10:10:10.0");
		Timestamp timestampDatedebutQuestion = Timestamp.valueOf("2007-09-23 10:15:10.0");
		ArrayList<Question> listeQuestion = new ArrayList<Question>();
		
		listeQuestion.add(new Question("libelle",new ArrayList<Proposition>() 
		{{ 
			add(new Proposition("libellePropo1",1));
			add(new Proposition("libellePropo2",2));
		}}
			
		));

	Quiz quiz = new Quiz("libelle",1,timestampDatedebutQuiz,1,1,timestampDatedebutQuestion,listeQuestion); 
	
	assertEquals(quiz.getLibelle(),"libelle");
	assertEquals(quiz.getEtat(),1);
	assertEquals(quiz.getDateDebutQuiz(),timestampDatedebutQuiz);
	assertEquals(quiz.getNoQuestionCourant(),1);
	assertEquals(quiz.getEtape(),1);
	assertEquals(quiz.getDateDebutQuestion(),timestampDatedebutQuestion);
	assertEquals(quiz.getListeQuestion(),listeQuestion);

	}

	@Test
	public void testAjouterQuiz() {
		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		//**********************		
		Timestamp timestampDatedebutQuiz = Timestamp.valueOf("2007-09-23 10:10:10.0");
		Timestamp timestampDatedebutQuestion = Timestamp.valueOf("2007-09-23 10:15:10.0");
		ArrayList<Question> listeQuestion = new ArrayList<Question>();
		
		listeQuestion.add(new Question("libelle",new ArrayList<Proposition>() 
		{{ 
			add(new Proposition("libellePropo1",1));
			add(new Proposition("libellePropo2",2));
		}}
			
		));

	Quiz quiz = new Quiz("libelle",1,timestampDatedebutQuiz,1,1,timestampDatedebutQuestion,listeQuestion);
		
		//**********************
		QuizDAO qdao = (QuizDAO) context.getBean("quizDAOImpl");
		qdao.ajouterQuiz(quiz);
		@SuppressWarnings("unused")
		Quiz quizget=qdao.getQuiz(1);
		
		assertEquals(quiz.getLibelle(),"libelle");

	
	}

	@Test
	public void testGetListeQuizzes() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetQuiz() {
		//fail("Not yet implemented");
	}

}
