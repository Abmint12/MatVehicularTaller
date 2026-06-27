/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Grupo 12
 */
import java.util.Date;
import java.util.Scanner;
import modelo.Dueno;
import modelo.Matricula;
import modelo.Pago;
import modelo.Vehiculo;
public class U3S12TallerTestingOO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        
        Dueno dueno=null;
        Vehiculo vehiculo= null;
        Matricula matricula=null;
        Pago pago =null;
        
        int opcion;
        
        do {

            System.out.println("\n==============================");
            System.out.println(" MATRICULACION VEHICULAR");
            System.out.println("==============================");
            System.out.println("1. Registrar Dueño");
            System.out.println("2. Consultar Dueño");
            System.out.println("3. Registrar Vehículo");
            System.out.println("4. Consultar Vehículo");
            System.out.println("5. Registrar Matrícula");
            System.out.println("6. Consultar Matrícula");
            System.out.println("7. Realizar Pago");
            System.out.println("8. Consultar Pago");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:

                    System.out.println("\n=== REGISTRO DE DUEÑO ===");

                    System.out.print("Cedula: ");
                    String cedula = sc.nextLine();

                    System.out.print("Nombres: ");
                    String nombres = sc.nextLine();

                    System.out.print("Apellidos: ");
                    String apellidos = sc.nextLine();

                    System.out.print("Direccion: ");
                    String direccion = sc.nextLine();

                    System.out.print("Telefono: ");
                    String telefono = sc.nextLine();

                    System.out.print("Correo: ");
                    String correo = sc.nextLine();

                    dueno = new Dueno(
                            cedula,
                            nombres,
                            apellidos,
                            direccion,
                            telefono,
                            correo,
                            new Date());

                    dueno.registrarDueno();

                    break;

                case 2:

                    if (dueno != null)
                        System.out.println("\n" + dueno.mostrarDatos());
                    else
                        System.out.println("No existe un dueño registrado.");

                    break;

                case 3:

                    if (dueno == null) {

                        System.out.println("Primero registre un dueño.");
                        break;

                    }

                    System.out.println("\n=== REGISTRO VEHICULO ===");

                    System.out.print("Placa: ");
                    String placa = sc.nextLine();

                    System.out.print("Marca: ");
                    String marca = sc.nextLine();

                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();

                    System.out.print("Año: ");
                    int anio = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Color: ");
                    String color = sc.nextLine();

                    System.out.print("Tipo: ");
                    String tipo = sc.nextLine();

                    vehiculo = new Vehiculo(
                            placa,
                            marca,
                            modelo,
                            anio,
                            color,
                            tipo,
                            dueno);

                    vehiculo.registrarVehiculo();

                    break;

                case 4:

                    if (vehiculo != null)
                        System.out.println("\n" + vehiculo.mostrarDatos());
                    else
                        System.out.println("No existe vehículo registrado.");

                    break;

                case 5:

                    if (vehiculo == null) {

                        System.out.println("Primero registre un vehículo.");
                        break;

                    }

                    System.out.println("\n=== MATRICULA ===");

                    System.out.print("Numero matricula: ");
                    String numero = sc.nextLine();

                    System.out.print("Año fiscal: ");
                    int fiscal = sc.nextInt();
                    sc.nextLine();

                    matricula = new Matricula(
                            numero,
                            new Date(),
                            fiscal,
                            "PENDIENTE",
                            vehiculo);

                    matricula.generarMatricula();

                    System.out.println("Matricula registrada.");

                    break;

                case 6:

                    if (matricula != null)
                        System.out.println("\n" + matricula.mostrarDatos());
                    else
                        System.out.println("No existe matrícula registrada.");

                    break;

                case 7:

                    if (matricula == null) {

                        System.out.println("Primero registre la matrícula.");
                        break;

                    }

                    System.out.println("\n=== PAGO ===");

                    System.out.print("Codigo pago: ");
                    String codigo = sc.nextLine();

                    System.out.print("Valor: ");
                    double valor = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Metodo de pago: ");
                    String metodo = sc.nextLine();

                    pago = new Pago(
                            codigo,
                            new Date(),
                            valor,
                            metodo,
                            "PENDIENTE");

                    pago.realizarPago();

                    break;

                case 8:

                    if (pago != null)
                        System.out.println("\n" + pago.mostrarDatos());
                    else
                        System.out.println("No existe pago registrado.");

                    break;

                case 9:

                    System.out.println("Gracias por utilizar el sistema.");

                    break;

                default:

                    System.out.println("Opción inválida.");

            }

        } while (opcion != 9);

        sc.close();

    }
}
