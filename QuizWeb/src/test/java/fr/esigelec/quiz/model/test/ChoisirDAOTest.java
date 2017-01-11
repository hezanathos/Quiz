package fr.esigelec.quiz.model.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.esigelec.quiz.dao.PersonneDAO;
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
				
		
		Proposition p1 = new Proposition("gris", 0);
		Proposition p2 = new Proposition("blanc", 1);
		Proposition p3 = new Proposition("Obi Wan Kenobi", 0);
		Proposition p4 = new Proposition("la réponse 4", 0);
		List<Proposition> lp = new ArrayList<Proposition>();
		lp.add(p1);
		lp.add(p2);
		lp.add(p3);
		lp.add(p4);
		Question q1 = new Question("Quelle est la couleur du cheval blanc d'henri IV", lp);
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Quiz quiz = new Quiz();
		
		Choisir choix = new Choisir(timestamp,personne2, quiz, p1);

	}

}
