package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.model.Paciente;
import br.com.fiap.tds2ps.spring_mvc.model.Prontuario;
import br.com.fiap.tds2ps.spring_mvc.service.PacienteService;
import br.com.fiap.tds2ps.spring_mvc.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/consulta")
public class ProntuarioController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ProntuarioService prontuarioService;

    @GetMapping("/inicio")
    public String novaConsultaForm() {
        return "consulta-inicio";
    }

    @PostMapping("/buscar")
    public String buscarPaciente(@RequestParam String cpf, Model model) {
        Optional<Paciente> paciente = pacienteService.buscarPorCpf(cpf);
        if (paciente.isPresent()) {
            model.addAttribute("paciente", paciente.get());
            model.addAttribute("historico", prontuarioService.listarPorPaciente(paciente.get()));
            model.addAttribute("prontuario", new Prontuario());
            return "consulta-form";
        }
        model.addAttribute("erro", "Paciente não encontrado");
        return "consulta-inicio";
    }

    @PostMapping("/salvar")
    public String salvarConsulta(@ModelAttribute Prontuario prontuario) {
        prontuarioService.salvar(prontuario);
        return "redirect:/consulta/inicio";
    }
}
