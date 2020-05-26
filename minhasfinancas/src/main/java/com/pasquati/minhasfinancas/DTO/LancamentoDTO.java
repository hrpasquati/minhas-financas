package com.pasquati.minhasfinancas.DTO;

import java.io.Serializable;
import java.math.BigDecimal;

public class LancamentoDTO implements Serializable {

    private Long id;
    private String descricao;
    private Integer mes;
    private Integer ano;
    private BigDecimal valaor;
    private Long usuario;
    private String tipo;
    private String status;

    public LancamentoDTO() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public BigDecimal getValaor() {
        return valaor;
    }

    public void setValaor(BigDecimal valaor) {
        this.valaor = valaor;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
