package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.model.Paciente;
import br.com.fiap.tds2ps.spring_mvc.model.Prontuario;
import br.com.fiap.tds2ps.spring_mvc.service.PacienteService;
import br.com.fiap.tds2ps.spring_mvc.service.ProntuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/secretaria/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;
    private final ProntuarioService prontuarioService;

    public PacienteController(PacienteService pacienteService, ProntuarioService prontuarioService) {
        this.pacienteService = pacienteService;
        this.prontuarioService = prontuarioService;
    }

    @GetMapping
    public String listar(Model model, @RequestParam(required = false) String nome) {
        List<Paciente> pacientes = (nome != null && !nome.isEmpty())
                ? pacienteService.buscarPorNome(nome)
                : pacienteService.listarTodos();
        model.addAttribute("pacientes", pacientes);
        return "secretaria/pacientes-lista";
    }

    @GetMapping("/novo")
    public String novoPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "secretaria/paciente-form";
    }

    @GetMapping("/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        paciente.ifPresent(value -> model.addAttribute("paciente", value));
        return "secretaria/paciente-form";
    }

    @PostMapping("/salvar")
    public String salvarPaciente(@ModelAttribute Paciente paciente) {
        if (paciente.getId() != null) {
            pacienteService.atualizar(paciente);
        } else {
            pacienteService.cadastrar(paciente);
        }
        return "redirect:/secretaria/pacientes";
    }

    @GetMapping("/excluir/{id}")
    public String confirmarExclusao(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));
        model.addAttribute("paciente", paciente);
        return "secretaria/confirmar-exclusao";
    }

    @PostMapping("/excluir/{id}")
    public String excluirPaciente(@PathVariable Long id) {
        pacienteService.excluir(id);
        return "redirect:/secretaria/pacientes";
    }

    @GetMapping("/historico/{id}")
    public String verHistorico(@PathVariable Long id, Model model) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        paciente.ifPresent(p -> {
            model.addAttribute("paciente", p);
            model.addAttribute("historico", prontuarioService.listarPorPaciente(p));
        });
        return "secretaria/historico-paciente";
    }
}