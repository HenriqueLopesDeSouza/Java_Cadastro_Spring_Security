package com.br.Desenvolvimento_de_Software.security;

import org.springframework.stereotype.Service;
import com.br.Desenvolvimento_de_Software.model.Usuario;
import com.br.Desenvolvimento_de_Software.servico.UsuarioServico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Service
public class Detalhes_de_Identificacao implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(Detalhes_de_Identificacao.class);

    @Autowired
    private UsuarioServico userService;

    private org.springframework.security.core.userdetails.User springUser;

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {

        boolean ativado = true;
        boolean contanaoexpirada = true;
        boolean credenciaisnaoexpiradas = true;
        boolean contanaobloqueada = true;

        Usuario usuario  = getUserDetail(nome);
        if (usuario  != null) {
            springUser = new org.springframework.security.core.userdetails.User(usuario .getnome(),
            		usuario .getsenha(),
                    ativado,
                    contanaoexpirada,
                    credenciaisnaoexpiradas,
                    contanaobloqueada,
                    getAuthorities(usuario.getfuncao())
            );
            return springUser;
        } else {
            springUser = new org.springframework.security.core.userdetails.User("esvaziar",
                    "esvaziar",
                    false,
                    true,
                    true,
                    false,
                    getAuthorities(1)
            );
            return springUser;
        }
    }

    public List<GrantedAuthority> getAuthorities(Integer role) {

        List<GrantedAuthority> autenticacaodalista = new ArrayList<GrantedAuthority>();
        if (role == 1) {
        	autenticacaodalista.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (role == 2) {
        	autenticacaodalista.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return autenticacaodalista;
    }

    private Usuario getUserDetail(String nome) {

        Usuario usuario = userService.findByNome(nome);
        if (usuario == null) {
            logger.warn("Usuario '" + nome + "' é nullo!");
        } else {
            logger.info(usuario.toString());
        }
        return usuario;
    }
}
