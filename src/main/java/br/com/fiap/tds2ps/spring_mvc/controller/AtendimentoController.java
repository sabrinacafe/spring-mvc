package br.com.fiap.tds2ps.spring_mvc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/atendimento")
public class AtendimentoController {

    // Exibe o formulário de início do atendimento
    @GetMapping("/iniciar")
    public String mostrarFormularioAtendimento() {
        return "novo-atendimento"; // esse é o HTML que contém o formulário de CPF
    }

    // Processa o CPF enviado no formulário
    @PostMapping("/iniciar")
    public String iniciarAtendimento(@RequestParam String cpf, HttpSession session) {
        session.setAttribute("cpfPaciente", cpf);
        return "redirect:/atendimento/prontuario"; // redireciona para preencher o prontuário
    }

    // Exibe o formulário do prontuário (anamnese + prescrição)
    @GetMapping("/prontuario")
    public String exibirFormularioDeAtendimento() {
        return "atendimento-prontuario"; // página com anamnese e prescrição
    }
}