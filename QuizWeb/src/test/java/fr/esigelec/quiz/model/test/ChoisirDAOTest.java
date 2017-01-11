package fr.esigelec.quiz.model.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.esigelec.quiz.dao.ChoisirDAO;
import fr.esigelec.quiz.dao.PersonneDAO;

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
		Personne personne1 = new Personne("Sauvage", "Guillaume", "blajgh@gmail.fr", "mdp", 0);
		PersonneDAO pDAO1 = (PersonneDAO) context.getBean("personneDAOImpl");
		pDAO1.ajouterPersonne(personne1);
		Personne personne2 = pDAO1.getPersonneByEmail("blajgh@gmail.fr");
				
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
		
		
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		
		
		Choisir choix = new Choisir(timestamp,personne2, quiz, listeQuestion.get(0).getListproposition().get(0));
		
		ChoisirDAO cdao = (ChoisirDAO) context.getBean("choisirDAOImpl");
		
		cdao.ajouterChoix(choix);
		Choisir c = cdao.getChoix(0);
		assertEquals(c.getPersonne(),personne2);

	}

}
