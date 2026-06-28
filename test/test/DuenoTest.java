/*
 * Verificacion-Validacion Software
 * Taller Testing OO - Testing de Métodos de Instancia: Clase Dueño
 */
package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;
import modelo.Dueno;
import modelo.Dueno;

/**
 * Clase de pruebas unitarias para los métodos de instancia de Dueno.
 *
 * Cubre:
 *  - registrarDueno()    → RF1
 *  - consultarDueno()    → RF2
 *  - actualizarDatos()   → RF8 (validación)
 *  - getFechaRegistro() / setFechaRegistro() → encapsulamiento
 *  - mostrarDatos()      → polimorfismo con Persona
 *
 * @author Grupo 12
 * Aguilera Zambrano Juan José
 * Camatón Laínez Segundo Rodolfo
 * Palma Carreño Diego Fernando
 * Zambrano Valverde Luis Abraham
 * Zhinin Muruzumbay Lady Nathaly
 */
public class DuenoTest {

    // ----------------------------------------------------------------
    // Fixture - objeto de prueba reutilizable
    // ----------------------------------------------------------------

    private Dueno dueno;
    private Date fechaBase;

    /**
     * Se ejecuta antes de cada test.
     * Crea un Dueño válido para usar como base en cada prueba.
     */
    @Before
    public void setUp() {
        fechaBase = new Date();
        dueno = new Dueno(
                "0912345678",   // cédula válida (10 dígitos)
                "Juan José",
                "Aguilera Zambrano",
                "Av. 9 de Octubre 123",
                "0991234567",
                "juan@correo.com",
                fechaBase
        );
    }

    // ================================================================
    // TESTS: Constructor y Encapsulamiento
    // ================================================================

    /**
     * TC-01: El constructor debe asignar correctamente la fecha de registro.
     */
    @Test
    public void testConstructor_FechaRegistroAsignadaCorrectamente() {
        assertEquals("La fecha de registro debe ser la proporcionada al constructor.",
                fechaBase, dueno.getFechaRegistro());
    }

    /**
     * TC-02: El constructor con fecha nula debe lanzar IllegalArgumentException.
     * Verifica que el setter valida antes de asignar (encapsulamiento).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_FechaRegistroNulaLanzaExcepcion() {
        new Dueno("0912345678", "Juan", "Aguilera",
                  "Av. 1", "099", "j@c.com", null);
    }

    /**
     * TC-03: setFechaRegistro con valor válido debe actualizar el atributo.
     */
    @Test
    public void testSetFechaRegistro_ValorValido() {
        Date nuevaFecha = new Date(fechaBase.getTime() + 86_400_000L); // +1 día
        dueno.setFechaRegistro(nuevaFecha);
        assertEquals("La fecha debe actualizarse al nuevo valor.", nuevaFecha, dueno.getFechaRegistro());
    }

    /**
     * TC-04: setFechaRegistro con null debe lanzar IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetFechaRegistro_NuloLanzaExcepcion() {
        dueno.setFechaRegistro(null);
    }

    // ================================================================
    // TESTS: registrarDueno() — RF1
    // ================================================================

    /**
     * TC-05: Con todos los datos válidos, registrarDueno() debe retornar true.
     */
    @Test
    public void testRegistrarDueno_DatosValidosRetornaTrue() {
        assertTrue("Un dueño con datos válidos debe registrarse correctamente.",
                dueno.registrarDueno());
    }

    /**
     * TC-06: Con cédula de menos de 10 dígitos, registrarDueno() retorna false.
     */
    @Test
    public void testRegistrarDueno_CedulaCorta_RetornaFalse() {
        Dueno duenoInvalido = new Dueno(
                "091234",          // cédula inválida
                "María", "López",
                "Calle 5", "098", "m@c.com", new Date()
        );
        assertFalse("Una cédula con menos de 10 dígitos debe rechazarse.",
                duenoInvalido.registrarDueno());
    }

    /**
     * TC-07: Con cédula con letras, registrarDueno() retorna false.
     */
    @Test
    public void testRegistrarDueno_CedulaConLetras_RetornaFalse() {
        Dueno duenoInvalido = new Dueno(
                "091ABC5678",      // contiene letras
                "Pedro", "Ramírez",
                "Calle 7", "097", "p@c.com", new Date()
        );
        assertFalse("Una cédula con letras debe rechazarse.", duenoInvalido.registrarDueno());
    }

    /**
     * TC-08: Con correo sin @, registrarDueno() retorna false.
     */
    @Test
    public void testRegistrarDueno_CorreoSinArroba_RetornaFalse() {
        Dueno duenoInvalido = new Dueno(
                "0912345679",
                "Ana", "Torres",
                "Calle 3", "096", "correosinArroba.com", new Date()
        );
        assertFalse("Un correo sin @ debe rechazarse.", duenoInvalido.registrarDueno());
    }

    /**
     * TC-09: Con nombre vacío, registrarDueno() retorna false.
     */
    @Test
    public void testRegistrarDueno_NombreVacio_RetornaFalse() {
        Dueno duenoInvalido = new Dueno(
                "0912345670",
                "",             // nombre vacío
                "González",
                "Calle 8", "095", "g@c.com", new Date()
        );
        assertFalse("Un nombre vacío debe rechazarse.", duenoInvalido.registrarDueno());
    }

    // ================================================================
    // TESTS: consultarDueno() — RF2
    // ================================================================

    /**
     * TC-10: Buscar con la cédula correcta debe retornar la misma instancia.
     */
    @Test
    public void testConsultarDueno_CedulaCorrecta_RetornaInstancia() {
        Dueno resultado = dueno.consultarDueno("0912345678");
        assertNotNull("Debe retornar el dueño cuando la cédula coincide.", resultado);
        assertSame("Debe ser la misma instancia.", dueno, resultado);
    }

    /**
     * TC-11: Buscar con cédula incorrecta debe retornar null.
     */
    @Test
    public void testConsultarDueno_CedulaIncorrecta_RetornaNull() {
        Dueno resultado = dueno.consultarDueno("0000000000");
        assertNull("Debe retornar null cuando la cédula no coincide.", resultado);
    }

    /**
     * TC-12: Buscar con cédula null debe retornar null (sin excepción).
     */
    @Test
    public void testConsultarDueno_CedulaNula_RetornaNull() {
        Dueno resultado = dueno.consultarDueno(null);
        assertNull("Debe retornar null cuando se pasa null como cédula.", resultado);
    }

    /**
     * TC-13: Buscar con cédula vacía debe retornar null.
     */
    @Test
    public void testConsultarDueno_CedulaVacia_RetornaNull() {
        Dueno resultado = dueno.consultarDueno("   ");
        assertNull("Debe retornar null cuando la cédula está vacía.", resultado);
    }

    // ================================================================
    // TESTS: actualizarDatos() — encapsulamiento / RF8
    // ================================================================

    /**
     * TC-14: Actualizar dirección válida debe retornar true y persistir el cambio.
     */
    @Test
    public void testActualizarDatos_DireccionValida_RetornaTrue() {
        boolean resultado = dueno.actualizarDatos("Nueva Av. Principal 456", null, null);
        assertTrue(" Actualizar con dirección válida debe retornar true.", resultado);
        assertEquals("La dirección debe haberse actualizado.",
                "Nueva Av. Principal 456", dueno.getDireccion());
    }

    /**
     * TC-15: Actualizar correo con formato inválido debe retornar false y no cambiar el correo.
     */
    @Test
    public void testActualizarDatos_CorreoInvalido_RetornaFalse() {
        String correoOriginal = dueno.getCorreo();
        boolean resultado = dueno.actualizarDatos(null, null, "correoMalo");
        assertFalse("Un correo inválido debe rechazar la actualización.", resultado);
        assertEquals("El correo no debe haber cambiado.", correoOriginal, dueno.getCorreo());
    }

    /**
     * TC-16: Llamar actualizarDatos() con todos los parámetros null retorna false.
     */
    @Test
    public void testActualizarDatos_TodosNulos_RetornaFalse() {
        boolean resultado = dueno.actualizarDatos(null, null, null);
        assertFalse("Sin datos para actualizar debe retornar false.", resultado);
    }

    /**
     * TC-17: Actualizar teléfono válido debe persistir el cambio.
     */
    @Test
    public void testActualizarDatos_TelefonoValido_Persistido() {
        dueno.actualizarDatos(null, "0987654321", null);
        assertEquals("El teléfono debe haberse actualizado.",
                "0987654321", dueno.getTelefono());
    }

    // ================================================================
    // TESTS: mostrarDatos() — polimorfismo (herencia de Persona)
    // ================================================================

    /**
     * TC-18: mostrarDatos() debe incluir la cédula del dueño.
     */
    @Test
    public void testMostrarDatos_ContieneCedula() {
        String datos = dueno.mostrarDatos();
        assertTrue("mostrarDatos() debe contener la cédula.",
                datos.contains("0912345678"));
    }

    /**
     * TC-19: mostrarDatos() debe incluir la fecha de registro (campo propio de Dueno).
     */
    @Test
    public void testMostrarDatos_ContieneFechaRegistro() {
        String datos = dueno.mostrarDatos();
        assertTrue("mostrarDatos() debe contener 'Fecha Registro'.",
                datos.contains("Fecha Registro"));
    }

    /**
     * TC-20: mostrarDatos() debe incluir el correo (campo heredado de Persona).
     */
    @Test
    public void testMostrarDatos_ContieneCorreo() {
        String datos = dueno.mostrarDatos();
        assertTrue("mostrarDatos() debe contener el correo electrónico.",
                datos.contains("juan@correo.com"));
    }
}
