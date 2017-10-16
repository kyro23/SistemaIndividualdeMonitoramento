package br.com.senai.sistemaindividualdemonitoramento.model;

/**
 * Created by OC on 22/09/2017.
 */

public class Activity {


    private Long id;
    private String horaFim;
    private int perda;
    private int funcionario;
    private int producao;
    private String horaInicio;
    private int os;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public int getPerda() {
        return perda;
    }

    public void setPerda(int perda) {
        this.perda = perda;
    }

    public int getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

    public int getProducao() {
        return producao;
    }

    public void setProducao(int producao) {
        this.producao = producao;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getOs() {
        return os;
    }

    public void setOs(int os) {
        this.os = os;
    }
}
