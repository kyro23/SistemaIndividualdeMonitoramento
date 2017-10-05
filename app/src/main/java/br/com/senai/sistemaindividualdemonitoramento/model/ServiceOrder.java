package br.com.senai.sistemaindividualdemonitoramento.model;

/**
 * Created by OC on 22/09/2017.
 */

public class ServiceOrder {

    private Long id;
    private String videoInstrucao;
    private String fotoInstrucao;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVideoInstrucao() {
        return videoInstrucao;
    }

    public void setVideoInstrucao(String videoInstrucao) {
        this.videoInstrucao = videoInstrucao;
    }

    public String getFotoInstrucao() {
        return fotoInstrucao;
    }

    public void setFotoInstrucao(String fotoInstrucao) {
        this.fotoInstrucao = fotoInstrucao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
