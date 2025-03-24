package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.model.Paciente;
import br.com.fiap.tds2ps.spring_mvc.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/secretaria/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    // Injeção via construtor
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public String listar(Model model, @RequestParam(required = false) String nome) {
        List<Paciente> pacientes = (nome != null && !nome.isEmpty())
                ? pacienteService.buscarPorNome(nome)
                : pacienteService.listarTodos();
        model.addAttribute("pacientes", pacientes);
        return "secretaria-pacientes";
    }

    @GetMapping("/novo")
    public String novoPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "formulario-paciente";
    }

    @GetMapping("/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        paciente.ifPresent(value -> model.addAttribute("paciente", value));
        return "formulario-paciente";
    }

    @PostMapping("/salvar")
    public String salvarPaciente(@ModelAttribute Paciente paciente, Model model) {
        if (paciente.getId() != null) {
            pacienteService.atualizar(paciente);
        } else {
            pacienteService.cadastrar(paciente);
        }
        return "redirect:/secretaria/pacientes";
    }

    @GetMapping("/excluir/{id}")
    public String confirmarExclusao(@PathVariable Long id, Model model) {
        model.addAttribute("idPaciente", id);
        return "confirmar-exclusao";
    }

    @PostMapping("/excluir/{id}")
    public String excluirPaciente(@PathVariable Long id) {
        pacienteService.excluir(id);
        return "redirect:/secretaria/pacientes";
    }

    @GetMapping("/historico/{id}")
    public String verHistorico(@PathVariable Long id, Model model) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        paciente.ifPresent(p -> model.addAttribute("paciente", p));
        return "historico-paciente";
    }
}
