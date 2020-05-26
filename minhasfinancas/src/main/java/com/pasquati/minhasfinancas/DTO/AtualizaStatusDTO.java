package com.pasquati.minhasfinancas.DTO;

import java.io.Serializable;

public class AtualizaStatusDTO implements Serializable {

    private String status;

    public AtualizaStatusDTO() {
    }

    public AtualizaStatusDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
