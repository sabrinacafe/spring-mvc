package br.com.fiap.tds2ps.spring_mvc.repository;

import br.com.fiap.tds2ps.spring_mvc.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByNomeContainingIgnoreCase(String nome);
    Optional<Paciente> findByCpf(String cpf);
}