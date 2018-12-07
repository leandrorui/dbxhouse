package com.example.leandro.DBXHouse.model;

public class RegistroSensorModel {

    private Integer idRegistroSensor;
    private SensorModel sensor;
    private Integer ValorRegistro;

    public Integer getIdRegistroSensor() {
        return idRegistroSensor;
    }

    public void setIdRegistroSensor(Integer idRegistroSensor) {
        this.idRegistroSensor = idRegistroSensor;
    }

    public SensorModel getSensor() {
        return sensor;
    }

    public void setSensor(SensorModel sensor) {
        this.sensor = sensor;
    }

    public Integer getValorRegistro() {
        return ValorRegistro;
    }

    public void setValorRegistro(Integer valorRegistro) {
        ValorRegistro = valorRegistro;
    }
}
