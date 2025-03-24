package br.com.fiap.tds2ps.spring_mvc.service;

import br.com.fiap.tds2ps.spring_mvc.model.Paciente;
import br.com.fiap.tds2ps.spring_mvc.model.Prontuario;
import br.com.fiap.tds2ps.spring_mvc.repository.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public void salvar(Prontuario prontuario) {
        prontuario.setData(new Date());
        prontuarioRepository.save(prontuario);
    }

    public List<Prontuario> listarPorPaciente(Paciente paciente) {
        return prontuarioRepository.findByPacienteOrderByDataDesc(paciente);
    }
}

