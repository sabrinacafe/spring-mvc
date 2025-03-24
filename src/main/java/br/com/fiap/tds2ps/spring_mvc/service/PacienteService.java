package br.com.fiap.tds2ps.spring_mvc.service;

import br.com.fiap.tds2ps.spring_mvc.model.Paciente;
import br.com.fiap.tds2ps.spring_mvc.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    // Injeção via construtor
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public List<Paciente> buscarPorNome(String nomeCompleto) {
        return pacienteRepository.findByNomeCompletoContainingIgnoreCase(nomeCompleto);
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Optional<Paciente> buscarPorCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf);
    }

    public void cadastrar(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    public void atualizar(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    public void excluir(Long id) {
        pacienteRepository.deleteById(id);
    }
}
