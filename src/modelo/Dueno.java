/*
 * Verificacion-Validacion Software
 * Taller Testing OO - Módulo: Clase Dueño y Encapsulamiento
 */
package modelo;

import java.util.Date;

/**
 * Clase Dueno - Hereda de Persona
 * Aplica encapsulamiento mediante atributos privados y validaciones en setters.
 *
 * Pilares POO aplicados:
 *  - Herencia: extiende de Persona
 *  - Encapsulamiento: atributos privados + getters/setters con validación
 *  - Polimorfismo: sobreescribe mostrarDatos()
 *
 * @author Grupo 12
 * Aguilera Zambrano Juan José
 * Camatón Laínez Segundo Rodolfo
 * Palma Carreño Diego Fernando
 * Zambrano Valverde Luis Abraham
 * Zhinin Muruzumbay Lady Nathaly
 */
public class Dueno extends Persona {

    // Atributo privado - encapsulamiento
    private Date fechaRegistro;

    // ----------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------

    /**
     * Crea un nuevo Dueño validando todos los campos obligatorios.
     *
     * @param cedula         Número de cédula (10 dígitos numéricos)
     * @param nombres        Nombres del propietario (no vacío)
     * @param apellidos      Apellidos del propietario (no vacío)
     * @param direccion      Dirección domiciliaria (no vacío)
     * @param telefono       Teléfono de contacto (no vacío)
     * @param correo         Correo electrónico válido (contiene @)
     * @param fechaRegistro  Fecha en que se registra el dueño (no nula)
     * @throws IllegalArgumentException si algún campo no pasa la validación
     */
    public Dueno(String cedula, String nombres, String apellidos,
                 String direccion, String telefono,
                 String correo, Date fechaRegistro) {
        super(cedula, nombres, apellidos, direccion, telefono, correo);
        // Reutiliza el setter para validar también en el constructor
        setFechaRegistro(fechaRegistro);
    }

    // ----------------------------------------------------------------
    // Getter y Setter con encapsulamiento
    // ----------------------------------------------------------------

    /** @return fecha en que el dueño fue registrado en el sistema */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Establece la fecha de registro. No puede ser nula.
     *
     * @param fechaRegistro fecha de registro
     * @throws IllegalArgumentException si la fecha es nula
     */
    public void setFechaRegistro(Date fechaRegistro) {
        if (fechaRegistro == null) {
            throw new IllegalArgumentException("La fecha de registro no puede ser nula.");
        }
        this.fechaRegistro = fechaRegistro;
    }

    // ----------------------------------------------------------------
    // Métodos de instancia (lógica de negocio)
    // ----------------------------------------------------------------

    /**
     * RF1 - Registrar dueño.
     * Valida que los campos heredados de Persona no estén vacíos y que
     * la cédula tenga exactamente 10 dígitos numéricos antes de confirmar
     * el registro.
     *
     * @return true si el registro es válido, false en caso contrario
     */
    public boolean registrarDueno() {
        if (!validarCedula(getCedula())) {
            System.out.println("Error: la cédula debe tener 10 dígitos numéricos.");
            return false;
        }
        if (getNombres() == null || getNombres().trim().isEmpty()) {
            System.out.println("Error: el nombre no puede estar vacío.");
            return false;
        }
        if (getApellidos() == null || getApellidos().trim().isEmpty()) {
            System.out.println("Error: los apellidos no pueden estar vacíos.");
            return false;
        }
        if (!validarCorreo(getCorreo())) {
            System.out.println("Error: el correo electrónico no es válido.");
            return false;
        }
        System.out.println("Dueño registrado correctamente.");
        return true;
    }

    /**
     * RF2 - Consultar dueño por cédula.
     * Compara la cédula proporcionada con la del objeto actual.
     *
     * @param cedula número de cédula a buscar
     * @return la instancia actual si coincide, null si no coincide
     */
    public Dueno consultarDueno(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            return null;
        }
        if (getCedula().equals(cedula.trim())) {
            return this;
        }
        return null;
    }

    /**
     * Actualiza los datos modificables del dueño.
     * Los campos nulos o vacíos son ignorados (se conserva el valor anterior).
     *
     * @param nuevaDireccion nueva dirección (puede ser null para no cambiar)
     * @param nuevoTelefono  nuevo teléfono  (puede ser null para no cambiar)
     * @param nuevoCorreo    nuevo correo    (puede ser null para no cambiar)
     * @return true si al menos un campo fue actualizado
     */
    public boolean actualizarDatos(String nuevaDireccion,
                                   String nuevoTelefono,
                                   String nuevoCorreo) {
        boolean actualizado = false;

        if (nuevaDireccion != null && !nuevaDireccion.trim().isEmpty()) {
            setDireccion(nuevaDireccion.trim());
            actualizado = true;
        }
        if (nuevoTelefono != null && !nuevoTelefono.trim().isEmpty()) {
            setTelefono(nuevoTelefono.trim());
            actualizado = true;
        }
        if (nuevoCorreo != null && !nuevoCorreo.trim().isEmpty()) {
            if (!validarCorreo(nuevoCorreo)) {
                System.out.println("Error: el nuevo correo no es válido.");
                return false;
            }
            setCorreo(nuevoCorreo.trim());
            actualizado = true;
        }

        if (actualizado) {
            System.out.println("Datos actualizados correctamente.");
        } else {
            System.out.println("No se proporcionaron datos para actualizar.");
        }
        return actualizado;
    }

    // ----------------------------------------------------------------
    // Métodos de validación privados (encapsulamiento de reglas)
    // ----------------------------------------------------------------

    /**
     * Valida que la cédula tenga exactamente 10 dígitos numéricos.
     *
     * @param cedula cédula a validar
     * @return true si es válida
     */
    private boolean validarCedula(String cedula) {
        if (cedula == null) return false;
        return cedula.matches("\\d{10}");
    }

    /**
     * Valida que el correo tenga formato básico (contiene @ y al menos un punto después).
     *
     * @param correo correo a validar
     * @return true si es válido
     */
    private boolean validarCorreo(String correo) {
        if (correo == null) return false;
        return correo.contains("@") && correo.contains(".");
    }

    // ----------------------------------------------------------------
    // Sobreescritura de mostrarDatos() - Polimorfismo
    // ----------------------------------------------------------------

    /**
     * Retorna la información completa del dueño, incluyendo
     * los datos heredados de Persona más la fecha de registro.
     *
     * @return cadena formateada con todos los datos
     */
    @Override
    public String mostrarDatos() {
        return super.mostrarDatos()
                + "\nFecha Registro: " + fechaRegistro;
    }
}
