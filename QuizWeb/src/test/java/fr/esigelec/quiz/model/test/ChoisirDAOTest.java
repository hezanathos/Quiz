package fr.esigelec.quiz.model.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.esigelec.quiz.dao.ChoisirDAO;
import fr.esigelec.quiz.dao.PersonneDAO;
import fr.esigelec.quiz.dao.PropositionDAO;
import fr.esigelec.quiz.dao.QuestionDAO;
import fr.esigelec.quiz.dao.QuizDAO;
import fr.esigelec.quiz.model.Choisir;
import fr.esigelec.quiz.model.Personne;
import fr.esigelec.quiz.model.Proposition;
import fr.esigelec.quiz.model.Question;
import fr.esigelec.quiz.model.Quiz;

public class ChoisirDAOTest {

	@Test
	public void testChoixDAO() {

		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		
	/*	Personne personne1 = new Personne("Sauvage", "Guillaume", "ghfsdfghjgdf@gmail.fr", "mdp", 0);
		Question q1 = new Question("libQ1");
		Question q2 = new Question("libQ2");
		
		ArrayList<Question> listeQuestion = new ArrayList<Question>();
		listeQuestion.add(q1);
		listeQuestion.add(q1);
		
		Timestamp timestampDatedebutQuiz = Timestamp.valueOf("2007-09-23 10:10:10.0");
		Timestamp timestampDatedebutQuestion = Timestamp.valueOf("2007-09-23 10:15:10.0");
		
		Quiz quiz = new Quiz("libelle", 1, timestampDatedebutQuiz, 1, 1, timestampDatedebutQuestion, listeQuestion);
		Proposition prop1 = new Proposition("libelleprop1", 1, q1);

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		Choisir choix = new Choisir(timestamp, personne1, quiz, prop1);
		
		ChoisirDAO cdao = (ChoisirDAO) context.getBean("choisirDAOImpl");
		cdao.ajouterChoix(choix);
		Choisir c = cdao.getChoix(0);
		assertEquals(c.getPersonne(), personne1);*/
		
		Personne personne1 = new Personne("Sauvage", "Guillaume", "ph@gmail.fr", "mdp", 0);
		PersonneDAO pDAO1 = (PersonneDAO) context.getBean("personneDAOImpl");
		pDAO1.ajouterPersonne(personne1);
		Personne personne2 = pDAO1.getPersonneByEmail("ph@gmail.fr");
	

		Question q1 = new Question("libQ1");
		QuestionDAO qDAO1 = (QuestionDAO) context.getBean("questionDAOImpl");
		qDAO1.ajouterQuestion(q1);
		

		Proposition prop1 = new Proposition("libelleprop1", 1, q1);
		PropositionDAO propDAO1 = (PropositionDAO) context.getBean("propositionDAOImpl");
		propDAO1.ajouterProposition(prop1);
		
		Timestamp timestampDatedebutQuiz = Timestamp.valueOf("2007-09-23 10:10:10.0");
		Timestamp timestampDatedebutQuestion = Timestamp.valueOf("2007-09-23 10:15:10.0");
		
		ArrayList<Question> listeQuestion = new ArrayList<Question>();
		listeQuestion.add(q1);

		Quiz quiz = new Quiz("libelle", 1, timestampDatedebutQuiz, 1, 1, timestampDatedebutQuestion, listeQuestion);

		QuizDAO qdao = (QuizDAO) context.getBean("quizDAOImpl");
		qdao.ajouterQuiz(quiz);
		

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		Choisir choix = new Choisir(timestamp, personne2, quiz, prop1);

		ChoisirDAO cdao = (ChoisirDAO) context.getBean("choisirDAOImpl");

		cdao.ajouterChoix(choix);
		assertEquals(choix.getQuiz(), quiz);
		assertEquals(choix.getPersonne(), personne2);
		assertEquals(choix.getProposition(), prop1);



	}

}
