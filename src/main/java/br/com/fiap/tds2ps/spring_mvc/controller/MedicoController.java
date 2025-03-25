package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.service.MedicoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/medico")
public class MedicoController {

    private final MedicoService medicoService;

    // Injeção de dependência via construtor (boa prática)
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    // Página de login
    @GetMapping("/login")
    public String mostrarTelaLoginMedico() {
        return "medico-login";
    }

    // Autenticação
    @PostMapping("/login")
    public String autenticarMedico(
            @RequestParam String cpf,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        return medicoService.autenticarPorCpf(cpf)
                .map(nome -> {
                    session.setAttribute("nomeProfissional", nome);
                    session.setAttribute("cpfProfissional", cpf);
                    return "redirect:/atendimento/iniciar";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("erro", "CPF de profissional não cadastrado");
                    return "redirect:/medico/login";
                });
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/medico/login";
    }
}