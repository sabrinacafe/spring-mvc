package br.com.fiap.tds2ps.spring_mvc.repository;

import br.com.fiap.tds2ps.spring_mvc.model.Secretaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretariaRepository extends JpaRepository<Secretaria, Long> {
}