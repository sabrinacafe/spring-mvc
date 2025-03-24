package br.com.fiap.tds2ps.spring_mvc.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicoService {

    public Optional<String> autenticarPorCpf(String cpf) {
        if ("111.111.111-11".equals(cpf)) {
            return Optional.of("Maria");
        } else if ("222.222.222-22".equals(cpf)) {
            return Optional.of("João");
        } else {
            return Optional.empty();
        }
    }
}
