package fr.esigelec.quiz.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.esigelec.quiz.model.Personne;

/**
 * 
 * @author Sahobau
 * 
 */


/**
 * 
 * Classe d'implementation des méthodes contenues dans PersonneDAO
 *
 */

@Repository("personneDAOImpl")
@Transactional(propagation = Propagation.SUPPORTS)
public class PersonneDAOImpl implements PersonneDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	/**
	 *  méthode Ajouter un Personne
	 * @param personne , représente la personne à enregistrer
	 * @return -1 when personne existe 0 when personne is null 1 when personne
	 *         save
	 */
	@Transactional
	public int ajouterPersonne(Personne personne) {

		if (personne != null) {

			Boolean verifEmailUnique = verifEmailPresent(personne.getMail());

			if (verifEmailUnique.equals(false))

			{
				return -1;
			} else {

				try {
					sessionFactory.getCurrentSession().saveOrUpdate(personne);

				} catch (HibernateException e) {

				}

				return 1;

			}

		}
		return 0;

	}

	/**
	 * méthode pour récupérer une liste des personnes 
	 * @return la liste des personnes de la bdd
	 */
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Personne> getListePersonnes() {

		return (List<Personne>) sessionFactory.getCurrentSession().createCriteria(Personne.class).list();
	}

	
	
	/**
	 * méthode pour récupérer une personne 
	 * @param id, représente l'identitifiant de la personne à récupérer 
	 * @return la personne désirée
	 */
	@Override
	@Transactional(readOnly = true)
	public Personne getPersonne(int id) {

		return (Personne) sessionFactory.getCurrentSession().get(Personne.class, id);

	}
	
	/**
	 * méthode de suppression d'une personne
	 * @param id, représente l'identifiant de la personne à supprimer 
	 */

	@Override
	@Transactional
	public void supprimerPersonne(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Personne p = (Personne) session.load(Personne.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	}
	
	
	/**
	 * méthode de vérification si une personne existe ou pas 
	 * @param email, email de la personne recherchée
	 * @param modtDePasse, mot de passe de la personne recherché
	 * @return -1 pour utilisateur inexistant dans la bdd
	 * 			!=-1 pour un utilisateur existant
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	
	public int verifPersonne(String email, String motDePasse) {

		List<Personne> personnes = new ArrayList<Personne>();

		personnes = sessionFactory.getCurrentSession().createQuery("from Personne where mail=? and mdp=?")
				.setParameter(0, email).setParameter(1, motDePasse).list();

		if (personnes.size() > 0) {

			return personnes.get(0).getId();

		} else {

			return -1;
		}

	}
	
	

	/**
	 * méthode de récupération d'une personne par son email
	 * @param email, email de la personne recherchée
	 * @return null when personne don't exist
	 * 			!=null when personne exist
	 */
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Personne getPersonneByEmail(String email) {
		List<Personne> personnes = new ArrayList<Personne>();

		personnes = sessionFactory.getCurrentSession().createQuery("from Personne where mail=?").setParameter(0, email)
				.list();

		if (personnes.size() != 0) {
			return personnes.get(0);
		}

		return null;
	}

	/**
	 * méthode de vérification si un email est contenu dans la bdd
	 * @param email, email à rechercher 
	 * @return true when Email is unique 
	 * 		   false when Email is not unique
	 */

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Boolean verifEmailPresent(String email) {
		//

		List<Personne> personnes = new ArrayList<Personne>();

		personnes = sessionFactory.getCurrentSession().createQuery("from Personne where mail=?").setParameter(0, email)
				.list();

		if (personnes.size() == 1) {

			return true;

		} else {

			return false;
		}

	}

}
