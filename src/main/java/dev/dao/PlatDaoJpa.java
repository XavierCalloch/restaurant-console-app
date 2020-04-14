package dev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.entite.Plat;

@Repository
@Profile("PlatDaoJpa")
public class PlatDaoJpa implements IPlatDao {

	private EntityManager em;

	@Override
	public List<Plat> listerPlats() {

		TypedQuery<Plat> query = em.createQuery("select p from Plat p", Plat.class);
		List<Plat> resultList = query.getResultList();

		em.close();

		return resultList;
	}

	@Override
	@Transactional
	public void ajouterPlat(String nomPlat, Integer prixPlat) {
		em.persist(new Plat(nomPlat, prixPlat));
	}

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

}
