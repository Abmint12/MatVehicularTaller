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

    public Matricula(String numeroMatricula, Date fechaEmision,
            int anioFiscal, String estado,
            Vehiculo vehiculo) {

        this.numeroMatricula = numeroMatricula;
        this.fechaEmision = fechaEmision;
        this.anioFiscal = anioFiscal;
        this.estado = estado;
        this.vehiculo = vehiculo;

    }

    public void registrarMatricula() {

        System.out.println("Matricula registrada.");

    }

    public Matricula consultarMatricula(String numero) {

        if (numeroMatricula.equals(numero))
            return this;

        return null;

    }

    public void generarMatricula() {

        estado = "GENERADA";

    }

    public void cambiarEstado(String estado) {

        this.estado = estado;

    }

    public String mostrarDatos() {

        return "Numero: " + numeroMatricula
                + "\nFecha: " + fechaEmision
                + "\nAño Fiscal: " + anioFiscal
                + "\nEstado: " + estado
                + "\nVehiculo: " + vehiculo.getPlaca();

    }
}
