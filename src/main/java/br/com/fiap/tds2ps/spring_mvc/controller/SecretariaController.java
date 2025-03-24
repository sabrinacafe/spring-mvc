package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.model.Secretaria;
import br.com.fiap.tds2ps.spring_mvc.repository.SecretariaRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

    @Controller
    @RequestMapping("/secretaria")
    public class SecretariaController {

        private final SecretariaRepository secretariaRepository;

        public SecretariaController(SecretariaRepository secretariaRepository) {
            this.secretariaRepository = secretariaRepository;
        }

        @GetMapping("/login")
        public String loginPage() {
            return "secretaria-login";
        }

        @PostMapping("/login")
        public String autenticarSecretaria(@RequestParam String usuario, @RequestParam String senha,
                                           RedirectAttributes redirectAttributes, HttpSession session) {
            Optional<Secretaria> secretaria = secretariaRepository.findByUsuarioAndSenha(usuario, senha);
            if (secretaria.isPresent()) {
                session.setAttribute("secretaria", secretaria.get());
                return "redirect:/secretaria/pacientes";
            } else {
                redirectAttributes.addFlashAttribute("erro", "Usuário ou senha inválidos!");
                return "redirect:/secretaria/login";
            }
        }

        @GetMapping("/dashboard")
        public String dashboard() {
            return "redirect:/secretaria/pacientes";
        }
    }