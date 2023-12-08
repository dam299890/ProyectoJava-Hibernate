package com.example.modelo;

import javax.persistence.*;
@Entity
@Table(name="referencias")
public class Referencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_referencia")
    private int id;

    @Column(name = "referencia")
    private String referencia;

    public Referencia() {
    }

    public Referencia(String referencia) {
        this.referencia = referencia;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }


    @Override
    public String toString() {
        return "Referencia{" +
                "id=" + id +
                ", referencia='" + referencia + '\'' +
                '}';
    }
}