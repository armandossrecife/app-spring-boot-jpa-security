package com.myapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.modelo.Role;
import com.myapp.modelo.Usuario;
import com.myapp.repository.RoleRepository;
import com.myapp.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private RoleRepository repositoryRole;

	/**
	 * Faz a busca de Role para transforma-lo em managed pelo JPA 
	 * e evitar erro de Detached quando for atualizar o usuário
	 * @param id do Role
	 * @return Role 
	 */
	public Role buscaRole(int id) {
		return repositoryRole.findOne(id);
	}

	/**
	 * Insere um usuário
	 * @param usuario 
	 */
	public void inserir(Usuario usuario) {
		repository.save(usuario);
	}

	public Usuario buscarPorEmail(String email, String senha) {
		// TODO implementar busca por e-mail e senha
		return null;
	}

	/**
	 * Dada uma lista de usuários checa se não está vazia
	 * @param usuarios Lista de usuários 
	 * @return Lista de usuários não vazia | null
	 */
	public List<Usuario> checaListaNaoVazia(List<Usuario> usuarios) {
		if (usuarios.size() > 0) {
			return usuarios;
		} else {
			return null;
		}
	}
	
	/**
	 * Faz a busca pelo nome exato
	 * 
	 * @param nome completo do usuário     
	 * @return List<Usuario> lista de usuários | null
	 */
	public List<Usuario> buscarPorNome(String nome) {
		List<Usuario> usuarios = repository.findByNome(nome);
		
		return checaListaNaoVazia(usuarios);

	}

	/**
	 * Faz a busca pelo e-mail exato
	 * @param email e-mail exato do usuário
	 * @return List<Usuario> lista de usuários | null
	 */
	public List<Usuario> buscarPorEmail(String email) {
		List<Usuario> usuarios = repository.findByEmail(email);
		
		return checaListaNaoVazia(usuarios);
	}

	/**
	 * Faz a busca pelo login exato
	 * @param login exato do usuário
	 * @return List<Usuario> lista de usuários
	 */
	public List<Usuario> buscarPorLogin(String login) {
		List<Usuario> usuarios = repository.findByLogin(login);
		
		return checaListaNaoVazia(usuarios);

	}

	/**
	 * Lista todos os usuários da lista de usuários
	 * @return List<Usuario> lista de usuários
	 */
	public List<Usuario> listar() {
		Iterable<Usuario> usuarios = repository.findAll();

		List<Usuario> listaUsuarios = new ArrayList<>();

		for (Usuario usuario : usuarios) {
			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}

	/**
	 * Lista todos os usuários da lista
	 * @return Iterable<Usuario>
	 */
	public Iterable<Usuario> obterTodos() {
		Iterable<Usuario> usuarios = repository.findAll();
		return usuarios;
	}

	/**
	 * Faz a busca de usuário de acordo com o tipo selecionado
	 * 
	 * @param conteudo dado do usuário
	 * @param tipo tipo da busca pode ser nome, email, ou login
	 * @return usuario contendo o resultado da busca
	 */
	public List<Usuario> buscarPorConteudo(String conteudo, String tipo) {
		List<Usuario> usuarios = null;
		switch (tipo) {
		case "nome":
			usuarios = this.buscarPorNome(conteudo);
			break;
		case "email":
			usuarios = this.buscarPorEmail(conteudo);
			break;
		case "login":
			usuarios = this.buscarPorLogin(conteudo);
			break;
		default:
			usuarios = null;
			break;
		}
		return usuarios;
	}

	/**
	 * Remove um usuário selecionado
	 * @param u usuário
	 */
	public void remover(Usuario usuario) {
		repository.delete(usuario);
	}

	/**
	 * Remove um usuário selecionado
	 * @param id do usuário
	 */
	public void remover(int id) {
		repository.delete(this.buscarPorId(id));
	}

	/**
	 * Altera um usuário pelo seu identificador
	 * 
	 * @param id
	 * @param nome
	 * @param login
	 * @param email
	 * @param senha
	 * @param imagemPath
	 */
	public void alterar(int id, String nome, String login, String email, String senha, String imagemPath) {
		Usuario usuario = this.buscarPorId(id);
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setEmail(email);
		usuario.setImagemPath(imagemPath);
		repository.save(usuario);
	}

	/**
	 * Faz a busca de um usuário por id
	 * 
	 * @param id do usuário      
	 * @return Usuario
	 */
	public Usuario buscarPorId(int id) {
		return repository.findOne(id);
	}

}