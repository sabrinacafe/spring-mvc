package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.model.Paciente;
import br.com.fiap.tds2ps.spring_mvc.model.Prontuario;
import br.com.fiap.tds2ps.spring_mvc.repository.PacienteRepository;
import br.com.fiap.tds2ps.spring_mvc.repository.ProntuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/secretaria")
public class SecretariaController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    // Dashboard inicial
    @GetMapping("/dashboard")
    public String dashboard() {
        return "secretaria-dashboard";
    }

    // Lista pacientes + busca por nome
    @GetMapping("/pacientes")
    public String listarPacientes(@RequestParam(name = "nome", required = false) String nome, Model model) {
        List<Paciente> pacientes = (nome != null && !nome.isBlank())
                ? pacienteRepository.findByNomeContainingIgnoreCase(nome)
                : pacienteRepository.findAll();

        model.addAttribute("pacientes", pacientes);
        return "secretaria/pacientes-lista";
    }

    // Formulário de novo paciente
    @GetMapping("/pacientes/novo")
    public String novoPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "secretaria/paciente-form";
    }

    // Editar paciente existente
    @GetMapping("/pacientes/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado: " + id));
        model.addAttribute("paciente", paciente);
        return "secretaria/paciente-form";
    }

    // Salvar novo ou editado
    @PostMapping("/pacientes/salvar")
    public String salvarPaciente(@ModelAttribute Paciente paciente) {
        pacienteRepository.save(paciente);
        return "redirect:/secretaria/pacientes";
    }

    // Página de confirmação de exclusão
    @GetMapping("/pacientes/excluir/{id}")
    public String confirmarExclusao(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado: " + id));
        model.addAttribute("paciente", paciente);
        return "secretaria/confirmar-exclusao";
    }

    // Excluir paciente após confirmação
    @PostMapping("/pacientes/excluir/{id}")
    public String excluirPaciente(@PathVariable Long id, HttpServletRequest request) {
        pacienteRepository.deleteById(id);
        return "redirect:/secretaria/pacientes";
    }

    // Visualizar histórico de atendimentos
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
