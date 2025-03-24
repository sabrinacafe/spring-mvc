package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.model.Paciente;
import br.com.fiap.tds2ps.spring_mvc.model.Prontuario;
import br.com.fiap.tds2ps.spring_mvc.model.Secretaria;
import br.com.fiap.tds2ps.spring_mvc.repository.PacienteRepository;
import br.com.fiap.tds2ps.spring_mvc.repository.ProntuarioRepository;
import br.com.fiap.tds2ps.spring_mvc.repository.SecretariaRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/secretaria")
public class SecretariaController {

    private final PacienteRepository pacienteRepository;
    private final ProntuarioRepository prontuarioRepository;
    private final SecretariaRepository secretariaRepository;

    public SecretariaController(
            PacienteRepository pacienteRepository,
            ProntuarioRepository prontuarioRepository,
            SecretariaRepository secretariaRepository
    ) {
        this.pacienteRepository = pacienteRepository;
        this.prontuarioRepository = prontuarioRepository;
        this.secretariaRepository = secretariaRepository;
    }

    // Tela de login da secretária
    @GetMapping("/login")
    public String loginPage() {
        return "secretaria-login";
    }

    // Autenticação da secretária
    @PostMapping("/login")
    public String autenticarSecretaria(
            @RequestParam String usuario,
            @RequestParam String senha,
            RedirectAttributes redirectAttributes,
            HttpSession session
    ) {
        Optional<Secretaria> secretaria = secretariaRepository.findByUsuarioAndSenha(usuario, senha);
        if (secretaria.isPresent()) {
            session.setAttribute("secretaria", secretaria.get());
            return "redirect:/secretaria/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("erro", "Usuário ou senha inválidos!");
            return "redirect:/secretaria/login";
        }
    }

    // Dashboard após login
    @GetMapping("/dashboard")
    public String dashboard() {
        return "secretaria-dashboard";
    }

    // Lista pacientes
    @GetMapping("/pacientes")
    public String listarPacientes(@RequestParam(name = "nome", required = false) String nome, Model model) {
        List<Paciente> pacientes = (nome != null && !nome.isBlank())
                ? pacienteRepository.findByNomeContainingIgnoreCase(nome)
                : pacienteRepository.findAll();

        model.addAttribute("pacientes", pacientes);
        return "secretaria/pacientes-lista";
    }

    @GetMapping("/pacientes/novo")
    public String novoPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "secretaria/paciente-form";
    }

    @GetMapping("/pacientes/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado: " + id));

        model.addAttribute("paciente", paciente);
        return "secretaria/paciente-form";
    }

    @PostMapping("/pacientes/salvar")
    public String salvarPaciente(@ModelAttribute Paciente paciente) {
        pacienteRepository.save(paciente);
        return "redirect:/secretaria/pacientes";
    }

    @GetMapping("/pacientes/excluir/{id}")
    public String confirmarExclusao(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado: " + id));

        model.addAttribute("paciente", paciente);
        return "secretaria/confirmar-exclusao";
    }

    @PostMapping("/pacientes/excluir/{id}")
    public String excluirPaciente(@PathVariable Long id) {
        pacienteRepository.deleteById(id);
        return "redirect:/secretaria/pacientes";
    }

    @GetMapping("/pacientes/historico/{id}")
    public String historicoPaciente(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado: " + id));

        List<Prontuario> historico = prontuarioRepository.findByPacienteOrderByDataDesc(paciente);

        model.addAttribute("paciente", paciente);
        model.addAttribute("historico", historico);
        return "secretaria/historico-paciente";
    }
}
