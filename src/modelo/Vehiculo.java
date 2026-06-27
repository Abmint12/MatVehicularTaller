/*
 * Verificacion-Validacion-Software
 */
package modelo;

/**
 *
 * @author Grupo 12
 *  Aguilera Zambrano Juan José
 * Camatón Laínez Segundo Rodolfo
 * Palma Carreño Diego Fernando
 * Zambrano Valverde Luis Abraham
 * Zhinin Muruzumbay Lady Nathaly
 */
public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int anio;
    private String color;
    private String tipo;
    private Dueno propietario;

    public Vehiculo(String placa, String marca, String modelo, int anio, String color, String tipo, Dueno propietario) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.color = color;
        this.tipo = tipo;
        this.propietario = propietario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Dueno getPropietario() {
        return propietario;
    }

    public void setPropietario(Dueno propietario) {
        this.propietario = propietario;
    }
    //métodos
    public Vehiculo consultarVehiculo(String placa){
        if(this.placa.equals(placa)){
            return this;
        }
        return null;
    }
    public String mostrarDatos(){
         return "Placa: " + placa
                + "\nMarca: " + marca
                + "\nModelo: " + modelo
                + "\nAño: " + anio
                + "\nColor: " + color
                + "\nTipo: " + tipo
                + "\nPropietario: " + propietario.getNombres() + " " + propietario.getApellidos();
    }
    
    
}
