package fr.esigelec.quiz.model.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.esigelec.quiz.model.Personne;

public class PropositionDAOTest {

	@Test
	public void testPersonne() {
		Personne p1=new Personne("Sauvage","Guillaume","guillaumesauv@gmail.fr","mdp",0);
		assertEquals(p1.getNom(),"Sauvage");
		assertEquals(p1.getPrenom(),"Guillaume");
		assertEquals(p1.getMail(),"guillaumesauv@gmail.fr");
		assertEquals(p1.getMdp(),"mdp");
		assertEquals(p1.getDroits(),0);
	}
}
