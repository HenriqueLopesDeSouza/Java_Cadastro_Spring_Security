package com.br.Desenvolvimento_de_Software.servico.imp;

import com.br.Desenvolvimento_de_Software.model.Usuario;
import com.br.Desenvolvimento_de_Software.repository.UsuarioRepositorio;
import com.br.Desenvolvimento_de_Software.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class UsuarioServicoimpl implements UsuarioServico {

    @Autowired
    private UsuarioRepositorio nomeRepositorio;

    @Override
    public Usuario save(Usuario nome) {
        return nomeRepositorio.save(nome);
    }

    @Override
    public Boolean delete(int id) {
        if (nomeRepositorio.existsById(id)) {
            nomeRepositorio.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Usuario update(Usuario nome) {
        return nomeRepositorio.save(nome);
    }

    @Override
    public Usuario findById(int id) {
        return nomeRepositorio.findById(id).get();
    }

    @Override
    public Usuario findByNome(String nomename) {
        return nomeRepositorio.findByNome(nomename);
    }

    @Override
    public Usuario findByEmail(String email) {
        return nomeRepositorio.findByEmail(email);
    }

    @Override
    public Collection<Usuario> findAll() {
        Iterable<Usuario> itr = nomeRepositorio.findAll();
        return (Collection<Usuario>) itr;
    }
}