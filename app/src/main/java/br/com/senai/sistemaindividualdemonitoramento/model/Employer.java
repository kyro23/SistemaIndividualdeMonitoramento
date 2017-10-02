package br.com.senai.sistemaindividualdemonitoramento.model;

import java.io.Serializable;

/**
 * Created by OC on 22/09/2017.
 */

public class Employer implements Serializable{

    private Long matricula;
    private String senha;
    private String nome;
    private String tipo;

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
