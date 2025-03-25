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
        return "paciente-form";
    }

    @GetMapping("/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        paciente.ifPresent(value -> model.addAttribute("paciente", value));
        return "paciente-form";
    }

    @PostMapping("/salvar")
    public String salvarPaciente(@ModelAttribute Paciente paciente) {
        if (paciente.getId() != null) {
            pacienteService.atualizar(paciente);
        } else {
            pacienteService.cadastrar(paciente);
        }
        return "redirect:/secretaria/pacientes-lista";
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
        return "redirect:/secretaria/pacientes-lista";
    }

    @GetMapping("/historico/{id}")
    public String verHistorico(@PathVariable Long id, Model model) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        paciente.ifPresent(p -> {
            model.addAttribute("paciente", p);
            model.addAttribute("historico", prontuarioService.listarPorPaciente(p));
        });
        return "historico-paciente";
    }

    @PostMapping("/historico")
    public String verHistoricoPaciente(@RequestParam String cpf, Model model) {
        Optional<Paciente> paciente = pacienteService.buscarPorCpf(cpf);
        if (paciente.isPresent()) {
            model.addAttribute("paciente", paciente.get());
            model.addAttribute("historico", prontuarioService.listarPorPaciente(paciente.get()));
            return "historico-paciente";
        }
        model.addAttribute("erro", "Paciente não encontrado.");
        return "consulta-inicio";
    }
}