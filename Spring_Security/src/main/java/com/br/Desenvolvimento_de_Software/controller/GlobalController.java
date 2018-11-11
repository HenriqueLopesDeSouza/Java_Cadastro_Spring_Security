package com.br.Desenvolvimento_de_Software.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.br.Desenvolvimento_de_Software.model.Usuario;
import com.br.Desenvolvimento_de_Software.servico.UsuarioServico;


@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GlobalController {

    @Autowired
    private UsuarioServico Servico;

    private Usuario login;

    public Usuario getLoginUser() {
        if (login == null) {
            Authentication autenticao = SecurityContextHolder.getContext().getAuthentication();
            login = Servico.findByNome(autenticao.getName());
        }
        return login;
    }
}