package com.example.modelo;

import java.time.LocalDate;
import javax.persistence.*;
@Entity
@Table(name="informacion")
public class Informacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "fecha_creacion", insertable = false)
    private LocalDate fecha;

    @Column(name="peso_inicial")
    private double pesoIni;

    @Column(name="peso_final")
    private double pesoFin;

    @Column(name="diferencia")
    private double diferenciaPeso;

    @Column(name="referencia")
    private String referencia;

    @Column(name="estado")
    private String estado;

    @Column(name="observaciones")
    private String observacion;
    @Column(name="numero_informe")
    private String numeroInforme;

    @Column(name="temperatura")
    private double temperatura;

    @Column(name="humedad")
    private double humedad;

    @Column(name="alcance_extraccion")
    private String alcanceExtraccion;

    @Column(name="resultado_muestra")
    private double resultadoMuestra;

    @Column(name="residuo_blanco")
    private double residuoBlanco;

    @Column(name="resultado_blanco")
    private double resultadoBlanco;

    @Column(name="estado_muestra")
    private String estadoMuestra;

    @Column(name="estado_blanco")
    private String estadoBlanco;
    public Informacion() {

    }


    public Informacion(String referencia, LocalDate fecha) {
        this.referencia = referencia;
        this.fecha = fecha;
    }

    public Informacion(double pesoIni, double pesoFin,
                       String referencia,
                       String estado, String observacion,
                       String numeroInforme, double temperatura,
                       double humedad, String alcanceExtraccion,
                       double resultadoMuestra, double residuoBlanco,
                       double resultadoBlanco, String estadoMuestra,
                       String estadoBlanco) {
        this.pesoIni = pesoIni;
        this.pesoFin = pesoFin;
        this.diferenciaPeso = pesoFin-pesoIni;
        this.referencia = referencia;
        this.estado = estado;
        this.observacion = observacion;
        this.numeroInforme = numeroInforme;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.alcanceExtraccion = alcanceExtraccion;
        this.resultadoMuestra = resultadoMuestra;
        this.residuoBlanco = residuoBlanco;
        this.resultadoBlanco = resultadoBlanco;
        this.estadoMuestra = estadoMuestra;
        this.estadoBlanco = estadoBlanco;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getPesoIni() {
        return pesoIni;
    }

    public void setPesoIni(double pesoIni) {
        this.pesoIni = pesoIni;
    }

    public double getPesoFin() {
        return pesoFin;
    }

    public void setPesoFin(double pesoFin) {
        this.pesoFin = pesoFin;
    }

    public double getDiferenciaPeso() {
        return diferenciaPeso;
    }

    public void setDiferenciaPeso(double diferenciaPeso) {
        this.diferenciaPeso = diferenciaPeso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getNumeroInforme() {
        return numeroInforme;
    }

    public void setNumeroInforme(String numeroInforme) {
        this.numeroInforme = numeroInforme;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getHumedad() {
        return humedad;
    }

    public void setHumedad(double humedad) {
        this.humedad = humedad;
    }

    public String getAlcanceExtraccion() {
        return alcanceExtraccion;
    }

    public void setAlcanceExtraccion(String alcanceExtraccion) {
        this.alcanceExtraccion = alcanceExtraccion;
    }

    public double getResultadoMuestra() {
        return resultadoMuestra;
    }

    public void setResultadoMuestra(double resultadoMuestra) {
        this.resultadoMuestra = resultadoMuestra;
    }

    public double getResiduoBlanco() {
        return residuoBlanco;
    }

    public void setResiduoBlanco(double residuoBlanco) {
        this.residuoBlanco = residuoBlanco;
    }

    public double getResultadoBlanco() {
        return resultadoBlanco;
    }

    public void setResultadoBlanco(double resultadoBlanco) {
        this.resultadoBlanco = resultadoBlanco;
    }

    public String getEstadoMuestra() {
        return estadoMuestra;
    }

    public void setEstadoMuestra(String estadoMuestra) {
        this.estadoMuestra = estadoMuestra;
    }

    public String getEstadoBlanco() {
        return estadoBlanco;
    }

    public void setEstadoBlanco(String estadoBlanco) {
        this.estadoBlanco = estadoBlanco;
    }


    @Override
    public String toString() {
        return "Informacion{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", pesoIni=" + pesoIni +
                ", pesoFin=" + pesoFin +
                ", diferenciaPeso=" + diferenciaPeso +
                ", referencia='" + referencia + '\'' +
                ", estado='" + estado + '\'' +
                ", observacion='" + observacion + '\'' +
                '}';
    }
}
