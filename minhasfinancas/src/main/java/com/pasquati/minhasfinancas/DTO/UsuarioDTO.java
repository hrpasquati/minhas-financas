package com.pasquati.minhasfinancas.DTO;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class UsuarioDTO implements Serializable {

    @NotEmpty(message = "Esse campo é obrigatório")
    private String nome;
    @Email(message = "Email invalido")
    private String email;
    @NotEmpty(message = "Esse valor é obrigatório")
    private String senha;

    public UsuarioDTO() { }

    public UsuarioDTO(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
