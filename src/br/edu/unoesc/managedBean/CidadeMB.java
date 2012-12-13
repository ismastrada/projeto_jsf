package br.edu.unoesc.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.unoesc.dao.CidadeDao;
import br.edu.unoesc.model.Cidade;
import br.edu.unoesc.model.Uf;

@ManagedBean
@ViewScoped
public class CidadeMB implements Serializable{

	
	private static final long serialVersionUID = 7827078762358585221L;

	private Cidade cidade;
	
	@ManagedProperty("#{cidadeDaoImpl}")
	private CidadeDao cidadeDao;
	
	@PostConstruct
	public void novo(){
		cidade = new Cidade();
	}
	
	public void salvar(){
		cidadeDao.salvarOuAlterar(cidade);
		FacesMessage mensagem = new FacesMessage();
		mensagem.setSummary("Salvo com sucesso");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
	
	public void remover(Cidade cidade){
		cidadeDao.remover(cidade);
		FacesMessage mensagem = new FacesMessage();
		mensagem.setSummary("Excluido com sucesso");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
	
	public List<Cidade> getListaCidade(){
		return cidadeDao.getTodos();
	}

	public void editar(Cidade cidade){
		this.cidade = cidade;
	}
	
	public Cidade getCidade(){
		return cidade;
	}
	
	public void setCidade(Cidade cidade){
		this.cidade = cidade;
	}
	
	public CidadeDao getCidadeDao(){
		return cidadeDao;
	}
	
	public void setCidadeDao(CidadeDao cidadeDao){
		this.cidadeDao = cidadeDao;
	}
	
	public List<Cidade> getCidadeByUf(Uf uf){
		return cidadeDao.getByUf(uf);
	}
		
}
