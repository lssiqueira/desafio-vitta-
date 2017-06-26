/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Leandro
 */
public class Tarefa {
    private int id;
    private String descricao;
    private String dataEHoraRealizada;
    private long tempoDeDuracao;
    private long lembrete;
    private String dataEHoraCriacao;
    private String dataEHoraEdicao;
    private String dataEHoraRemocao;
    private boolean realizada;

    public Tarefa(int id, String descricao, String dataEHoraRealizada, long tempoDeDuracao, long lembrete, String dataEHoraCriacao, String dataEHoraEdicao, String dataEHoraRemocao, boolean realizada) {
        this.id = id;
        this.descricao = descricao;
        this.dataEHoraRealizada = dataEHoraRealizada;
        this.tempoDeDuracao = tempoDeDuracao;
        this.lembrete = lembrete;
        this.dataEHoraCriacao = dataEHoraCriacao;
        this.dataEHoraEdicao = dataEHoraEdicao;
        this.dataEHoraRemocao = dataEHoraRemocao;
        this.realizada = realizada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataEHoraRealizada() {
        return dataEHoraRealizada;
    }

    public void setDataEHoraRealizada(String dataEHoraRealizada) {
        this.dataEHoraRealizada = dataEHoraRealizada;
    }

    public long getTempoDeDuracao() {
        return tempoDeDuracao;
    }

    public void setTempoDeDuracao(long tempoDeDuracao) {
        this.tempoDeDuracao = tempoDeDuracao;
    }

    public long getLembrete() {
        return lembrete;
    }

    public void setLembrete(long lembrete) {
        this.lembrete = lembrete;
    }

    public String getDataEHoraCriacao() {
        return dataEHoraCriacao;
    }

    public void setDataEHoraCriacao(String dataEHoraCriacao) {
        this.dataEHoraCriacao = dataEHoraCriacao;
    }

    public String getDataEHoraEdicao() {
        return dataEHoraEdicao;
    }

    public void setDataEHoraEdicao(String dataEHoraEdicao) {
        this.dataEHoraEdicao = dataEHoraEdicao;
    }

    public String getDataEHoraRemocao() {
        return dataEHoraRemocao;
    }

    public void setDataEHoraRemocao(String dataEHoraRemocao) {
        this.dataEHoraRemocao = dataEHoraRemocao;
    }
    
    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

}
