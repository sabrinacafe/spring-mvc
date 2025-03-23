package br.com.fiap.tds2ps.spring_mvc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_PACIENTE")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NOME_COMPLETO", nullable = false, length = 100)
    private String nomeCompleto;

    @Column(name = "CPF", nullable = false, length = 14)
    private String cpf;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "TELEFONE", nullable = false, length = 20)
    private String telefone;

    @Column(name = "CEP", nullable = false, length = 10)
    private String cep;

    @Column(name = "COMPLEMENTO", length = 100)
    private String complemento;

    @Column(name = "GENERO", nullable = false, length = 20)
    private String genero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}