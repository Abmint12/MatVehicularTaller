/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 * @author GRUPO 12
 *  Aguilera Zambrano Juan José
 * Camatón Laínez Segundo Rodolfo
 * Palma Carreño Diego Fernando
 * Zambrano Valverde Luis Abraham
 * Zhinin Muruzumbay Lady Nathaly
 */
public class Matricula {

    private String numeroMatricula;
    private Date fechaEmision;
    private int anioFiscal;
    private String estado;
    private Vehiculo vehiculo;

    // ----------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------

    /**
     * Crea una nueva matrícula asociada a un vehículo.
     */
    public Matricula(String numeroMatricula, Date fechaEmision,
            int anioFiscal, String estado, Vehiculo vehiculo) {

        this.numeroMatricula = numeroMatricula;
        this.fechaEmision = fechaEmision;
        this.anioFiscal = anioFiscal;
        this.estado = estado;
        this.vehiculo = vehiculo;
    }

    // ----------------------------------------------------------------
    // Getters y Setters
    // ----------------------------------------------------------------

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public int getAnioFiscal() {
        return anioFiscal;
    }

    public void setAnioFiscal(int anioFiscal) {
        this.anioFiscal = anioFiscal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    // ----------------------------------------------------------------
    // Métodos de instancia
    // ----------------------------------------------------------------

    /**
     * RF5 - Registrar matrícula.
     *
     * @return true si la matrícula contiene datos válidos.
     */
    public boolean registrarMatricula() {

        if (numeroMatricula == null || numeroMatricula.trim().isEmpty()) {
            System.out.println("Error: el número de matrícula no puede estar vacío.");
            return false;
        }

        if (fechaEmision == null) {
            System.out.println("Error: la fecha de emisión no puede ser nula.");
            return false;
        }

        if (anioFiscal <= 0) {
            System.out.println("Error: el año fiscal debe ser mayor a cero.");
            return false;
        }

        if (vehiculo == null) {
            System.out.println("Error: la matrícula debe estar asociada a un vehículo.");
            return false;
        }

        System.out.println("Matrícula registrada correctamente.");
        return true;
    }

    /**
     * RF6 - Consultar matrícula por número.
     */
    public Matricula consultarMatricula(String numero) {

        if (numero == null || numero.trim().isEmpty()) {
            return null;
        }

        if (numeroMatricula != null && numeroMatricula.equals(numero.trim())) {
            return this;
        }

        return null;
    }

    /**
     * Genera la matrícula cambiando su estado a GENERADA.
     */
    public void generarMatricula() {
        estado = "GENERADA";
    }

    /**
     * Cambia el estado de la matrícula.
     */
    public void cambiarEstado(String estado) {

        if (estado != null && !estado.trim().isEmpty()) {
            this.estado = estado;
        }
    }

    /**
     * Muestra la información de la matrícula.
     */
    public String mostrarDatos() {

        String placaVehiculo = "Sin vehículo asignado";

        if (vehiculo != null) {
            placaVehiculo = vehiculo.getPlaca();
        }

        return "Numero: " + numeroMatricula
                + "\nFecha: " + fechaEmision
                + "\nAño Fiscal: " + anioFiscal
                + "\nEstado: " + estado
                + "\nVehiculo: " + placaVehiculo;
    }
}