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
	/**
	 * @return -1 when personne existe
	 * 			0 when personne is null
	 * 			1 when personne save 
	 */
	public int ajouterPersonne(Personne personne) {
		
		if(personne !=null){
			
			Boolean verifEmailUnique = verifEmailUnique(personne.getMail());
			
			if(verifEmailUnique.equals(true))
			
			{
				return -1;
			}
			else
			{
				   sessionFactory.getCurrentSession().saveOrUpdate(personne);		
				   return 1;

			}

			
		}
		return 0;
		
	
		
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
	
	/**
	 * @return -1 pour utilsateur inexistant dans la bdd
	 */
	public int verifPersonne(String email, String motDePasse) {
		
		List<Personne> personnes = new ArrayList<Personne>();

		personnes = sessionFactory.getCurrentSession()
			.createQuery("from personne where mail=? and mdp=?")
			.setParameter(0, email)
			.setParameter(1, motDePasse)
			.list();

		if (personnes.size() > 0) {
			
		return personnes.get(0).getId();
		
		} else {
			
			return -1;
		}

			}

	/**
	 * @return null when personne don't exist
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Personne getPersonneByEmail(String email) {
		List<Personne> personnes = new ArrayList<Personne>();

		personnes = sessionFactory.getCurrentSession()
			.createQuery("from personne where mail=?")
			.setParameter(0, email)
			.list();
		
		if(personnes.size()!=0){
			return personnes.get(0);
		}
		
		return null;
	}

	
	/**
	 * @return true when Email is unique 
	 * 			false when !=
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Boolean verifEmailUnique(String email) {

		List<Personne> personnes = new ArrayList<Personne>();

		personnes = sessionFactory.getCurrentSession()
			.createQuery("from personne where mail=?")
			.setParameter(0, email)
			.list();

		if (personnes.size() == 1) {
			
		return true;
		
		} else {
			
			return false;
		}
		
	}

}