package com.apienviofotos.apienviofotos.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InspecaoRemota {

    private Long vistoria;

    private byte[] foto;
    private String descricao;
    private String latitude;
    private String longitude;
    private String observacao;

   // private LocalDateTime dataHora;
    private String tipo;


    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

//    public LocalDateTime getDataHora() {
//        return dataHora;
//    }

//    public void setDataHora(String dataHora) {
//        this.dataHora = LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
//    }

    public Long getVistoria() {
        return vistoria;
    }

    public void setVistoria(Long vistoria) {
        this.vistoria = vistoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }



}

