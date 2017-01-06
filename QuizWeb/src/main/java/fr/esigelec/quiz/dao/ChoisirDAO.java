package fr.esigelec.quiz.dao;

import fr.esigelec.quiz.model.Choisir;

/**
 * 
 * @author Sahobau
 * 
 * */

public interface ChoisirDAO {
	
	public void ajouterChoix(Choisir choix);
	
	public void supprimerChoix(int id);

}
