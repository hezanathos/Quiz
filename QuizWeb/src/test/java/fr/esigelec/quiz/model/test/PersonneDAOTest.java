package fr.esigelec.quiz.model.test;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.esigelec.quiz.dao.PersonneDAO;
import fr.esigelec.quiz.model.Personne;
import junit.framework.TestCase;

public class PersonneDAOTest extends TestCase {

	@Test
	public void testPersonne() {
		Personne p1 = new Personne("Sauvage", "Guillaume", "guillaumesauv@gmail.fr", "mdp", 0);
		assertEquals(p1.getNom(), "Sauvage");
		assertEquals(p1.getPrenom(), "Guillaume");
		assertEquals(p1.getMail(), "guillaumesauv@gmail.fr");
		assertEquals(p1.getMdp(), "mdp");
		assertEquals(p1.getDroits(), 0);
	}

	@Test
	public void testPersonneDAO() {
		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet2.xml");
		Personne p2 = new Personne("Sauvage", "Guillaume", "guillaumesauv@gmail.fr", "mdp", 0);
		PersonneDAO pDAO1 = (PersonneDAO) context.getBean("personneDAOImpl");
		pDAO1.ajouterPersonne(p2);
		Personne p3 = pDAO1.getPersonneByEmail("guillaumesauv@gmail.fr");
		assertEquals(p3.getNom(), "Sauvage");
		assertEquals(p3.getPrenom(), "Guillaume");
		assertEquals(p3.getMail(), "guillaumesauv@gmail.fr");
		assertEquals(p3.getMdp(), "mdp");
		assertEquals(p3.getDroits(), 0);

	}

	@Test
	public void testSuppPersonneDAO() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet2.xml");
		Personne p31 = new Personne("Sauvage", "Guillaume", "guillaumesauv@gmail.fr", "mdp", 0);

		PersonneDAO pDAO1 = (PersonneDAO) context.getBean("personneDAOImpl");
		pDAO1.ajouterPersonne(p31);

		Personne p4 = pDAO1.getPersonneByEmail("guillaumesauv@gmail.fr");
		pDAO1.supprimerPersonne(p4.getId());
		Personne p5 = pDAO1.getPersonneByEmail("guillaumesauv@gmail.fr");
		assertNull(p5);
	}

	@Test
	public void testGetListePersonneDAO() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet2.xml");
		PersonneDAO pDAO3 = (PersonneDAO) context.getBean("personneDAOImpl");
		List<Personne> Pliste = new ArrayList<Personne>();
		Personne p6 = new Personne("Sauvage", "Guillaume", "guillaumesauv@gmail.fr", "mdp", 0);
		Personne p7 = new Personne("Lecoq", "Alex", "alex@gmail.fr", "mdp", 0);
		Personne p8 = new Personne("Dupond", "Paul", "dupond@gmail.fr", "mdp", 0);
		pDAO3.ajouterPersonne(p6);
		pDAO3.ajouterPersonne(p7);
		pDAO3.ajouterPersonne(p8);

		Pliste = pDAO3.getListePersonnes();
		for (Personne p : Pliste) {
			assertNotNull(p);
		}

	}

	@Test
	public void testVerifPersonneDAO() {
		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet2.xml");
		PersonneDAO pDAO4 = (PersonneDAO) context.getBean("personneDAOImpl");
		int test;

		Personne p8 = new Personne("Test", "Paul", "test@gmail.fr", "mdp", 0);
		pDAO4.ajouterPersonne(p8);
		Personne p9 = pDAO4.getPersonneByEmail("test@gmail.fr");
		test = pDAO4.verifPersonne("test@gmail.fr", "mdp");
		assertEquals(test, p9.getId());

		test = pDAO4.verifPersonne("test@gmail.fr", "bla");
		assertEquals(test, -1);

	}

	@Test
	public void testVerifMailPresent() {
		ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet2.xml");
		Personne p10 = new Personne("Test", "Paul", "present@gmail.fr", "mdp", 0);
		PersonneDAO pDAO5 = (PersonneDAO) context.getBean("personneDAOImpl");
		pDAO5.ajouterPersonne(p10);

		assertTrue(pDAO5.verifEmailPresent("present@gmail.fr"));
		assertFalse(pDAO5.verifEmailPresent("paspresent@gmail.fr"));
	}

}
