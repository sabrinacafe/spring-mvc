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
@RequestMapping("/atendimento")
public class ProntuarioController {

    private final PacienteService pacienteService;
    private final ProntuarioService prontuarioService;

    public ProntuarioController(PacienteService pacienteService, ProntuarioService prontuarioService) {
        this.pacienteService = pacienteService;
        this.prontuarioService = prontuarioService;
    }

    @GetMapping
    public String atendimento(@RequestParam String cpf, Model model) {
        System.out.println("Chegou no atendimento com CPF: " + cpf);

        Optional<Paciente> paciente = pacienteService.buscarPorCpf(cpf);
        if (paciente.isPresent()) {
            model.addAttribute("paciente", paciente.get());

            List<Prontuario> historico = prontuarioService.listarPorPaciente(paciente.get());
            model.addAttribute("historico", historico);
            return "atendimento";
        }

        System.out.println("Paciente não encontrado");
        model.addAttribute("erro", "Paciente não encontrado.");
        return "novo-atendimento";
    }

    @PostMapping("/salvar")
    public String salvarConsulta(@RequestParam String cpf,
                                 @RequestParam String anamnese,
                                 @RequestParam String prescricao) {
        Optional<Paciente> paciente = pacienteService.buscarPorCpf(cpf);
        if (paciente.isPresent()) {
            Prontuario prontuario = new Prontuario();
            prontuario.setPaciente(paciente.get());
            prontuario.setAnamnese(anamnese);
            prontuario.setPrescricao(prescricao);
            prontuarioService.salvar(prontuario);
        }

        return "redirect:/";
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
