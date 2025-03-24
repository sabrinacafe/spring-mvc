package br.com.fiap.tds2ps.spring_mvc.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link br.com.fiap.tds2ps.spring_mvc.model.Medico}
 */
public class MedicoDto implements Serializable {
    private final Long id;
    private final String nome;
    private final String MedicoDTO;

    public MedicoDto(Long id, String nome, String medicoDTO) {
        this.id = id;
        this.nome = nome;
        MedicoDTO = medicoDTO;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getMedicoDTO() {
        return MedicoDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicoDto entity = (MedicoDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nome, entity.nome) &&
                Objects.equals(this.MedicoDTO, entity.MedicoDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, MedicoDTO);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nome = " + nome + ", " +
                "MedicoDTO = " + MedicoDTO + ")";
    }
}