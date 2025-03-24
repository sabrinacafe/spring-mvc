package br.com.fiap.tds2ps.spring_mvc.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link br.com.fiap.tds2ps.spring_mvc.model.Secretaria}
 */
public class SecretariaDto implements Serializable {
    private final Long id;
    private final String usuario;
    private final String SecretariaDTO;

    public SecretariaDto(Long id, String usuario, String secretariaDTO) {
        this.id = id;
        this.usuario = usuario;
        SecretariaDTO = secretariaDTO;
    }

    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSecretariaDTO() {
        return SecretariaDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecretariaDto entity = (SecretariaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.usuario, entity.usuario) &&
                Objects.equals(this.SecretariaDTO, entity.SecretariaDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, SecretariaDTO);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "usuario = " + usuario + ", " +
                "SecretariaDTO = " + SecretariaDTO + ")";
    }
}