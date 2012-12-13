package br.edu.unoesc.dao;

import java.util.List;

import br.edu.unoesc.model.Uf;

public interface UfDao {

	void salvarOuAlterar(Uf uf);
	void remover(Uf uf);
	List<Uf> getTodos();
	Uf getById(Long id);
	List<Uf> getByNome(String nomePesquisa);
	
}
