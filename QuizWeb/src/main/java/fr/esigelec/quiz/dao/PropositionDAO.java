package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.model.Proposition;

/**
 * 
 * @author Sahobau
 * 
 * classe interface PropositionDAO
 * */

public interface PropositionDAO {
	
	/**
	 *  méthode Ajouter une proposition
	 *  
	 * @param proposition  représente la proposition à enregistrer
	 * 
	 */
	public void ajouterProposition(Proposition proposition);
	
	/**
	 * méthode pour récupérer une liste des propositions 
	 * @return la liste des propositions de la bdd
	 */
	public List<Proposition> getListePropositions();
	
	/**
	 * méthode pour récupérer une proposition 
	 * @param id représente l'identitifiant de la proposition à récupérer 
	 * @return la proposition désirée
	 */
	public Proposition getProposition(int id);
	
	//public void supprimerProposition(int id);
	
	
}
