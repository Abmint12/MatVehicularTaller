/*
 * Verificacion-Validacion Software
 * Taller Testing OO - Controlador: Matricula
 */
package controlador;

import modelo.Matricula;
import modelo.Vehiculo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ControladorMatricula
 * Intermediario entre la Vista y el Modelo (patron MVC).
 * Gestiona la lista de matriculas registradas en memoria y
 * expone operaciones de registro, consulta y actualizacion de estado.
 *
 * @author Grupo 12
 */
public class ControladorMatricula {

    // Lista en memoria que actua como repositorio de matriculas
    private List<Matricula> listaMatriculas;

    public ControladorMatricula() {
        this.listaMatriculas = new ArrayList<>();
    }

    // ------------------------------------------------------------------
    // RF5 - Registrar matricula
    // ------------------------------------------------------------------

    /**
     * Registra una nueva matricula si el numero no esta duplicado
     * y los datos son validos.
     *
     * @return mensaje de resultado para mostrar en la vista
     */
    public String registrarMatricula(String numeroMatricula, int anioFiscal,
                                     String estado, Vehiculo vehiculo) {

        if (buscarPorNumero(numeroMatricula) != null) {
            return "ERROR: Ya existe una matricula registrada con el numero "
                    + numeroMatricula + ".";
        }

        Matricula nueva = new Matricula(numeroMatricula, new Date(),
                anioFiscal, estado, vehiculo);

        boolean ok = nueva.registrarMatricula();

        if (ok) {
            nueva.generarMatricula();
            listaMatriculas.add(nueva);
            return "Matricula registrada correctamente.";
        }

        return "ERROR: Los datos ingresados no son validos. Verifique numero, año fiscal y vehiculo.";
    }

    // ------------------------------------------------------------------
    // RF6 - Consultar matricula
    // ------------------------------------------------------------------

    /**
     * Busca y retorna una matricula por numero.
     *
     * @param numeroMatricula numero de matricula a buscar
     * @return la Matricula encontrada o null
     */
    public Matricula consultarMatricula(String numeroMatricula) {
        return buscarPorNumero(numeroMatricula);
    }

    /**
     * Retorna los datos formateados de una matricula para mostrar en la vista.
     */
    public String getDatosMatricula(String numeroMatricula) {
        Matricula m = buscarPorNumero(numeroMatricula);

        if (m == null) {
            return "ERROR: No se encontro ninguna matricula con el numero "
                    + numeroMatricula + ".";
        }

        return m.mostrarDatos();
    }

    // ------------------------------------------------------------------
    // Actualizar estado
    // ------------------------------------------------------------------

    /**
     * Actualiza el estado de una matricula existente.
     */
    public String actualizarEstadoMatricula(String numeroMatricula, String nuevoEstado) {
        Matricula m = buscarPorNumero(numeroMatricula);

        if (m == null) {
            return "ERROR: No se encontro matricula con numero " + numeroMatricula + ".";
        }

        if (nuevoEstado == null || nuevoEstado.trim().isEmpty()) {
            return "ERROR: El nuevo estado no puede estar vacio.";
        }

        m.cambiarEstado(nuevoEstado);
        return "Estado de matricula actualizado correctamente.";
    }

    // ------------------------------------------------------------------
    // Auxiliares
    // ------------------------------------------------------------------

    public List<Matricula> getListaMatriculas() {
        return listaMatriculas;
    }

    public int getTotalMatriculas() {
        return listaMatriculas.size();
    }

    private Matricula buscarPorNumero(String numeroMatricula) {
        if (numeroMatricula == null) {
            return null;
        }

        for (Matricula m : listaMatriculas) {
            if (m.getNumeroMatricula().equals(numeroMatricula.trim())) {
                return m;
            }
        }

        return null;
    }
}