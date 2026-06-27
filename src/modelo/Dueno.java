/*
 * Verficacion-Validacion Software
 */
package modelo;
import java.util.Date;

/**
 * @author Grupo 12
 * Aguilera Zambrano Juan José
 * Camatón Laínez Segundo Rodolfo
 * Palma Carreño Diego Fernando
 * Zambrano Valverde Luis Abraham
 * Zhinin Muruzumbay Lady Nathaly
 */
public class Dueno extends Persona{
     private Date fechaRegistro;

    public Dueno(String cedula, String nombres, String apellidos,
            String direccion, String telefono,
            String correo, Date fechaRegistro) {
        super(cedula, nombres, apellidos, direccion, telefono, correo);
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void registrarDueno() {
        System.out.println("Dueño registrado correctamente.");
    }

    public Dueno consultarDueno(String cedula) {

        if (getCedula().equals(cedula)) {
            return this;
        }

        return null;
    }

    public void actualizarDatos() {
        System.out.println("Datos actualizados.");
    }

    @Override
    public String mostrarDatos() {

        return super.mostrarDatos()
                + "\nFecha Registro: " + fechaRegistro;
    }

}
