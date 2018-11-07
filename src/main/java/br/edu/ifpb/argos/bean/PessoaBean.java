package br.edu.ifpb.argos.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.edu.ifpb.argos.entity.Investigacao;
import br.edu.ifpb.argos.entity.Pessoa;
import br.edu.ifpb.argos.facade.PessoaController;


@ManagedBean(name = "pessoaBean")
@RequestScoped

public class PessoaBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id = null;
	private String nome;
	private String apelido;
	private String historico;
	
	// Acrescentada essa opcao File para a questao da foto.
	// Ainda em duvida, implementacao precisa ser revisada.
	// Nao esta funcionando ainda.	
	private UploadedFile file;
	
	private byte[] foto;

	private List<Investigacao> investigacoes;
	
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	
	
	// Metodo para criar uma nova Pessoa

	public String salvar() {
		PessoaController controller = new PessoaController();
		String proxView = null;
		
		try {
			System.out.println("Metodo para Criar Pessoa - Bean");
			
			pessoa = new Pessoa();
			pessoa.setNome("nome");
			pessoa.setApelido("apelido");
			pessoa.setHistorico("historico");
			pessoa.setFoto(getFoto());
			
			pessoa.setInvestigacoes(getInvestigacoes());
			controller.cadastrar(pessoa);
			this.addSuccessMessage("Pessoa criada com sucesso!");
			proxView = "/pessoa/pessoas?faces-redirect=true";
			pessoa = new Pessoa();
		}
		catch (PersistenceException e) {
			this.addErrorMessage("Erro ao tentar criar pessoa.");
		}
		return proxView;
	}
	
	
	//implementado por Hilberto
	public String goCadastroPessoa() {
		return "/pessoa/cadastroPessoa?faces-redirect=true";
	}
	
	
	// Metodo para listar as Pessoas (sem opcao de excluir e editar Pessoa)
	
	public String goPessoas() {
		return "/pessoa/pessoas?faces-redirect=true";
	}
	
	// Metodo para atualizar dados de Pessoa
	
	public String editar(Pessoa pessoa) {
		this.nome = pessoa.getNome();
		this.apelido = pessoa.getApelido();
		this.historico = pessoa.getHistorico();
//		this.foto = pessoa.getFoto();
		return "/pessoa/cadastroPessoa?faces-redirect=true&includeViewParams=true";
	}
	
	// Metodo para excluir Pessoa
	
	public String excluir(Pessoa pessoa) {
		String proxima_pagina = null;
		
		PessoaController controller = new PessoaController();
		controller.excluir(pessoa);
		this.addSuccessMessage("Pessoa excluida com sucesso!");
		
		proxima_pagina = "/pessoa/pessoas?faces-redirect-true";
		return proxima_pagina;
	}
	
	// Metodo para buscar uma Pessoa pelo nome
	
	public String pesquisarPessoa() {
		PessoaController controller = new PessoaController();
		this.pessoa = controller.buscar(nome);
		
		if (pessoa == null) {
			this.addErrorMessage("Essa pessoa nao existe!");
		}
		return "/pessoa/buscaPessoa?faces-redirect=true&includeViewParams=true";
	}
	
	// Metodo para listar Pessoas
	
	public void listarPessoas() {
		PessoaController controller = new PessoaController();
		this.pessoas = controller.listar();
	}
	
	public void upload(FileUploadEvent event) {
		file = event.getFile();
		
		if (file != null) {
			
			File file1 = new File("Caminho que voce deseja salvar a imagem", file.getFileName());
			
			try {
				FileOutputStream fos = new FileOutputStream(file1);
				fos.write(event.getFile().getContents());
				fos.close();
				
				FacesContext instance = FacesContext.getCurrentInstance();
				instance.addMessage("mensagens", new FacesMessage(FacesMessage.SEVERITY_ERROR, file.getFileName()
						+ " anexado com sucesso", null));
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
		
	
	 // Getters e setters
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getApelido() {
		return apelido;
	}
	
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	
	public String getHistorico() {
		return historico;
	}
	
	public void setHistorico(String historico) {
		this.historico = historico;
	}
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public byte[] getFoto() {
		return foto;
	}
	
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	public List<Investigacao> getInvestigacoes() {
		return investigacoes;
	}

	public void setInvestigacoes(List<Investigacao> investigacoes) {
		this.investigacoes = investigacoes;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
}