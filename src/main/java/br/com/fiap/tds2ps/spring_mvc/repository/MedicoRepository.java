package br.com.fiap.tds2ps.spring_mvc.repository;

import br.com.fiap.tds2ps.spring_mvc.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}