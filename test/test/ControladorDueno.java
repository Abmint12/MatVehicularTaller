/*
 * Verificacion-Validacion Software
 * Taller Testing OO - Controlador: Dueno
 */
package test;

import modelo.Dueno;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ControladorDueno
 * Intermediario entre la Vista y el Modelo (patron MVC).
 * Gestiona la lista de duenos registrados en memoria y
 * expone operaciones de registro, consulta y actualizacion.
 *
 * @author Grupo 12
 * Aguilera Zambrano Juan Jose
 * Camaton Lainez Segundo Rodolfo
 * Palma Carreno Diego Fernando
 * Zambrano Valverde Luis Abraham
 * Zhinin Muruzumbay Lady Nathaly
 */
public class ControladorDueno {

    // Lista en memoria que actua como repositorio de duenos
    private List<Dueno> listaDuenos;

    public ControladorDueno() {
        this.listaDuenos = new ArrayList<>();
    }
    // ------------------------------------------------------------------
    // RF1 - Registrar dueno
    // ------------------------------------------------------------------

    /**
     * Registra un nuevo dueno si la cedula no esta duplicada y los datos son validos.
     *
     * @return mensaje de resultado para mostrar en la vista
     */
    public String registrarDueno(String cedula, String nombres, String apellidos,
                                  String direccion, String telefono, String correo) {
        // Verificar duplicado
        if (buscarPorCedula(cedula) != null) {
            return "ERROR: Ya existe un dueno registrado con la cedula " + cedula + ".";
        }

        try {
            Dueno nuevo = new Dueno(cedula, nombres, apellidos,
                                    direccion, telefono, correo, new Date());
            boolean ok = nuevo.registrarDueno();
            if (ok) {
                listaDuenos.add(nuevo);
                return "Dueno registrado correctamente.";
            } else {
                return "ERROR: Los datos ingresados no son validos. Verifique cedula, nombre y correo.";
            }
        } catch (IllegalArgumentException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    // ------------------------------------------------------------------
    // RF2 - Consultar dueno
    // ------------------------------------------------------------------

    /**
     * Busca y retorna un dueno por cedula.
     *
     * @param cedula cedula a buscar
     * @return el Dueno encontrado o null
     */
    public Dueno consultarDueno(String cedula) {
        return buscarPorCedula(cedula);
    }

    /**
     * Retorna los datos formateados de un dueno para mostrar en la vista.
     */
    public String getDatosDueno(String cedula) {
        Dueno d = buscarPorCedula(cedula);
        if (d == null) {
            return "ERROR: No se encontro ningun dueno con la cedula " + cedula + ".";
        }
        return d.mostrarDatos();
    }

    // ------------------------------------------------------------------
    // Actualizar datos
    // ------------------------------------------------------------------

    /**
     * Actualiza direccion, telefono y/o correo de un dueno existente.
     */
    public String actualizarDueno(String cedula, String nuevaDireccion,
                                   String nuevoTelefono, String nuevoCorreo) {
        Dueno d = buscarPorCedula(cedula);
        if (d == null) {
            return "ERROR: No se encontro dueno con cedula " + cedula + ".";
        }
        boolean ok = d.actualizarDatos(nuevaDireccion, nuevoTelefono, nuevoCorreo);
        return ok ? "Datos del dueno actualizados correctamente."
                  : "ERROR: No se pudo actualizar. Verifique los datos ingresados.";
    }

    // ------------------------------------------------------------------
    // Auxiliares
    // ------------------------------------------------------------------

    public List<Dueno> getListaDuenos() {
        return listaDuenos;
    }

    public int getTotalDuenos() {
        return listaDuenos.size();
    }

    private Dueno buscarPorCedula(String cedula) {
        if (cedula == null) return null;
        for (Dueno d : listaDuenos) {
            if (d.getCedula().equals(cedula.trim())) {
                return d;
            }
        }
        return null;
    }
}
