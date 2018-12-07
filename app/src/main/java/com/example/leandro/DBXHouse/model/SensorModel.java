package com.example.leandro.DBXHouse.model;

public class SensorModel {

    private Integer idSensor;
    private String nomeSensor;
    private boolean estadoSensor;
    private String funcaoSensor;


    public Integer getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(Integer idSensor) {
        this.idSensor = idSensor;
    }

    public String getNomeSensor() {
        return nomeSensor;
    }

    public void setNomeSensor(String nomeSensor) {
        this.nomeSensor = nomeSensor;
    }

    public boolean isEstadoSensor() {
        return estadoSensor;
    }

    public void setEstadoSensor(boolean estadoSensor) {
        this.estadoSensor = estadoSensor;
    }

    public String getFuncaoSensor() {
        return funcaoSensor;
    }

    public void setFuncaoSensor(String funcaoSensor) {
        this.funcaoSensor = funcaoSensor;
    }
}
