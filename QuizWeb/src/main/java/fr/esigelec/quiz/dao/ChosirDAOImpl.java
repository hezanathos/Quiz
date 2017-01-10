package fr.esigelec.quiz.dao;

/**
 * @author Sahobau
 */

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.esigelec.quiz.model.Choisir;

@Repository("choisirDAO")
@Transactional(propagation = Propagation.SUPPORTS)
public class ChosirDAOImpl implements ChoisirDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void ajouterChoix(Choisir choix) {

		sessionFactory.getCurrentSession().saveOrUpdate(choix);
		
	

	}

	@Override
	@Transactional(readOnly = true)
	public Choisir getChoix(int id) {
		return (Choisir) sessionFactory.getCurrentSession().get(Choisir.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Choisir> getListeChoix() {
		return (List<Choisir>) sessionFactory.getCurrentSession().createCriteria(Choisir.class).list();
	}

	@Override
	@Transactional(readOnly = true)
	public void supprimerChoix(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Choisir chx = (Choisir) session.load(Choisir.class, new Integer(id));
		if (null != chx) {
			session.delete(chx);

		}

	}

}
