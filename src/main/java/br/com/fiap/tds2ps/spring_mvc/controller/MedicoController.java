package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.model.Medico;
import br.com.fiap.tds2ps.spring_mvc.repository.MedicoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/medico")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    // 🔽 Injeção via construtor
    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @GetMapping("/login")
    public String mostrarTelaLoginMedico() {
        return "medico-login";
    }

    @PostMapping("/login")
    public String autenticarMedico(
            @RequestParam String cpf,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        Optional<Medico> medico = medicoRepository.findByCpf(cpf);
        if (medico.isPresent()) {
            session.setAttribute("nomeProfissional", medico.get().getNome());
            session.setAttribute("cpfProfissional", cpf);
            return "redirect:/consulta/inicio";
        } else {
            redirectAttributes.addFlashAttribute("erro", "CPF inválido");
            return "redirect:/medico/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/medico/login";
    }
}
