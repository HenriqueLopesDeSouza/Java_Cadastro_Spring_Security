package com.br.Desenvolvimento_de_Software.model;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "usuario", schema = "teste")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "senha")
    private String senha;

    @Transient
    @Column(name = "senha_2")
    private String senha_2;

    @Column(name = "email")
    private String email;

    @Column(name = "funcao")
    private int funcao;

    public Usuario() {
    }

    public Usuario(String nome, String senha, String email, int funcao) {
        this.setnome(nome);
        this.setsenha(senha);
        this.setEmail(email);
        this.setfuncao(funcao);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnome() {
        return nome;
    }

    public void setnome(String nome) {
        this.nome = nome;
    }

    public String getsenha() {
        return senha;
    }

    public void setsenha(String senha) {
        this.senha = senha;
    }

    public String getsenha_2() {
        return senha_2;
    }

    public void setsenha_2(String senha_2) {
        this.senha_2 = senha_2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getfuncao() {
        return funcao;
    }

    public void setfuncao(int funcao) {
        this.funcao = funcao;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario user = (Usuario) o;
        return id == user.id &&
                funcao == user.funcao &&
                Objects.equals(nome, user.nome) &&
                Objects.equals(senha, user.senha) &&
                Objects.equals(senha_2, user.senha_2) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nome, senha, senha_2, email, funcao);
    }
}
