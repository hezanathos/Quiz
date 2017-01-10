package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.model.Personne;


/**
 * 
 * @author Sahobau
 * 
 * */
public interface PersonneDAO {
	
	public int ajouterPersonne(Personne personne);
	
	public List<Personne> getListePersonnes();
	
	public Personne getPersonne(int id);
	
	public Personne getPersonneByEmail(String email);
	
	public void supprimerPersonne(int id);
	
	public int verifPersonne(String email,String motdePasse);
	
	public Boolean verifEmailUnique(String email);
	

}
