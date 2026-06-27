/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.Date;

/**
 *
 * @author GRUPO 12
 *  Aguilera Zambrano Juan José
 * Camatón Laínez Segundo Rodolfo
 * Palma Carreño Diego Fernando
 * Zambrano Valverde Luis Abraham
 * Zhinin Muruzumbay Lady Nathaly
 */
public class Pago {
  private String codigoPago;
    private Date fechaPago;
    private double valor;
    private String metodoPago;
    private String estado;

    public Pago(String codigoPago, Date fechaPago,
            double valor, String metodoPago,
            String estado) {

        this.codigoPago = codigoPago;
        this.fechaPago = fechaPago;
        this.valor = valor;
        this.metodoPago = metodoPago;
        this.estado = estado;

    }

    public void realizarPago() {

        estado = "PAGADO";

        System.out.println("Pago realizado correctamente.");

    }

    public boolean validarPago() {

        return estado.equalsIgnoreCase("PAGADO");

    }

    public Pago consultarPago(String codigo) {

        if (codigoPago.equals(codigo))
            return this;

        return null;

    }

    public String mostrarDatos() {

        return "Codigo: " + codigoPago
                + "\nFecha: " + fechaPago
                + "\nValor: $" + valor
                + "\nMetodo: " + metodoPago
                + "\nEstado: " + estado;

    }   
}
