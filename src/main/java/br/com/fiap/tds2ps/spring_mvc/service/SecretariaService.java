package br.com.fiap.tds2ps.spring_mvc.service;

import br.com.fiap.tds2ps.spring_mvc.model.Secretaria;
import br.com.fiap.tds2ps.spring_mvc.repository.SecretariaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecretariaService {

    private final SecretariaRepository secretariaRepository;

    public SecretariaService(SecretariaRepository secretariaRepository) {
        this.secretariaRepository = secretariaRepository;
    }

    public Optional<Secretaria> autenticar(String usuario, String senha) {
        return secretariaRepository.findByUsuarioAndSenha(usuario, senha);
    }
}