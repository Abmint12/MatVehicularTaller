/*
 * Verificacion-Validacion Software
 * Taller Testing OO - Testing de Controladores: ControladorMatricula
 */
package test;

import controlador.ControladorMatricula;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import modelo.Dueno;
import modelo.Vehiculo;
import modelo.Matricula;
import java.util.Date;

/**
 * Pruebas unitarias para ControladorMatricula.
 * Cubre RF5 (registro), RF6 (consulta) y RF8 (validacion de duplicados).
 *
 * @author Grupo 12
 */
public class ControladorMatriculaTest {

    private ControladorMatricula ctrl;
    private Dueno dueno;
    private Vehiculo vehiculo;

    @Before
    public void setUp() {
        ctrl = new ControladorMatricula();

        dueno = new Dueno("0912345678", "Lady", "Zhinin",
                "Guayaquil", "0998765432", "lady@gmail.com", new Date());

        vehiculo = new Vehiculo("GSA1234", "Chevrolet", "Aveo",
                2022, "Rojo", "Automovil", dueno);

        // Pre-registrar una matricula de base para pruebas de duplicado y consulta
        ctrl.registrarMatricula("MAT001", 2026, "PENDIENTE", vehiculo);
    }

    // RF5 - registro exitoso
    @Test
    public void testRegistrar_DatosValidos_MensajeExito() {
        String res = ctrl.registrarMatricula("MAT002", 2026, "PENDIENTE", vehiculo);

        assertFalse("No debe ser mensaje de error.", res.startsWith("ERROR"));
        assertEquals(2, ctrl.getTotalMatriculas());
    }

    // RF8 - numero de matricula duplicado
    @Test
    public void testRegistrar_NumeroDuplicado_MensajeError() {
        String res = ctrl.registrarMatricula("MAT001", 2026, "PENDIENTE", vehiculo);

        assertTrue("Debe retornar ERROR por matricula duplicada.", res.startsWith("ERROR"));
        assertEquals("No debe agregarse el duplicado.", 1, ctrl.getTotalMatriculas());
    }

    // RF5 - año fiscal invalido
    @Test
    public void testRegistrar_AnioFiscalInvalido_MensajeError() {
        String res = ctrl.registrarMatricula("MAT003", 0, "PENDIENTE", vehiculo);

        assertTrue("Año fiscal invalido debe retornar ERROR.", res.startsWith("ERROR"));
    }

    // RF5 - vehiculo nulo
    @Test
    public void testRegistrar_VehiculoNulo_MensajeError() {
        String res = ctrl.registrarMatricula("MAT004", 2026, "PENDIENTE", null);

        assertTrue("Vehiculo nulo debe retornar ERROR.", res.startsWith("ERROR"));
    }

    // RF6 - consulta existente
    @Test
    public void testConsultar_NumeroExistente_RetornaMatricula() {
        Matricula m = ctrl.consultarMatricula("MAT001");

        assertNotNull("Debe encontrar la matricula registrada.", m);
        assertEquals("MAT001", m.getNumeroMatricula());
    }

    // RF6 - consulta no existente
    @Test
    public void testConsultar_NumeroNoExistente_RetornaNull() {
        Matricula m = ctrl.consultarMatricula("MAT999");

        assertNull("Debe retornar null para matricula no registrada.", m);
    }

    // getDatosMatricula - formato correcto
    @Test
    public void testGetDatos_NumeroExistente_ContieneNumero() {
        String datos = ctrl.getDatosMatricula("MAT001");

        assertTrue("Los datos deben contener el numero de matricula.",
                datos.contains("MAT001"));
    }

    // getDatosMatricula - no encontrado
    @Test
    public void testGetDatos_NumeroNoExistente_MensajeError() {
        String datos = ctrl.getDatosMatricula("MAT999");

        assertTrue("Debe retornar ERROR si no existe.", datos.startsWith("ERROR"));
    }

    // actualizarEstadoMatricula - exitoso
    @Test
    public void testActualizarEstado_EstadoValido_Actualizado() {
        String res = ctrl.actualizarEstadoMatricula("MAT001", "PAGADA");

        assertFalse("Debe ser exitoso.", res.startsWith("ERROR"));

        Matricula m = ctrl.consultarMatricula("MAT001");
        assertEquals("PAGADA", m.getEstado());
    }

    // actualizarEstadoMatricula - matricula no existe
    @Test
    public void testActualizarEstado_NumeroNoExistente_Error() {
        String res = ctrl.actualizarEstadoMatricula("MAT999", "PAGADA");

        assertTrue("Debe retornar ERROR.", res.startsWith("ERROR"));
    }

    // actualizarEstadoMatricula - estado vacio
    @Test
    public void testActualizarEstado_EstadoVacio_Error() {
        String res = ctrl.actualizarEstadoMatricula("MAT001", "");

        assertTrue("Estado vacio debe retornar ERROR.", res.startsWith("ERROR"));
    }
}