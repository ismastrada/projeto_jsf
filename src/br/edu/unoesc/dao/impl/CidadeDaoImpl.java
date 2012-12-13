package br.edu.unoesc.dao.impl;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;

import br.edu.unoesc.dao.CidadeDao;
import br.edu.unoesc.jpa.EntityManagerUtil;
import br.edu.unoesc.model.Cidade;
import br.edu.unoesc.model.Uf;

@ManagedBean
@ApplicationScoped
public class CidadeDaoImpl implements CidadeDao{

	private EntityManager entityManager = EntityManagerUtil.getEntityManager();

	@Override
	public void salvarOuAlterar(Cidade cidade) {
		if (cidade.getId()==null){
			// salva
			entityManager.getTransaction().begin();
			entityManager.persist(cidade);
			entityManager.getTransaction().commit();
			
		} else {
			// altera
			entityManager.getTransaction().begin();
			cidade = entityManager.merge(cidade);
			entityManager.getTransaction().commit();
		}
		
	}
	
	@Override
	public void remover(Cidade cidade) {
		entityManager.getTransaction().begin();
		entityManager.remove(cidade);
		entityManager.getTransaction().commit();
		
	}
	
	@Override
	public List<Cidade> getTodos() {
		String sql = "select cidade from Cidade cidade";
		
		return entityManager.createQuery(sql, Cidade.class).getResultList();
	}
	
	@Override
	public Cidade getById(Long id) {
		String sql = "select cidade from Cidade cidade where cidade.id= :cidadeParam";
		
		return entityManager.createQuery(sql, Cidade.class).setParameter("cidadeParam", id).getSingleResult();
	}
	
	@Override
	public List<Cidade> getByNome(String nomePesquisa) {
		if (nomePesquisa!=null){
			nomePesquisa="%"+nomePesquisa+"%";
		}
		String sql = "select cidade from Cidade cidade where cidade.nome like :nomeParam";
		
		return entityManager.createQuery(sql, Cidade.class).setParameter("nomeParam", nomePesquisa).getResultList();
	}

	@Override
	public List<Cidade> getByUf(Uf uf) {
		
		String sql = "select cidade from Cidade cidade where cidade.uf = :uf";
		
		return entityManager.createQuery(sql, Cidade.class).setParameter("uf", uf).getResultList();
	}

}