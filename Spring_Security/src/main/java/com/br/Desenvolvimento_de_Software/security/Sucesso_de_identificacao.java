package com.br.Desenvolvimento_de_Software.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class Sucesso_de_identificacao extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(Sucesso_de_identificacao.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request,
                          HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        System.out.println("handle: " + targetUrl);

        if (response.isCommitted()) {
            logger.warn("Não pode redirecionar");
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication autenticacao) {
        logger.info("determine: " + autenticacao.getName());
        Collection<? extends GrantedAuthority> autoridade = autenticacao.getAuthorities();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority a : autoridade) {
            System.out.println("autoridade: " + a.getAuthority());
            roles.add(a.getAuthority());
        }

        if (isAdmin(roles)) {
            return "/admin";
        } else if (isUser(roles)) {
            return "/home";
        } else {
            return "/login?error";
        }
    }

    @Override
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    @Override
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    private boolean isUser(List<String> roles) {
        return roles.contains("ROLE_USER");
    }

    private boolean isAdmin(List<String> roles) {
        return roles.contains("ROLE_ADMIN");
    }
}