/**
 * 
 */
package fr.esigelec.quiz.model.test;

import junit.framework.TestCase;
import fr.esigelec.quiz.dao.PersonneDAO;
import fr.esigelec.quiz.dao.PropositionDAO;
import fr.esigelec.quiz.dao.QuestionDAO;
import fr.esigelec.quiz.model.Question;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.esigelec.quiz.model.Personne;
import fr.esigelec.quiz.model.Proposition;;

/**
 * @author Alex Lecoq
 *
 */
public class QuestionDAOTest extends TestCase {

	@Test
	public void testQuestion() {

		Question q1 = new Question("Quelle est la couleur du cheval blanc d'henri IV");
		assertEquals(q1.getLibelle(), "Quelle est la couleur du cheval blanc d'henri IV");

	}

	@Test
	public void testQuestionDAO() {
		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");

		Question q2 = new Question("Quelle est la couleur du cheval blanc d'henri IV");

		QuestionDAO qDAO1 = (QuestionDAO) context.getBean("questionDAOImpl");

		int formersize = qDAO1.getListeQuestions().size();

		qDAO1.ajouterQuestion(q2);
		assertTrue(qDAO1.getListeQuestions().size() > formersize);

	}

	@Test
	public void testGetListProposition() {
		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");

		Question q2 = new Question("Quelle est la couleur du cheval blanc d'henri IV");
		PropositionDAO pDAO = (PropositionDAO) context.getBean("propositionDAOImpl");
		Proposition p1 = new Proposition("gris", 0, q2);
		Proposition p2 = new Proposition("blanc", 1, q2);
		Proposition p3 = new Proposition("Obi Wan Kenobi", 0, q2);
		Proposition p4 = new Proposition("la réponse 4", 0, q2);
		QuestionDAO qDAO1 = (QuestionDAO) context.getBean("questionDAOImpl");

		qDAO1.ajouterQuestion(q2);
		pDAO.ajouterProposition(p1);
		pDAO.ajouterProposition(p2);
		pDAO.ajouterProposition(p3);
		pDAO.ajouterProposition(p4);
		assertEquals(4, qDAO1.getListePropositions(q2.getId()).size());
	}
}
