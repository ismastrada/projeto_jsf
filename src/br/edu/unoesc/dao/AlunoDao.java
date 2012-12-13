package br.edu.unoesc.dao;

import java.util.List;

import br.edu.unoesc.model.Aluno;

public interface AlunoDao{

	void SalvarOuAlterar(Aluno aluno);
	void remover(Aluno aluno);
	List<Aluno> getTodos();
	Aluno getById(Long id);
	List<Aluno> getByNome(String nomePesquisa);
		
}
