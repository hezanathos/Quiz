package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.model.Proposition;

/**
 * 
 * @author Sahobau
 * 
 * */

//Interface PropositionDAO
public interface PropositionDAO {
	
	public void ajouterProposition(Proposition proposition);
	
	public List<Proposition> getListePropositions();
	
	public Proposition getProposition(int id);
	
	// public void supprimerProposition(int id);

}
