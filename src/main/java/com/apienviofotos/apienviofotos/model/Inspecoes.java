package com.apienviofotos.apienviofotos.model;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;

@Entity
public class Inspecoes {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data_inspecao;
    private LocalDateTime data_lancamento;
    private String observacao;

    private String tipo;
    private double valor;
    private Long endereco;
    private Long proponente;
    private Long risco;
    private Long seguradora;
    private long usuario;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData_inspecao() {
        return data_inspecao;
    }

    public void setData_inspecao(LocalDateTime data_inspecao) {
        this.data_inspecao = data_inspecao;
    }

    public LocalDateTime getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(LocalDateTime data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Long getEndereco() {
        return endereco;
    }

    public void setEndereco(Long endereco) {
        this.endereco = endereco;
    }

    public Long getProponente() {
        return proponente;
    }

    public void setProponente(Long proponente) {
        this.proponente = proponente;
    }

    public Long getRisco() {
        return risco;
    }

    public void setRisco(Long risco) {
        this.risco = risco;
    }

    public Long getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Long seguradora) {
        this.seguradora = seguradora;
    }

    public long getUsuario() {
        return usuario;
    }

    public void setUsuario(long usuario) {
        this.usuario = usuario;
    }



}
