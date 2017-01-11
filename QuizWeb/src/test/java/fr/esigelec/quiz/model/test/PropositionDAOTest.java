package fr.esigelec.quiz.model.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import fr.esigelec.quiz.dao.PropositionDAO;
import fr.esigelec.quiz.model.Proposition;

public class PropositionDAOTest {

	@Test
	public void testProposition() {
		Proposition p1=new Proposition(0,"la reponse D",0);
		assertEquals(p1.getId(),0);
		assertEquals(p1.getLibelle(),"la reponse D");
		assertEquals(p1.isBonneReponse(),0);
		
	}
	
	@Test
	public void testAjoutProposition() {
		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		Proposition p1=new Proposition(4444,"la reponse D",0);
		PropositionDAO pDAO1 = (PropositionDAO) context.getBean("ppropositionDAOImpl");
		pDAO1.ajouterProposition(p1);
		
		Proposition p2 = pDAO1.getProposition(4444);
		assertEquals(p2.getId(),p1);
		assertEquals(p1.getLibelle(),"la reponse D");
		assertEquals(p1.isBonneReponse(),0);
	
	}
}
