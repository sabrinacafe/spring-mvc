
package br.com.fiap.tds2ps.spring_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/paciente")
public class HistoricoController {

    @GetMapping("/historico/{id}")
    public String historicoPaciente(@PathVariable Long id, Model model) {
        return "historico-consultas";
    }

    @GetMapping("/prontuario")
    public String exibirProntuario() {
        return "consulta-prontuario";
    }
}
