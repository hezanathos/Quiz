package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.model.Personne;


/**
 * 
 * @author Sahobau
 * 
 * classe interface PersonneDAO
 * 
 * */

public interface PersonneDAO {
	
	/**
	 * méthode Ajouter un Personne
	 * 
	 * @param personne représente la personne à enregistrer
	 * @return -1 when personne existe 0 when personne is null 1 when personne save
	 */
	public int ajouterPersonne(Personne personne);
	
	/**
	 * méthode pour récupérer une liste des personnes
	 * 
	 * @return la liste des personnes de la bdd
	 */
	public List<Personne> getListePersonnes();
	
	/**
	 * méthode pour récupérer une personne
	 * 
	 * @param id représente l'identitifiant de la personne à récupérer
	 * @return la personne désirée
	 */
	public Personne getPersonne(int id);
	
	/**
	 * méthode de récupération d'une personne par son email
	 * 
	 * @param email email de la personne recherchée
	 * @return null when personne don't exist !=null when personne exist
	 */
	public Personne getPersonneByEmail(String email);
	
	/**
	 * méthode de suppression d'une personne
	 * 
	 * @param id représente l'identifiant de la personne à supprimer
	 */
	public void supprimerPersonne(int id);
	
	/**
	 * méthode de vérification si une personne existe ou pas
	 * 
	 * @param  email de la personne recherchée
	 * @param modtDePasse mot de passe de la personne recherché
	 * @return -1 pour utilisateur inexistant dans la bdd !=-1 pour un
	 *         utilisateur existant
	 */
	public int verifPersonne(String email,String motdePasse);
	
	/**
	 * méthode de vérification si un email est contenu dans la bdd
	 * 
	 * @param email email à rechercher
	 * @return true when Email is unique false when Email is not unique
	 */
	public Boolean verifEmailPresent(String email);

}
