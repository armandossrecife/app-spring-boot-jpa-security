package com.myapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.modelo.Usuario;
import com.myapp.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	
	public void inserir(Usuario usuario) {
		repository.save(usuario);
	}
	
	public Usuario buscarPorEmail(String email, String senha){
		//TODO implementar busca por e-mail e senha
		return null;
	}
	
	public Usuario buscarPorNome(String nome){
		//TODO implementar busca por nome
		return null;
	}
	
	public Usuario buscarPorEmail(String email){
		//TODO implementar busca por email
		return null;
	}
	
	public Usuario buscarPorLogin(String login){
		//TODO implementar busca por login
		return null;
	}
	
	public List<Usuario> listar(){
		Iterable<Usuario> usuarios = repository.findAll();
		
		List<Usuario> listaUsuarios = new ArrayList<>();
		
		for (Usuario usuario : usuarios){
			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}
	
	public Iterable<Usuario> obterTodos(){
	        Iterable<Usuario> usuarios = repository.findAll();
	        return usuarios;
	}
	
	/**
	 * Faz a busca de usuário de acordo com o tipo selecionado
	 * @param conteudo dado do usuário
	 * @param tipo tipo da busca pode ser nome, email, ou login
	 * @return usuario contendo o resultado da busca
	 */
	public Usuario buscarPorConteudo(String conteudo, String tipo){
		Usuario usuario=null;
		switch (tipo) {
		case "nome":
			usuario = this.buscarPorNome(conteudo);
			break;
		case "email":
			usuario = this.buscarPorEmail(conteudo);
			break;
		case "login":
			usuario = this.buscarPorLogin(conteudo);
			break;
		default:
			usuario = null;
			break;
		}
		return usuario;
	}
	
	/**
	 * Remove um usuário selecionado
	 * @param u usuário
	 */
	public void remover(Usuario usuario){
		repository.delete(usuario);
	}

	/**
	 * Remove um usuário selecionado
	 * @param id do usuário
	 */
	public void remover(int id){
		repository.delete(this.buscarPorId(id));
	}

	/**
	 * Altera um usuário pelo seu identificador
	 * @param id
	 * @param nome
	 * @param login
	 * @param email
	 * @param senha
	 * @param imagemPath
	 */
	public void alterar(int id, String nome, String login, String email, String senha, String imagemPath){
		Usuario usuario = this.buscarPorId(id);
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setEmail(email);
		usuario.setImagemPath(imagemPath);
		repository.save(usuario);
	}
	
	/**
	 * Faz a busca de um usuário
	 * @param id do usuário
	 * @return Usuario
	 */
	public Usuario buscarPorId(int id) {
		return repository.findOne(id);
	}
	
}