
package br.com.fiap.tds2ps.spring_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {

    @GetMapping("/novo")
    public String iniciarConsulta() {
        return "consulta-inicio";
    }

    @PostMapping("/salvar")
    public String salvarConsulta(@RequestParam String cpfPaciente, Model model) {
        return "redirect:/consulta/prontuario";
    }
}
