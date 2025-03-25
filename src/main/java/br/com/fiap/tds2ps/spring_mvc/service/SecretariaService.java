package br.com.fiap.tds2ps.spring_mvc.service;

import br.com.fiap.tds2ps.spring_mvc.model.Secretaria;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecretariaService {

    public Optional<Secretaria> autenticar(String usuario, String senha) {
        if ("admin123".equals(usuario) && "admin123".equals(senha)) {
            Secretaria secretaria = new Secretaria();
            secretaria.setId(1L); // ou null, se preferir
            secretaria.setUsuario(usuario);
            return Optional.of(secretaria);
        }
        return Optional.empty();
    }
}