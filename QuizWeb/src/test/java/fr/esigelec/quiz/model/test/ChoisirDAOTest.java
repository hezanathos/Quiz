package fr.esigelec.quiz.model.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

		/*
		 * Personne personne1 = new Personne("Sauvage", "Guillaume",
		 * "ghfsdfghjgdf@gmail.fr", "mdp", 0); Question q1 = new
		 * Question("libQ1"); Question q2 = new Question("libQ2");
		 * 
		 * ArrayList<Question> listeQuestion = new ArrayList<Question>();
		 * listeQuestion.add(q1); listeQuestion.add(q1);
		 * 
		 * Timestamp timestampDatedebutQuiz =
		 * Timestamp.valueOf("2007-09-23 10:10:10.0"); Timestamp
		 * timestampDatedebutQuestion =
		 * Timestamp.valueOf("2007-09-23 10:15:10.0");
		 * 
		 * Quiz quiz = new Quiz("libelle", 1, timestampDatedebutQuiz, 1, 1,
		 * timestampDatedebutQuestion, listeQuestion); Proposition prop1 = new
		 * Proposition("libelleprop1", 1, q1);
		 * 
		 * Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 * 
		 * Choisir choix = new Choisir(timestamp, personne1, quiz, prop1);
		 * 
		 * ChoisirDAO cdao = (ChoisirDAO) context.getBean("choisirDAOImpl");
		 * cdao.ajouterChoix(choix); Choisir c = cdao.getChoix(0);
		 * assertEquals(c.getPersonne(), personne1);
		 */

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

	@Test
	public void testgetNbBonnesReponses() {
		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");

		Timestamp timestampDate1 = Timestamp.valueOf("2017-01-16 10:10:10.0");
		Timestamp timestampDate2 = Timestamp.valueOf("2017-01-16 12:10:10.0");

		List<Question> listeQuestion1 = new ArrayList<Question>();

		listeQuestion1.add(new Question("libelle1Q1"));
		listeQuestion1.add(new Question("libelle1Q2"));

		/*
		 * List<Question> listeQuestion2 = new ArrayList<Question>();
		 * 
		 * listeQuestion2.add(new Question("libelle2Q1"));
		 * listeQuestion2.add(new Question("libelle2Q2"));
		 */

		Personne p1 = new Personne("LESIEU", "Rock", "lesieurock@der.fr", "mdp1", 1000);
		Personne p2 = new Personne("Richard", "richard", "rouxrichard@der.fr", "mdp2", 0);

		PersonneDAO personneDAO = (PersonneDAO) context.getBean("personneDAOImpl");
		personneDAO.ajouterPersonne(p1);
		personneDAO.ajouterPersonne(p2);

		p1 = personneDAO.getPersonneByEmail("lesieurock@der.fr");
		p2 = personneDAO.getPersonneByEmail("rouxrichard@der.fr");

		Quiz quiz1 = new Quiz("libelleQuiz1-1", 1, Timestamp.valueOf("2017-01-16 10:10:10.0"), 1, 1,
				Timestamp.valueOf("2017-01-16 10:16:10.0"), listeQuestion1);
		// Quiz quiz2 = new Quiz("libelleQuiz2",1,Timestamp.valueOf("2007-09-23
		// 10:10:10.0"),1,1,Timestamp.valueOf("2007-09-23
		// 10:16:10.0"),listeQuestion2);

		QuizDAO quizDAO = (QuizDAO) context.getBean("quizDAOImpl");
		quizDAO.ajouterQuiz(quiz1);
		// quizDAO.ajouterQuiz(quiz2);

		Proposition prop1 = new Proposition("libelleprop1", 1, listeQuestion1.get(0));
		Proposition prop2 = new Proposition("libelleprop2", 1, listeQuestion1.get(0));

		PropositionDAO propositionDAO = (PropositionDAO) context.getBean("propositionDAOImpl");
		propositionDAO.ajouterProposition(prop1);
		propositionDAO.ajouterProposition(prop2);

		Choisir chx1 = new Choisir(timestampDate1, p1, quiz1, prop1);
		Choisir chx2 = new Choisir(timestampDate2, p2, quiz1, prop2);

		ChoisirDAO cdao = (ChoisirDAO) context.getBean("choisirDAOImpl");

		cdao.ajouterChoix(chx1);
		cdao.ajouterChoix(chx2);

		int nbbonnesRp = cdao.getNbBonnesReponses(quiz1.getId(), listeQuestion1.get(0).getId());

		System.out.println(nbbonnesRp);

		
			assertEquals(nbbonnesRp, 2);

	

	}
	
	
	@Test
	public void testgetNbReponses(){
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");

		Timestamp timestampDate1 = Timestamp.valueOf("2017-01-16 10:10:10.0");
		Timestamp timestampDate2 = Timestamp.valueOf("2017-01-16 12:10:10.0");

		Timestamp timestampDate3 = Timestamp.valueOf("2017-02-16 10:10:10.0");
		Timestamp timestampDate4 = Timestamp.valueOf("2017-02-16 12:10:10.0");

		Timestamp timestampDate5 = Timestamp.valueOf("2017-03-16 10:10:10.0");
		Timestamp timestampDate6 = Timestamp.valueOf("2017-03-16 12:10:10.0");

		List<Question> listeQuestion1 = new ArrayList<Question>();

		listeQuestion1.add(new Question("libelle1Q1"));
		listeQuestion1.add(new Question("libelle1Q2"));

		/*
		 * List<Question> listeQuestion2 = new ArrayList<Question>();
		 * 
		 * listeQuestion2.add(new Question("libelle2Q1"));
		 * listeQuestion2.add(new Question("libelle2Q2"));
		 */

		Personne p1 = new Personne("DOMI", "Zebi", "domizebi@der.fr", "mdp1", 1000);
		Personne p2 = new Personne("UKOI", "darinne", "ukoidarinne@der.fr", "mdp2", 0);

		PersonneDAO personneDAO = (PersonneDAO) context.getBean("personneDAOImpl");
		personneDAO.ajouterPersonne(p1);
		personneDAO.ajouterPersonne(p2);

		p1 = personneDAO.getPersonneByEmail("domizebi@der.fr");
		p2 = personneDAO.getPersonneByEmail("ukoidarinne@der.fr");

		Quiz quiz1 = new Quiz("libelleQuiz1-1_nbReponses", 1, Timestamp.valueOf("2017-01-16 12:10:10.0"), 1, 1,
				Timestamp.valueOf("2017-01-16 12:16:10.0"), listeQuestion1);
		// Quiz quiz2 = new Quiz("libelleQuiz2",1,Timestamp.valueOf("2007-09-23
		// 10:10:10.0"),1,1,Timestamp.valueOf("2007-09-23
		// 10:16:10.0"),listeQuestion2);

		QuizDAO quizDAO = (QuizDAO) context.getBean("quizDAOImpl");
		quizDAO.ajouterQuiz(quiz1);
		// quizDAO.ajouterQuiz(quiz2);

		Proposition prop1 = new Proposition("libelleprop1", 1, listeQuestion1.get(0));
		Proposition prop2 = new Proposition("libelleprop2", 1, listeQuestion1.get(0));
		Proposition prop3 = new Proposition("libelleprop3", 0, listeQuestion1.get(0));
		Proposition prop4 = new Proposition("libelleprop4", 1, listeQuestion1.get(0));
		Proposition prop5 = new Proposition("libelleprop5", 1, listeQuestion1.get(0));
		Proposition prop6 = new Proposition("libelleprop6", 0, listeQuestion1.get(0));

		PropositionDAO propositionDAO = (PropositionDAO) context.getBean("propositionDAOImpl");
		propositionDAO.ajouterProposition(prop1);
		propositionDAO.ajouterProposition(prop2);
		propositionDAO.ajouterProposition(prop3);
		propositionDAO.ajouterProposition(prop4);
		propositionDAO.ajouterProposition(prop5);
		propositionDAO.ajouterProposition(prop6);

		Choisir chx1 = new Choisir(timestampDate1, p1, quiz1, prop1);
		Choisir chx2 = new Choisir(timestampDate2, p2, quiz1, prop2);

		Choisir chx3 = new Choisir(timestampDate3, p1, quiz1, prop3);
		Choisir chx4 = new Choisir(timestampDate4, p2, quiz1, prop4);

		Choisir chx5 = new Choisir(timestampDate5, p1, quiz1, prop5);
		Choisir chx6 = new Choisir(timestampDate6, p2, quiz1, prop6);

		ChoisirDAO cdao = (ChoisirDAO) context.getBean("choisirDAOImpl");

		cdao.ajouterChoix(chx1);
		cdao.ajouterChoix(chx2);
		cdao.ajouterChoix(chx3);
		cdao.ajouterChoix(chx4);
		cdao.ajouterChoix(chx5);
		cdao.ajouterChoix(chx6);

		int nbbonnesRp = cdao.getNbReponses(quiz1.getId(), listeQuestion1.get(0).getId());
		
		assertEquals(nbbonnesRp, 6);

		
	
	}
	
	@Test
	public void testgetNbBonnesReponseDunParticipantAuQuiz(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");

		Timestamp timestampDate1 = Timestamp.valueOf("2017-01-16 10:10:10.0");
		Timestamp timestampDate2 = Timestamp.valueOf("2017-01-16 12:10:10.0");

		Timestamp timestampDate3 = Timestamp.valueOf("2017-02-16 10:10:10.0");
		Timestamp timestampDate4 = Timestamp.valueOf("2017-02-16 12:10:10.0");

		Timestamp timestampDate5 = Timestamp.valueOf("2017-03-16 10:10:10.0");
		Timestamp timestampDate6 = Timestamp.valueOf("2017-03-16 12:10:10.0");

		List<Question> listeQuestion1 = new ArrayList<Question>();

		listeQuestion1.add(new Question("libelle1Q1"));
		listeQuestion1.add(new Question("libelle1Q2"));

		/*
		 * List<Question> listeQuestion2 = new ArrayList<Question>();
		 * 
		 * listeQuestion2.add(new Question("libelle2Q1"));
		 * listeQuestion2.add(new Question("libelle2Q2"));
		 */

		Personne p1 = new Personne("OKI", "Zebi", "okizebi@der.fr", "mdp1", 1000);
		Personne p2 = new Personne("UKYOI", "darinne", "ukyoidarinne@der.fr", "mdp2", 0);

		PersonneDAO personneDAO = (PersonneDAO) context.getBean("personneDAOImpl");
		personneDAO.ajouterPersonne(p1);
		personneDAO.ajouterPersonne(p2);

		p1 = personneDAO.getPersonneByEmail("okizebi@der.fr");
		p2 = personneDAO.getPersonneByEmail("ukyoidarinne@der.fr");

		Quiz quiz1 = new Quiz("libelleQuiz1-1_nbReponses", 1, Timestamp.valueOf("2017-01-16 13:10:10.0"), 1, 1,
				Timestamp.valueOf("2017-01-16 13:16:10.0"), listeQuestion1);
		// Quiz quiz2 = new Quiz("libelleQuiz2",1,Timestamp.valueOf("2007-09-23
		// 10:10:10.0"),1,1,Timestamp.valueOf("2007-09-23
		// 10:16:10.0"),listeQuestion2);

		QuizDAO quizDAO = (QuizDAO) context.getBean("quizDAOImpl");
		quizDAO.ajouterQuiz(quiz1);
		// quizDAO.ajouterQuiz(quiz2);

		Proposition prop1 = new Proposition("libelleprop1", 1, listeQuestion1.get(0));
		Proposition prop2 = new Proposition("libelleprop2", 1, listeQuestion1.get(0));
		Proposition prop3 = new Proposition("libelleprop3", 0, listeQuestion1.get(0));
		Proposition prop4 = new Proposition("libelleprop4", 1, listeQuestion1.get(0));
		Proposition prop5 = new Proposition("libelleprop5", 1, listeQuestion1.get(0));
		Proposition prop6 = new Proposition("libelleprop6", 0, listeQuestion1.get(0));

		PropositionDAO propositionDAO = (PropositionDAO) context.getBean("propositionDAOImpl");
		propositionDAO.ajouterProposition(prop1);
		propositionDAO.ajouterProposition(prop2);
		propositionDAO.ajouterProposition(prop3);
		propositionDAO.ajouterProposition(prop4);
		propositionDAO.ajouterProposition(prop5);
		propositionDAO.ajouterProposition(prop6);

		Choisir chx1 = new Choisir(timestampDate1, p1, quiz1, prop1);
		Choisir chx2 = new Choisir(timestampDate2, p2, quiz1, prop2);

		Choisir chx3 = new Choisir(timestampDate3, p1, quiz1, prop3);
		Choisir chx4 = new Choisir(timestampDate4, p2, quiz1, prop4);

		Choisir chx5 = new Choisir(timestampDate5, p1, quiz1, prop5);
		Choisir chx6 = new Choisir(timestampDate6, p2, quiz1, prop6);

		ChoisirDAO cdao = (ChoisirDAO) context.getBean("choisirDAOImpl");

		cdao.ajouterChoix(chx1);
		cdao.ajouterChoix(chx2);
		cdao.ajouterChoix(chx3);
		cdao.ajouterChoix(chx4);
		cdao.ajouterChoix(chx5);
		cdao.ajouterChoix(chx6);

		int nbbonnesRpPersonne = cdao.getNbBonnesReponseDunParticipantAuQuiz(quiz1.getId(), p1.getId());
		
		assertEquals(nbbonnesRpPersonne, 2);
		
		
	}

}
