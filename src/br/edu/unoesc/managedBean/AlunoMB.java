package br.edu.unoesc.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.unoesc.dao.AlunoDao;
import br.edu.unoesc.dao.CidadeDao;
import br.edu.unoesc.model.Aluno;
import br.edu.unoesc.model.Cidade;
import br.edu.unoesc.model.Uf;

@ManagedBean
@ViewScoped
public class AlunoMB implements Serializable{

	private static final long serialVersionUID = 5435141969144068152L;
	
	private Aluno aluno;
	
	private Uf uf;
	
	@ManagedProperty("#{alunoDaoImpl}")
	private AlunoDao alunoDao;
	
	@ManagedProperty("#{cidadeDaoImpl}")
	private CidadeDao cidadeDao;
	
	@PostConstruct
	public void novo(){
		aluno = new Aluno();
	}
	
	public List<Cidade> getCidadesPorUfSelecionada(){
		return cidadeDao.getByUf(uf);
	}
	
	
	public void salvar(){
		alunoDao.SalvarOuAlterar(aluno);
		FacesMessage mensagem = new FacesMessage();
		mensagem.setSummary("Salvo com sucesso");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
	
	public void remover(Aluno aluno){
		alunoDao.remover(aluno);
		FacesMessage mensagem = new FacesMessage();
		mensagem.setSummary("Excluido com sucesso");
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
	
	public List<Aluno> getListaAluno(){
		return alunoDao.getTodos();
	}
	
	public void editar(Aluno aluno){
		this.aluno = aluno;
	}
	
	public Aluno getAluno(){
		return aluno;
	}
	
	public void setAluno(Aluno aluno){
		this.aluno = aluno;
	}

	public AlunoDao getAlunoDao() {
		return alunoDao;
	}

	public void setAlunoDao(AlunoDao alunoDao) {
		this.alunoDao = alunoDao;
	}
	
	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public CidadeDao getCidadeDao() {
		return cidadeDao;
	}

	public void setCidadeDao(CidadeDao cidadeDao) {
		this.cidadeDao = cidadeDao;
	}
	
}
