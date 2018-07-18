package com.myapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myapp.modelo.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{
	List<Usuario> findByNome(String nome);
	List<Usuario> findByEmail(String email);
	List<Usuario> findByLogin(String login);
	
}
