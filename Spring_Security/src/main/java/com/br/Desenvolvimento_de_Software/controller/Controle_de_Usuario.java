package com.br.Desenvolvimento_de_Software.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.br.Desenvolvimento_de_Software.model.Usuario;
import com.br.Desenvolvimento_de_Software.servico.UsuarioServico;
import com.br.Desenvolvimento_de_Software.util.Codificacao;
import com.br.Desenvolvimento_de_Software.util.Roles;


@Controller
public class Controle_de_Usuario {

    private static final Logger logger = LoggerFactory.getLogger(Controle_de_Usuario.class);

    @Autowired
    GlobalController globalController;

    @Autowired
    UsuarioServico userService;

    @RequestMapping("/")
    public String root(Model model) {
        model.addAttribute("reqUser", new Usuario());
        logger.info("root");
        return "login";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("reqUser", new Usuario());
        logger.info("login");
        return "login";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        
        return "home";
    }

    @RequestMapping("/admin")
    public String helloAdmin() {
        logger.info("admin");
        return "admin";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("reqUser", new Usuario());
        logger.info("register");
        return "register";
    }

    @RequestMapping(value = {"/user/register"}, method = RequestMethod.POST)
    public String register(@ModelAttribute("reqUser") Usuario RequerirUsuario,
                           final RedirectAttributes redirectAttributes) {

        logger.info("/user/register");
        Usuario usuario = userService.findByNome(RequerirUsuario.getnome());
        if (usuario != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-name");
            return "redirect:/register";
        }
        usuario = userService.findByEmail(RequerirUsuario.getEmail());
        if (usuario != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-email");
            return "redirect:/register";
        }

        RequerirUsuario.setsenha(Codificacao.getInstance().codificacao_senha.encode(RequerirUsuario.getsenha()));
        RequerirUsuario.setfuncao(Roles.ROLE_USER.getValue());

        if (userService.save(RequerirUsuario) != null) {
            redirectAttributes.addFlashAttribute("saveUser", "success");
        } else {
            redirectAttributes.addFlashAttribute("saveUser", "fail");
        }

        return "redirect:/register";
    }


}