package fr.esigelec.quiz.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.esigelec.quiz.model.Personne;
import fr.esigelec.quiz.model.Proposition;
/**
 * 
 * @author Sahobau
 * 
 * */

@Repository("PersonneDAO") 
public class PersonneDAOImpl implements PersonneDAO{
	

	 @Autowired
	 private SessionFactory sessionFactory;

	@Override
	public void ajouterPersonne(Personne personne) {
		   sessionFactory.getCurrentSession().saveOrUpdate(personne);		
		
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Personne> getListePersonnes() {
		
		return (List<Personne>) sessionFactory.getCurrentSession().createCriteria(Personne.class).list();
	}

	@Override
	@Transactional(readOnly=true)
	public Personne getPersonne(int id) {
		
		  return (Personne) sessionFactory.getCurrentSession().get(Personne.class, id);

	}

	@Override
	public void supprimerPersonne(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Personne p = (Personne) session.load(Personne.class, new Integer(id));
		if(null != p){
			session.delete(p);	
	}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean verifPersonne(String email, String motDePasse) {
		
		List<Personne> personnes = new ArrayList<Personne>();

		personnes = sessionFactory.getCurrentSession()
			.createQuery("from personne where mail=? and mdp=?")
			.setParameter(0, email)
			.setParameter(2, motDePasse)
			.list();

		if (personnes.size() > 0) {
			return true;
		} else {
			return false;
		}

			}

}
