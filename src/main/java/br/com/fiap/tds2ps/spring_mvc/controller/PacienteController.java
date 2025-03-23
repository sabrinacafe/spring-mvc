package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.model.Paciente;
import br.com.fiap.tds2ps.spring_mvc.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    // Mostrar formulário de novo paciente
    @GetMapping("/novo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "paciente_forms"; // nome do template HTML
    }

    // Salvar novo ou editar paciente
    @PostMapping("/salvar")
    public String salvarPaciente(@ModelAttribute("paciente") Paciente paciente, Model model) {
        Optional<Paciente> pacienteExistente = pacienteService.buscarPorCpf(paciente.getCpf());

        if (pacienteExistente.isPresent()) {
            paciente.setId(pacienteExistente.get().getId()); // garantir update
            pacienteService.atualizar(paciente);
            model.addAttribute("mensagem", "Paciente editado com sucesso!");
        } else {
            pacienteService.cadastrar(paciente);
            model.addAttribute("mensagem", "Paciente cadastrado com sucesso!");
        }

        return "redirect:/paciente/prontuario/" + paciente.getId(); // pode redirecionar pra consulta, por ex
    }

    // Buscar paciente por CPF e decidir próximo passo
    @PostMapping("/buscar")
    public String buscarPorCpf(@RequestParam("cpf") String cpf, Model model) {
        Optional<Paciente> paciente = pacienteService.buscarPorCpf(cpf);

        if (paciente.isPresent()) {
            model.addAttribute("paciente", paciente.get());
            return "opcoes-paciente"; // página com botões "Editar" ou "Iniciar Consulta"
        } else {
            return "redirect:/paciente/novo"; // redireciona para formulário de cadastro
        }
    }

    // Mostrar formulário para edição
    @GetMapping("/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        paciente.ifPresent(value -> model.addAttribute("paciente", value));
        return "paciente_forms"; // reaproveita o mesmo formulário
    }

    // Redireciona para tela de anamnese/prontuário
    @GetMapping("/prontuario/{id}")
    public String abrirProntuario(@PathVariable Long id, Model model) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        paciente.ifPresent(value -> model.addAttribute("paciente", value));
        return "patient-save"; // ou outro HTML de prontuário
    }
}
