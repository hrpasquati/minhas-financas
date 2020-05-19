package com.pasquati.minhasfinancas.model.entity;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuario", schema = "financas") //O banco de dados Postgres exige o schema
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    @Nullable
    private String nome;

    private String email;
    private String senha;
}
