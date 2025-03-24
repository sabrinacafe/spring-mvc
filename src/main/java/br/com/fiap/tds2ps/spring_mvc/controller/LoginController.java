package br.com.fiap.tds2ps.spring_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/secretaria/login")
    public String loginSecretaria() {
        return "secretaria-login";
    }
}
