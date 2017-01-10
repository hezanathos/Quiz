package fr.esigelec.quiz.dao;

import java.util.List;

import fr.esigelec.quiz.model.Choisir;

/**
 * 
 * @author Sahobau
 * 
 * */

public interface ChoisirDAO {
	
	public void ajouterChoix(Choisir choix);
	
	public Choisir getChoix(int id);
	
	public List<Choisir> getListeChoix();
	
	public void supprimerChoix(int id);

}
