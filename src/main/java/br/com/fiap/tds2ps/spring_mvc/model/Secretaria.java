package br.com.fiap.tds2ps.spring_mvc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_SECRETARIA")
public class Secretaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "USUARIO", nullable = false, length = 50)
    private String usuario;

    @Column(name = "SENHA", nullable = false, length = 100)
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}