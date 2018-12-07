package com.example.leandro.DBXHouse.model;

public class DispositivoModel {

    private Integer idDispositivo;
    private String nomeDispositivo;
    private ComodoModel comodo;
    private boolean estadoDispositivo;

    public Integer getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(Integer idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getNomeDispositivo() {
        return nomeDispositivo;
    }

    public void setNomeDispositivo(String nomeDispositivo) {
        this.nomeDispositivo = nomeDispositivo;
    }

    public ComodoModel getComodo() {
        return comodo;
    }

    public void setComodo(ComodoModel comodo) {
        this.comodo = comodo;
    }

    public boolean isEstadoDispositivo() {
        return estadoDispositivo;
    }

    public void setEstadoDispositivo(boolean estadoDispositivo) {
        this.estadoDispositivo = estadoDispositivo;
    }
}