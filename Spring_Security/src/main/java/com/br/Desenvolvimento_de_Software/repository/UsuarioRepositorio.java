package com.br.Desenvolvimento_de_Software.repository;

import com.br.Desenvolvimento_de_Software.model.Usuario;
import org.springframework.data.repository.CrudRepository;


public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer> {
	
	Usuario findByNome(String nome);

	Usuario findByEmail(String email);
}