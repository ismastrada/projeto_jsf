package br.edu.unoesc.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.unoesc.dao.UfDao;
import br.edu.unoesc.model.Uf;

@ManagedBean
@ViewScoped
public class UfMB implements Serializable{

	private static final long serialVersionUID = 5028008841239086551L;

	private Uf uf;
	
	@ManagedProperty("#{ufDaoImpl}")
	private UfDao ufDao;
	
	@PostConstruct
	public void novo(){
		uf = new Uf();
	}
	
	public void salvar(){
		ufDao.salvarOuAlterar(uf);
		FacesMessage mensagem = new FacesMessage();
		mensagem.setSummary("Salvo com sucesso");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
		
	}
	
	public void remover(Uf uf){
		ufDao.remover(uf);
		FacesMessage mensagem = new FacesMessage();
		mensagem.setSummary("Excluido com sucesso");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
	
	public List<Uf> getListaUf(){
		return ufDao.getTodos();
	}
	
	public void editar(Uf uf){
		this.uf=uf;
	}
	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public UfDao getUfDao() {
		return ufDao;
	}

	public void setUfDao(UfDao ufDao) {
		this.ufDao = ufDao;
	}
	
}
