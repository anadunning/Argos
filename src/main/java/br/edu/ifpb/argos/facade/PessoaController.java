package br.edu.ifpb.argos.facade;

import java.util.List;

import javax.persistence.PersistenceException;

import br.edu.ifpb.argos.dao.PersistenceUtil;
import br.edu.ifpb.argos.dao.PessoaDAO;
import br.edu.ifpb.argos.entity.Pessoa;

public class PessoaController {
	
	// Metodo da classe PessoaController para Criar Pessoa
	
	// Esse metodo é acessado pela classe PessoaBean.
	
	public void cadastrar(Pessoa pessoa) {
		System.out.println("\nMetodo Cadastrar do Pessoa Controller");
		PessoaDAO dao = new PessoaDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.insert(pessoa);
		dao.commit();
	}
	
	// Metodo da classe PessoaController para Consultar Pessoa
		
	public List<Pessoa> consultar(Pessoa pessoa) {
		PessoaDAO dao = new PessoaDAO(PersistenceUtil.getCurrentEntityManager());
		List<Pessoa> individuos = dao.findAll();
		return individuos;	
	}
	
	// Metodo da classe PessoaController para Excluir Pessoa
	
	// Esse metodo é acessado pela classe PessoaBean
	
	public boolean excluir(Pessoa pessoa) {
		boolean excluiu = false;
		PessoaDAO dao = new PessoaDAO();
		try {
			dao.beginTransaction();
			Pessoa p = dao.find(pessoa.getId());
			dao.delete(p);
			excluiu = true;
			
		} catch (PersistenceException e) {
			dao.rollback();
		}
		return excluiu;
	}
	
	// Metodo da classe PessoaController para Listar Pessoas
	
	// Esse metodo é acessado pela classe PessoaBean 
	
	public List<Pessoa> listar() {
		PessoaDAO dao = new PessoaDAO();
		List<Pessoa> pessoas = dao.findAll();
		return pessoas;
	}
	
	// Metodo da classe PessoaController para Buscar Pessoa
	
	// Esse metodo é acessado pela classe PessoaBean
	
	public Pessoa buscar(String nome) {
		PessoaDAO dao = new PessoaDAO();
		Pessoa pessoaEncontrada = dao.findByNome(nome);
		return pessoaEncontrada;
	}
	
	// Metodo da classe PessoaController para atualizar / editar Pessoa
	
	// Esse metodo é acessado pela classe PessoaBean
	
	public void atualizar(Pessoa pessoa) {
		PessoaDAO dao = new PessoaDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.update(pessoa);
		dao.commit();
	}
}