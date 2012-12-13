package br.edu.unoesc.dao;

import java.util.List;

import br.edu.unoesc.model.Cidade;
import br.edu.unoesc.model.Uf;

public interface CidadeDao {

	void salvarOuAlterar(Cidade cidade);
	void remover(Cidade cidade);
	List<Cidade> getTodos();
	Cidade getById(Long id);
	List<Cidade> getByNome(String nomePesquisa);
	List<Cidade> getByUf(Uf uf);
	
}
