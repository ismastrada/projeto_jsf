package br.edu.unoesc.dao.impl;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;

import br.edu.unoesc.dao.UfDao;
import br.edu.unoesc.jpa.EntityManagerUtil;
import br.edu.unoesc.model.Uf;

@ManagedBean
@ApplicationScoped
public class UfDaoImpl implements UfDao{

	private EntityManager entityManager = EntityManagerUtil.getEntityManager();
	
	
	@Override
	public void salvarOuAlterar(Uf uf) {
		if (uf.getId()==null){
			// salva
			entityManager.getTransaction().begin();
			entityManager.persist(uf);
			entityManager.getTransaction().commit();
			
		} else {
			// altera
			entityManager.getTransaction().begin();
			uf = entityManager.merge(uf);
			entityManager.getTransaction().commit();
			
		}
	}

	@Override
	public void remover(Uf uf) {
		entityManager.getTransaction().begin();
		entityManager.remove(uf);
		entityManager.getTransaction().commit();
		
	}

	@Override
	public List<Uf> getTodos() {
		String sql = "select uf from Uf uf";
		
		return entityManager.createQuery(sql, Uf.class).getResultList();
		
	}

	@Override
	public Uf getById(Long id) {
		
		String sql = "select uf from Uf uf where uf.id= :idParam";
		
		return entityManager.createQuery(sql, Uf.class).setParameter("idParam", id).getSingleResult();
	}

	@Override
	public List<Uf> getByNome(String nomePesquisa) {
		if (nomePesquisa!=null){
			nomePesquisa="%"+nomePesquisa+"%";
		}
		String sql = "select uf from Uf uf where uf.nome like :nomeParam";
		
		return entityManager.createQuery(sql, Uf.class).setParameter("nomeParam", nomePesquisa).getResultList();
	}

}
