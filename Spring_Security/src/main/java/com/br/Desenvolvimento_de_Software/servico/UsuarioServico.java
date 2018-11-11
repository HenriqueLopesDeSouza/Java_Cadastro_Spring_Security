package com.br.Desenvolvimento_de_Software.servico;

import com.br.Desenvolvimento_de_Software.model.Usuario;

import java.util.Collection;

public interface UsuarioServico {

	Usuario save(Usuario user);

	Boolean delete(int id);

	Usuario update(Usuario user);

	Usuario findById(int id);

	Usuario findByNome(String nome);

	Usuario findByEmail(String email);

	Collection<Usuario> findAll();

}
