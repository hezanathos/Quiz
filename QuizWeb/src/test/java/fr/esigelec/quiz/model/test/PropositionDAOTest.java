package fr.esigelec.quiz.model.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.esigelec.quiz.model.Proposition;
import fr.esigelec.quiz.model.Question;
import junit.framework.TestCase;
import fr.esigelec.quiz.dao.PropositionDAO;
import fr.esigelec.quiz.dao.QuestionDAO;
public class PropositionDAOTest extends TestCase {

	
	
	@Test
	public void testProposition() {
		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet2.xml");
		
		Question q = new Question("Quelle est la couleur du cheval blanc d'Henry IV");
		Proposition p = new Proposition("blanc",1,q);
		 
		
		PropositionDAO pDAO = (PropositionDAO) context.getBean("propositionDAOImpl");
		QuestionDAO qDAO = (QuestionDAO) context.getBean("questionDAOImpl");
		int formersize = pDAO.getListePropositions().size();
		int formersizeQuestion = qDAO.getListeQuestions().size();
		pDAO.ajouterProposition(p);
		assertTrue(formersize+1==pDAO.getListePropositions().size());
		assertTrue(formersizeQuestion+1==qDAO.getListeQuestions().size());
		
		
		Proposition p1 = pDAO.getProposition(p.getId());
		assertEquals(q.getId(),p1.getQuestion().getId());
		
		
	}
}
