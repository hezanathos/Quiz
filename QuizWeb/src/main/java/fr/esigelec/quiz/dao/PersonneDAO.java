package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.model.Personne;


/**
 * 
 * @author Sahobau
 * 
 * */
public interface PersonneDAO {
	
	public void ajouterPersonne(Personne personne);
	
	public List<Personne> getListePersonnes();
	
	public Personne getPersonne(int id);
	
	public void supprimerPersonne(int id);
	

}
