package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;
import modelo.*;

/**
 * @author GRUPO 12
 *  Aguilera Zambrano Juan José
 * Camatón Laínez Segundo Rodolfo
 * Palma Carreño Diego Fernando
 * Zambrano Valverde Luis Abraham
 * Zhinin Muruzumbay Lady Nathaly
 */
public class MatriculaTest {

    // ----------------------------------------------------------------
    // Fixture - objetos de prueba reutilizables
    // ----------------------------------------------------------------

    private Dueno dueno;
    private Vehiculo vehiculo;
    private Matricula matricula;
    private Date fechaBase;

    /**
     * Se ejecuta antes de cada test.
     * Crea un dueño, un vehículo y una matrícula válidos.
     */
    @Before
    public void setUp() {
        fechaBase = new Date();

        dueno = new Dueno(
                "0912345678",
                "Lady",
                "Zhinin",
                "Guayaquil",
                "0998765432",
                "lady@gmail.com",
                fechaBase
        );

        vehiculo = new Vehiculo(
                "GSA1234",
                "Chevrolet",
                "Aveo",
                2022,
                "Rojo",
                "Automovil",
                dueno
        );

        matricula = new Matricula(
                "MAT001",
                fechaBase,
                2026,
                "PENDIENTE",
                vehiculo
        );
    }

    // ================================================================
    // TESTS: Constructor
    // ================================================================

    /**
     * TC-01: El constructor debe crear una matrícula válida.
     */
    @Test
    public void testConstructor_MatriculaCreadaCorrectamente() {
        assertNotNull("La matrícula no debe ser null.", matricula);
        assertEquals("El número de matrícula debe coincidir.",
                "MAT001", matricula.getNumeroMatricula());
        assertEquals("La fecha de emisión debe coincidir.",
                fechaBase, matricula.getFechaEmision());
        assertEquals("El año fiscal debe coincidir.",
                2026, matricula.getAnioFiscal());
        assertEquals("El estado inicial debe ser PENDIENTE.",
                "PENDIENTE", matricula.getEstado());
        assertSame("El vehículo asociado debe ser el mismo.",
                vehiculo, matricula.getVehiculo());
    }

    // ================================================================
    // TESTS: registrarMatricula() — RF5
    // ================================================================

    /**
     * TC-02: Con todos los datos válidos, registrarMatricula() debe retornar true.
     */
    @Test
    public void testRegistrarMatricula_DatosValidosRetornaTrue() {
        assertTrue("Una matrícula con datos válidos debe registrarse correctamente.",
                matricula.registrarMatricula());
    }

    /**
     * TC-03: Con número de matrícula vacío, registrarMatricula() debe retornar false.
     */
    @Test
    public void testRegistrarMatricula_NumeroVacioRetornaFalse() {
        Matricula matriculaInvalida = new Matricula(
                "",
                fechaBase,
                2026,
                "PENDIENTE",
                vehiculo
        );

        assertFalse("Una matrícula con número vacío debe rechazarse.",
                matriculaInvalida.registrarMatricula());
    }

    /**
     * TC-04: Con fecha de emisión nula, registrarMatricula() debe retornar false.
     */
    @Test
    public void testRegistrarMatricula_FechaNulaRetornaFalse() {
        Matricula matriculaInvalida = new Matricula(
                "MAT002",
                null,
                2026,
                "PENDIENTE",
                vehiculo
        );

        assertFalse("Una matrícula con fecha nula debe rechazarse.",
                matriculaInvalida.registrarMatricula());
    }

    /**
     * TC-05: Con año fiscal inválido, registrarMatricula() debe retornar false.
     */
    @Test
    public void testRegistrarMatricula_AnioFiscalInvalidoRetornaFalse() {
        Matricula matriculaInvalida = new Matricula(
                "MAT003",
                fechaBase,
                0,
                "PENDIENTE",
                vehiculo
        );

        assertFalse("Una matrícula con año fiscal inválido debe rechazarse.",
                matriculaInvalida.registrarMatricula());
    }

    /**
     * TC-06: Sin vehículo asociado, registrarMatricula() debe retornar false.
     */
    @Test
    public void testRegistrarMatricula_VehiculoNuloRetornaFalse() {
        Matricula matriculaInvalida = new Matricula(
                "MAT004",
                fechaBase,
                2026,
                "PENDIENTE",
                null
        );

        assertFalse("Una matrícula sin vehículo asociado debe rechazarse.",
                matriculaInvalida.registrarMatricula());
    }

    // ================================================================
    // TESTS: consultarMatricula() — RF6
    // ================================================================

    /**
     * TC-07: Consultar con número correcto debe retornar la misma instancia.
     */
    @Test
    public void testConsultarMatricula_NumeroCorrectoRetornaInstancia() {
        Matricula resultado = matricula.consultarMatricula("MAT001");

        assertNotNull("Debe retornar la matrícula cuando el número coincide.", resultado);
        assertSame("Debe ser la misma instancia.", matricula, resultado);
    }

    /**
     * TC-08: Consultar con número incorrecto debe retornar null.
     */
    @Test
    public void testConsultarMatricula_NumeroIncorrectoRetornaNull() {
        Matricula resultado = matricula.consultarMatricula("MAT999");

        assertNull("Debe retornar null cuando el número no coincide.", resultado);
    }

    /**
     * TC-09: Consultar con número null debe retornar null sin lanzar excepción.
     */
    @Test
    public void testConsultarMatricula_NumeroNuloRetornaNull() {
        Matricula resultado = matricula.consultarMatricula(null);

        assertNull("Debe retornar null cuando se pasa null como número.", resultado);
    }

    /**
     * TC-10: Consultar con número vacío debe retornar null.
     */
    @Test
    public void testConsultarMatricula_NumeroVacioRetornaNull() {
        Matricula resultado = matricula.consultarMatricula("   ");

        assertNull("Debe retornar null cuando el número está vacío.", resultado);
    }

    // ================================================================
    // TESTS: generarMatricula()
    // ================================================================

    /**
     * TC-11: generarMatricula() debe cambiar el estado a GENERADA.
     */
    @Test
    public void testGenerarMatricula_CambiaEstadoAGenerada() {
        matricula.generarMatricula();

        assertEquals("El estado debe cambiar a GENERADA.",
                "GENERADA", matricula.getEstado());
    }

    // ================================================================
    // TESTS: cambiarEstado()
    // ================================================================

    /**
     * TC-12: cambiarEstado() debe actualizar el estado a PAGADA.
     */
    @Test
    public void testCambiarEstado_EstadoPagada() {
        matricula.cambiarEstado("PAGADA");

        assertEquals("El estado debe actualizarse a PAGADA.",
                "PAGADA", matricula.getEstado());
    }

    /**
     * TC-13: cambiarEstado() con valor vacío no debe modificar el estado anterior.
     */
    @Test
    public void testCambiarEstado_EstadoVacioNoModifica() {
        matricula.cambiarEstado("GENERADA");
        matricula.cambiarEstado("");

        assertEquals("El estado no debe cambiar si se envía vacío.",
                "GENERADA", matricula.getEstado());
    }

    // ================================================================
    // TESTS: mostrarDatos()
    // ================================================================

    /**
     * TC-14: mostrarDatos() debe contener el número de matrícula.
     */
    @Test
    public void testMostrarDatos_ContieneNumeroMatricula() {
        String datos = matricula.mostrarDatos();

        assertTrue("mostrarDatos() debe contener el número de matrícula.",
                datos.contains("MAT001"));
    }

    /**
     * TC-15: mostrarDatos() debe contener el estado de la matrícula.
     */
    @Test
    public void testMostrarDatos_ContieneEstado() {
        String datos = matricula.mostrarDatos();

        assertTrue("mostrarDatos() debe contener el estado.",
                datos.contains("PENDIENTE"));
    }

    /**
     * TC-16: mostrarDatos() debe contener la placa del vehículo asociado.
     */
    @Test
    public void testMostrarDatos_ContienePlacaVehiculo() {
        String datos = matricula.mostrarDatos();

        assertTrue("mostrarDatos() debe contener la placa del vehículo.",
                datos.contains("GSA1234"));
    }
}
