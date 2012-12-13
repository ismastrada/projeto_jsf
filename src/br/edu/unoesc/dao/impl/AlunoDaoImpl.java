package br.edu.unoesc.dao.impl;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;

import br.edu.unoesc.dao.AlunoDao;
import br.edu.unoesc.jpa.EntityManagerUtil;
import br.edu.unoesc.model.Aluno;

@ManagedBean
@ApplicationScoped
public class AlunoDaoImpl implements AlunoDao{

	private EntityManager entityManager = EntityManagerUtil.getEntityManager();
	
	@Override
	public void SalvarOuAlterar(Aluno aluno) {
		if (aluno.getId()==null){
			// salva
			entityManager.getTransaction().begin();
			entityManager.persist(aluno);
			entityManager.getTransaction().commit();
			
		} else {
			// altera
			entityManager.getTransaction().begin();
			aluno= entityManager.merge(aluno);
			entityManager.getTransaction().commit();
		}
		
	}

	@Override
	public void remover(Aluno aluno) {
		entityManager.getTransaction().begin();
		entityManager.remove(aluno);
		entityManager.getTransaction().commit();
		
	}

	@Override
	public List<Aluno> getTodos() {
		String sql = "select aluno from Aluno aluno";
		
		return entityManager.createQuery(sql, Aluno.class).getResultList();
	}

	@Override
	public Aluno getById(Long id) {
		String sql = "select aluno from Aluno aluno where aluno.id= :alunoParam";
		
		return entityManager.createQuery(sql, Aluno.class).setParameter("alunoParam", id).getSingleResult();
	}

	@Override
	public List<Aluno> getByNome(String nomePesquisa) {
		if (nomePesquisa!=null){
			nomePesquisa="%"+nomePesquisa+"%";
		}
		String sql = "select aluno from Aluno aluno where aluno.nome like :nomeParam";
		
		return entityManager.createQuery(sql, Aluno.class).setParameter("nomeParam", nomePesquisa).getResultList();
	}

}
