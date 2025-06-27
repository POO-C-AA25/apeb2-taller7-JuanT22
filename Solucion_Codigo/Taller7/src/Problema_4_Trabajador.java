/**
 * Problema 4 - Sistema de nómina para trabajadores
Se desea desarrollar un sistema de nómina para los trabajadores de una empresa. 
* Los datos personales de los trabajadores son nombre y apellidos, dirección y 
* DNI. Además, existen diferentes tipos de trabajadores:

Fijos Mensuales: que cobran una cantidad fija al mes.
Comisionistas: cobran un porcentaje fijo por las ventas que han realizado
Por Horas: cobran un precio por cada una de las horas que han realizado durante 
* el mes. El precio es fijo para las primeras 40 horas y es otro para las horas 
* realizadas a partir de la 40 hora mensual.
Jefe: cobra un sueldo fijo (no hay que calcularlo). Cada empleado tiene 
* obligatoriamente un jefe (exceptuando los jefes que no tienen ninguno). 
* El programa debe permitir dar de alta a trabajadores, así como fijar horas o
* ventas realizadas e imprimir la nómina correspondiente al final de mes.
Note

Diseñe la jerarquia de clases UML basado en herencia, que defina de mejor forma
* el escenario planteado.
Para probar el diseño de clases, instancia en el clase de prueba Ejecutor 
* (la-s clase-s respectiva-s), con datos aleatorios.
En los escenarios de prueba verifique su solución con al menos 2 tipos de trabajadores.
 * @author juani
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Problema_4_Trabajador {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("NOMINA");
        System.out.println("=".repeat("NOMINA".length()));
        Jefe jefe1 = new Jefe("1900831320", "JUAN", "TACURI", "YANTZAZA", null, 3000.0);
        Jefe jefe2 = new Jefe("1900499441", "PITUFA", "CRUZ", "MIAMI", null, 2000.1);
        FijosMensuales trabajador1 = new FijosMensuales("1900750173", "PITUFO", "AZUL", "ColiseoLoja", jefe1, 500);
        Comisionista trabajador2 = new Comisionista("1900831312", "LUIS", "PEREZ", "LOJA", jefe2, 0.10, 1000);
        Hora trabajador3 = new Hora("11502569", "CARLOS", "HUMBERTO", "LasPitas", jefe1, 0, 15, 45);
        Nomina nomina = new Nomina();
        nomina.jefes.add(jefe1);
        nomina.jefes.add(jefe2);
        nomina.fijosMensuales.add(trabajador1);
        nomina.comisionistas.add(trabajador2);
        nomina.horas.add(trabajador3);
        nomina.imprimirNominaCompleta();
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar Jefe");
            System.out.println("2. Agregar Trabajador Fijo Mensual");
            System.out.println("3. Agregar Comisionista");
            System.out.println("4. Agregar Trabajador por Horas");
            System.out.println("5. Imprimir Nómina Completa");
            System.out.println("6. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    System.out.print("DNI: ");
                    String dniJefe = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombreJefe = scanner.nextLine();
                    System.out.print("Apellidos: ");
                    String apellidosJefe = scanner.nextLine();
                    System.out.print("Dirección: ");
                    String direccionJefe = scanner.nextLine();
                    System.out.print("Sueldo: ");
                    double sueldoJefe = scanner.nextDouble();
                    Jefe jefe = new Jefe(dniJefe, nombreJefe, apellidosJefe, direccionJefe, null, sueldoJefe);
                    nomina.agregarJefe(jefe);
                    break;
                case 2:
                    System.out.print("DNI: ");
                    String dniFijo = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombreFijo = scanner.nextLine();
                    System.out.print("Apellidos: ");
                    String apellidosFijo = scanner.nextLine();
                    System.out.print("Dirección: ");
                    String direccionFijo = scanner.nextLine();
                    System.out.print("DNI del Jefe: ");
                    String dniJefeFijo = scanner.nextLine();
                    System.out.print("Sueldo: ");
                    double sueldoFijo = scanner.nextDouble();
                    scanner.nextLine();
                    Trabajador jefeFijo = null;
                    for (Jefe j : nomina.jefes) {
                        if (j.dni.equals(dniJefeFijo)) {
                            jefeFijo = j;
                            break;
                        }
                    }
                    FijosMensuales trabajadorFijo = new FijosMensuales(dniFijo, nombreFijo,
                            apellidosFijo, direccionFijo,
                            jefeFijo, sueldoFijo);
                    nomina.agregarFijoMensual(trabajadorFijo);
                    break;
                case 3:
                    System.out.print("DNI: ");
                    String dniComisionista = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombreComisionista = scanner.nextLine();
                    System.out.print("Apellidos: ");
                    String apellidosComisionista = scanner.nextLine();
                    System.out.print("Dirección: ");
                    String direccionComisionista = scanner.nextLine();
                    System.out.print("DNI del Jefe: ");
                    String dniJefeComisionista = scanner.nextLine();
                    System.out.print("Porcentaje: ");
                    double porcentaje = scanner.nextDouble();
                    System.out.print("Ventas: ");
                    int ventas = scanner.nextInt();
                    Trabajador jefeComisionista = null;
                    for (Jefe j : nomina.jefes) {
                        if (j.dni.equals(dniJefeComisionista)) {
                            jefeComisionista = j;
                            break;
                        }
                    }
                    Comisionista trabajadorComisionista = new Comisionista(dniComisionista, nombreComisionista,
                            apellidosComisionista, direccionComisionista,
                            jefeComisionista, porcentaje, ventas);
                    nomina.agregarComisionista(trabajadorComisionista);

                    break;
                case 4:
                    System.out.print("DNI: ");
                    String dniHoras = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombreHoras = scanner.nextLine();
                    System.out.print("Apellidos: ");
                    String apellidosHoras = scanner.nextLine();
                    System.out.print("Dirección: ");
                    String direccionHoras = scanner.nextLine();
                    System.out.print("DNI del Jefe: ");
                    String dniJefeHoras = scanner.nextLine();
                    System.out.print("Pago por Hora: ");
                    double pagoHora = scanner.nextDouble();
                    System.out.print("Horas Trabajadas: ");
                    int horasTrabajadas = scanner.nextInt();
                    Trabajador jefeHoras = null;
                    for (Jefe j : nomina.jefes) {
                        if (j.dni.equals(dniJefeHoras)) {
                            jefeHoras = j;
                            break;
                        }
                    }
                    Hora trabajadorHoras = new Hora(dniHoras, nombreHoras, apellidosHoras, direccionHoras, jefeHoras,
                            0, pagoHora, horasTrabajadas);
                    nomina.agregarHoras(trabajadorHoras);
                    break;
                case 5:
                    nomina.imprimirNominaCompleta();
                    break;
                case 6:
                    System.out.println("Saliendo del programa, hasta luego");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}

class Trabajador {
    public String dni;
    public String nombre;
    public String apellidos;
    public String direccion;
    public Trabajador jefe;

    public Trabajador() {
    }

    public Trabajador(String dni, String nombre, String apellidos, String direccion, Trabajador jefe) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.jefe = jefe;
    }

    public double calcularSueldo() {
        return 0;
    }
}

class Jefe extends Trabajador {
    public double sueldo;

    public Jefe(String dni, String nombre, String apellidos, String direccion, Trabajador jefe, double sueldo) {
        super(dni, nombre, apellidos, direccion, jefe);
        this.sueldo = sueldo;
    }

    @Override
    public double calcularSueldo() {
        return this.sueldo;
    }

    @Override
    public String toString() {
        return String.format("Jefe{nombre = %15s, apellidos = %20s, jefe = %10s}", jefe.nombre, jefe.apellidos, null);
    }
}

class FijosMensuales extends Trabajador {
    public double sueldo;

    public FijosMensuales(String dni, String nombre, String apellidos, String direccion, Trabajador jefe,
            double sueldo) {
        super(dni, nombre, apellidos, direccion, jefe);
        this.sueldo = sueldo;
    }

    @Override
    public double calcularSueldo() {
        return sueldo;
    }

    public String toString() {
        return String.format(
                "FijosMensuales{nombre = %15s, apellidos = %15s, direccion = %15s, jefe = %15s, sueldo = %5.2f", nombre,
                apellidos, direccion, jefe, sueldo);
    }
}

class Comisionista extends Trabajador {
    public double porcentaje;
    public int venta;

    public Comisionista(String dni, String nombre, String apellidos, String direccion, Trabajador jefe,
            double porcentaje, int venta) {
        super(dni, nombre, apellidos, direccion, jefe);
        this.porcentaje = porcentaje;
        this.venta = venta;
    }

    public double calcularSueldo() {
        return this.venta * this.porcentaje;
    }

    public String toString() {
        return String.format(
                "Comisionista{nombre = %15s, apellidos = %15s, direccion = %15s, jefe = %15s, porcentaje = %3.2f, ventas = %5d, pago = %5.2f}",
                nombre, apellidos, direccion, jefe, porcentaje, venta, calcularSueldo());
    }
}

class Hora extends Trabajador {
    public double sueldo;
    public double pagoHora;
    public int horas;

    public Hora(String dni, String nombre, String apellidos, String direccion, Trabajador jefe, double sueldo,
            double pagoHora, int horas) {
        super(dni, nombre, apellidos, direccion, jefe);
        this.horas = horas;
        this.pagoHora = pagoHora;
    }

    public double calcularSueldo() {
        if (horas > 40) {
            return (horas * pagoHora) * 1.05;
        }
        return horas * pagoHora;
    }
}

class Nomina {
    public ArrayList<Jefe> jefes;
    public ArrayList<FijosMensuales> fijosMensuales;
    public ArrayList<Comisionista> comisionistas;
    public ArrayList<Hora> horas;

    public Nomina() {
        this.jefes = new ArrayList<>();
        this.fijosMensuales = new ArrayList<>();
        this.comisionistas = new ArrayList<>();
        this.horas = new ArrayList<>();
    }

    public void agregarJefe(Jefe jefe) {
        jefes.add(jefe);
    }

    public void agregarFijoMensual(FijosMensuales trabajador) {
        fijosMensuales.add(trabajador);
    }

    public void agregarComisionista(Comisionista comisionista) {
        comisionistas.add(comisionista);
    }

    public void agregarHoras(Hora trabajador) {
        horas.add(trabajador);
    }

    public void imprimirNominaCompleta() {
        System.out.println("NOMINA DE JEFES");
        System.out.println("=".repeat("NOMINA DE JEFES".length()));
        for (Jefe j : this.jefes) {
            System.out.printf("---- Nómina de: %s %s ----\n", j.nombre, j.apellidos);
            System.out.printf(" > DNI: %s\n", j.dni);
            System.out.printf(" > Dirección: %s\n", j.direccion);
            System.out.printf(" > Tipo de Contrato: Jefe\n");
            System.out.println(" > Jefe: N/A");
            System.out.printf(" > SUELDO A PAGAR: $ %.2f\n\n", j.calcularSueldo());
        }
        System.out.println("NOMINA DE EMPLEADOS FIJOS");
        System.out.println("=".repeat("NOMINA DE EMPLEADOS FIJOS".length()));
        for (FijosMensuales f : this.fijosMensuales) {
            System.out.printf("---- Nómina de: %s %s ----\n", f.nombre, f.apellidos);
            System.out.printf(" > DNI: %s\n", f.dni);
            System.out.printf(" > Dirección: %s\n", f.direccion);
            System.out.printf(" > Tipo de Contrato: Fijo Mensual\n");
            System.out.printf(" > Jefe: %s %s\n", f.jefe.nombre, f.jefe.apellidos);
            System.out.printf(" > SUELDO A PAGAR: $ %.2f\n\n", f.calcularSueldo());
        }
        System.out.println("NOMINA DE COMISIONISTAS");
        System.out.println("=".repeat("NOMINA DE COMISIONISTAS".length()));
        for (Comisionista c : this.comisionistas) {
            System.out.printf("---- Nómina de: %s %s ----\n", c.nombre, c.apellidos);
            System.out.printf(" > DNI: %s\n", c.dni);
            System.out.printf(" > Dirección: %s\n", c.direccion);
            System.out.printf(" > Tipo de Contrato: Comisionista\n");
            System.out.printf(" > Jefe: %s %s\n", c.jefe.nombre, c.jefe.apellidos);
            System.out.printf(" > SUELDO A PAGAR: $ %.2f\n\n", c.calcularSueldo());
        }
        System.out.println("NOMINA DE EMPLEADOS POR HORA");
        System.out.println("=".repeat("NOMINA DE EMPLEADOS POR HORA".length()));
        for (Hora p : this.horas) {
            System.out.printf("---- Nómina de: %s %s ----\n", p.nombre, p.apellidos);
            System.out.printf(" > DNI: %s\n", p.dni);
            System.out.printf(" > Dirección: %s\n", p.direccion);
            System.out.printf(" > Tipo de Contrato: Por Horas\n");
            System.out.printf(" > Jefe: %s %s\n", p.jefe.nombre, p.jefe.apellidos);
            System.out.printf(" > SUELDO A PAGAR: $ %.2f\n\n", p.calcularSueldo());
        }
    }
}