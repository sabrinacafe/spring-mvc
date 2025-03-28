package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.model.Secretaria;
import br.com.fiap.tds2ps.spring_mvc.service.PacienteService;
import br.com.fiap.tds2ps.spring_mvc.service.SecretariaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/secretaria")
public class SecretariaController {

    private final SecretariaService secretariaService;
    private final PacienteService pacienteService;

    public SecretariaController(SecretariaService secretariaService, PacienteService pacienteService) {
        this.secretariaService = secretariaService;
        this.pacienteService = pacienteService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "secretaria-login";
    }

    @PostMapping("/login")
    public String autenticarSecretaria(@RequestParam String usuario,
                                       @RequestParam String senha,
                                       HttpSession session,
                                       RedirectAttributes redirectAttributes) {

        Optional<Secretaria> secretaria = secretariaService.autenticar(usuario, senha);

        if (secretaria.isPresent()) {
            session.setAttribute("idSecretaria", secretaria.get().getId());
            session.setAttribute("usuarioSecretaria", secretaria.get().getUsuario());
            return "redirect:/secretaria/pacientes-lista";
        } else {
            redirectAttributes.addFlashAttribute("erro", "Usuário ou senha inválidos!");
            return "redirect:/secretaria/login";
        }
    }

    @GetMapping("/pacientes-lista")
    public String listarPacientes(Model model) {
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "secretaria/pacientes-lista";
    }
}