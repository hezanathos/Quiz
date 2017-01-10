package fr.esigelec.quiz.model.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.esigelec.quiz.dao.PersonneDAO;
import fr.esigelec.quiz.dao.PersonneDAOImpl;
import fr.esigelec.quiz.model.Personne;
import junit.framework.TestCase;

public class TestPersonne extends TestCase {

	
	@Test
	public void testPersonne() {
		Personne p1=new Personne("Sauvage","Guillaume","guillaumesauv@gmail.fr","mdp",0);
		assertEquals(p1.getNom(),"Sauvage");
		assertEquals(p1.getPrenom(),"Guillaume");
		assertEquals(p1.getMail(),"guillaumesauv@gmail.fr");
		assertEquals(p1.getMdp(),"mdp");
		assertEquals(p1.getDroits(),0);
	}
	
	public void testPersonneDAO() {
		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		Personne p2=new Personne("Sauvage","Guillaume","guillaumesauv@gmail.fr","mdp",0);
		PersonneDAO pDAO1 = (PersonneDAO) context.getBean("personneDAOImpl");
		pDAO1.ajouterPersonne(p2);
		Personne p3 = pDAO1.getPersonneByEmail("guillaumesauv@gmail.fr");
		assertEquals(p3.getNom(),"Sauvage");
		assertEquals(p3.getPrenom(),"Guillaume");
		assertEquals(p3.getMail(),"guillaumesauv@gmail.fr");
		assertEquals(p3.getMdp(),"mdp");
		assertEquals(p3.getDroits(),0);
	
	}
}
