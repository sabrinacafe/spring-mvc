package br.com.fiap.tds2ps.spring_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    // Página inicial
    @GetMapping("/")
    public String index(Model model) {
        return "index"; // index.html no templates
    }

    // Processa o login do profissional
    @PostMapping("/sign-in")
    public String autenticarProfissional(@RequestParam("cpf") String cpf, RedirectAttributes redirectAttributes) {

        if (cpf.equals("111.111.111-11")) {
            redirectAttributes.addFlashAttribute("nomeProfissional", "Maria");
            return "redirect:/entrada";
        } else if (cpf.equals("222.222.222-22")) {
            redirectAttributes.addFlashAttribute("nomeProfissional", "João");
            return "redirect:/entrada";
        } else {
            redirectAttributes.addFlashAttribute("erro", "CPF inválido. Tente novamente.");
            return "redirect:/";
        }
    }

    // Tela após o login do médico
    @GetMapping("/entrada")
    public String telaEntrada(Model model) {
        return "paciente_cpf"; // Exibe a tela com as opções do médico
    }
}
