package br.com.fiap.tds2ps.spring_mvc.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link br.com.fiap.tds2ps.spring_mvc.model.Paciente}
 */
public class PacienteDto implements Serializable {
    private final Long id;
    private String nomeCompleto;
    private final String cpf;
    private final String email;
    private final String telefone;
    private final String cep;
    private final String complemento;
    private final String genero;

    public PacienteDto(Long id, String nomeCompleto, String cpf, String email, String telefone, String cep, String complemento, String genero) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.cep = cep;
        this.complemento = complemento;
        this.genero = genero;
    }

    public Long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PacienteDto entity = (PacienteDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nomeCompleto, entity.nomeCompleto) &&
                Objects.equals(this.cpf, entity.cpf) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.telefone, entity.telefone) &&
                Objects.equals(this.cep, entity.cep) &&
                Objects.equals(this.complemento, entity.complemento) &&
                Objects.equals(this.genero, entity.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeCompleto, cpf, email, telefone, cep, complemento, genero);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nomeCompleto = " + nomeCompleto + ", " +
                "cpf = " + cpf + ", " +
                "email = " + email + ", " +
                "telefone = " + telefone + ", " +
                "cep = " + cep + ", " +
                "complemento = " + complemento + ", " +
                "genero = " + genero + ")";
    }
}