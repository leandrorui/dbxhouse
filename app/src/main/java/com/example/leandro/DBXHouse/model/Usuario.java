package com.example.leandro.DBXHouse.model;

public class Usuario  implements Serializablezable {

    private Integer idUsuario;
    private String login;
    private int senhaUsuario;

    private String nomeUsuario;
    private String sobrenome;
    private String dataNasc;

    private boolean isAdmin;
    private boolean isMobile;
    private boolean isVisitante;

    public Usuario(int id, String login, int senha,
                   String nome, String sobrenome, String dataNasc,
                   boolean isAdmin, boolean isMobile, boolean isVisitante) {
        this.idUsuario = id;
        this.login = login;
        this.senhaUsuario = senha;

        this.nomeUsuario = nome;
        this.sobrenome = sobrenome;
        this.dataNasc = dataNasc;

        this.isAdmin = isAdmin;
        this.isMobile = isMobile;
        this.isVisitante = isVisitante;
    }

    public boolean isVisitante() {
        return isVisitante;
    }

    public void setVisitante(boolean visitante) {
        isVisitante = visitante;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public int getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(int senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isMobile() {
        return isMobile;
    }

    public void setMobile(boolean mobile) {
        isMobile = mobile;
    }

    @Override
    public boolean equals(Object o){
        return this.idUsuario == ((Usuario)o).idUsuario;
    }

    @Override
    public int hashCode(){
        return this.idUsuario;
    }
}
