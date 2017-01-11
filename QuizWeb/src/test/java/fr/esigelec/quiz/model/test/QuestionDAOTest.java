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
		assertEquals(q1.getLibelle(), "Quelle est la couleur du cheval blanc d'henri IV");
		assertEquals(q1.getListproposition().size(), 4);
		assertEquals(q1.getListproposition().get(0).getLibelle(), "gris");

	}

	@Test
	public void testPersonneDAO() {
		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		Proposition p1 = new Proposition("gris", 0);
		Proposition p2 = new Proposition("blanc", 1);
		Proposition p3 = new Proposition("Obi Wan Kenobi", 0);
		Proposition p4 = new Proposition("la réponse 4", 0);
		List<Proposition> lp = new ArrayList<Proposition>();
		PropositionDAO pDAO = (PropositionDAO) context.getBean("propositionDAOImpl");
		lp.add(p1);
		lp.add(p2);
		lp.add(p3);
		lp.add(p4);
		int flag = 0;
		Question q2 = new Question("Quelle est la couleur du cheval blanc d'henri IV", lp);
		
		QuestionDAO qDAO1 = (QuestionDAO) context.getBean("questionDAOImpl");
		int formerSize = pDAO.getListePropositions().size();
		qDAO1.ajouterQuestion(q2);
		assertTrue( qDAO1.getListeQuestions().contains((q2)));
		for (Question question : qDAO1.getListeQuestions()) {
		if (question.getLibelle().equals("Quelle est la couleur du cheval blanc d'henri IV"))	flag =1;
		} 
		assertEquals(1, flag);
		
		
		
		
		assertTrue( pDAO.getListePropositions().size()>formerSize);
		
	
	}
	
}
