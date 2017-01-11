package fr.esigelec.quiz.model.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.esigelec.quiz.dao.PersonneDAO;
import fr.esigelec.quiz.model.Choisir;
import fr.esigelec.quiz.model.Personne;
import fr.esigelec.quiz.model.Proposition;
import fr.esigelec.quiz.model.Quiz;

public class ChoixDAOTest {

	@Test

	public void testChoixDAO() {
		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		Personne personne1 = new Personne("Sauvage", "Guillaume", "blajgh@gmail.fr", "mdp", 0);
		PersonneDAO pDAO1 = (PersonneDAO) context.getBean("personneDAOImpl");
		pDAO1.ajouterPersonne(personne1);
		Personne personne2 = pDAO1.getPersonneByEmail("blajgh@gmail.fr");
				
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Quiz quiz = new Quiz();
		Proposition prop = new Proposition();
		Choisir choix = new Choisir(timestamp,personne2, quiz, prop);

	}

}
