/**
 * Problema 5 - Venta de entradas al teatro
Dadas las siguientes clases, que expresan una relación de herencia entre las entidades:

Se desea gestionar la venta de entradas para un espectáculo en un teatro. 
* El patio de butacas del teatro se divide en varias zonas, cada una identificada 
* por su nombre. Los datos de las zonas son los mostrados en la siguiente tabla:

NOMBRE ZONA	NÚMERO DE LOCALIDADES	PRECIO NORMA	PRECIO ABONADO
Principal	200	                    25$             17.5$
PalcoB	        40	                    70$              40$
Central	400	20$                                          14$
Lateral	100	15.5$                                        10$
Para realizar la compra de una entrada, un espectador debe indicar la zona que 
* desea y presentar al vendedor el documento que justifique que tiene algún tipo
* de descuento (estudiante, abonado o pensionista). El vendedor sacará la entrada
* del tipo apropiado y de la zona indicada, en el momento de la compra se asignará0
* a la entrada un identificador (un número entero) que permitirá la identificación 
* de la entrada en todas las operaciones que posteriormente se desee realizar con ella.

Una entrada tiene como datos asociados su identificador, la zona a la que pertenece
* y el nombre del comprador.

Los precios de las entradas dependen de la zona y del tipo de entrada según lo 
* explicado a continuación:

Entradas normales: su precio es el precio normal de la zona elegida sin ningún
* tipo de descuento.
Entradas reducidas (para estudiantes o pensionistas): su precio tiene una rebaja 
* del 15% sobre el precio normal de la zona elegida.
Entradas abonado: su precio es el precio para abonados de la zona elegida.
La interacción entre el vendedor y la aplicación es la descrita en los siguientes casos de usos.

Note

Caso de uso “Vende entrada”:

El vendedor elige la opción “vende entrada” e introduce la zona deseada, el 
* nombre del espectador y el tipo (normal, abonado o beneficiario de entrada reducida).
Si la zona elegida no está completa, la aplicación genera una nueva entrada con 
* los datos facilitados.
Si no existe ninguna zona con ese nombre, se notifica y finaliza el caso de uso 
* sin generar la entrada.
Si la zona elegida está completa lo notifica y finaliza el caso de uno sin generar la entrada.
La aplicación muestra el identificador y el precio de la entrada.
Caso de uso “Consulta entrada”:

El vendedor elige la opción “consulta entrada” e introduce el identificador de la entrada.
La aplicación muestra los datos de la entrada: nombre del espectador, precio y
* nombre de la zona. Si no existe ninguna entrada con ese identificador, lo notifica y finaliza el caso de uso
 * @author juani
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Problema_5_Teatro {
    public static void main(String[] args) {
        Teatro miTeatro = new Teatro();
        Scanner scanner = new Scanner(System.in);

        miTeatro.zonas.add(new Zona("Principal", 200, 25.0, 17.5));
        miTeatro.zonas.add(new Zona("PalcoB", 40, 70.0, 40.0));
        miTeatro.zonas.add(new Zona("Central", 400, 20.0, 14.0));
        miTeatro.zonas.add(new Zona("Lateral", 100, 15.5, 10.0));

        System.out.println("====== VENTA DE ENTRADAS DEL TEATRO ======\n");
        int opcion;
        do {
            System.out.println("--- MENÚ PRINCIPAL ---");
            System.out.println("1. Vender Entrada");
            System.out.println("2. Consultar Entrada");
            System.out.println("3. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- VENDER ENTRADA ---");
                    miTeatro.mostrarDisponibles();
                    System.out.print("Ingrese el nombre de la zona deseada: ");
                    String zonaDeseada = scanner.nextLine();
                    System.out.print("Ingrese el nombre del espectador: ");
                    String nombreEspectador = scanner.nextLine();
                    System.out.print("Ingrese el tipo de entrada (normal, reducida, abonado): ");
                    String tipoEntrada = scanner.nextLine();
                    miTeatro.venderEntrada(zonaDeseada, nombreEspectador, tipoEntrada);
                    break;

                case 2:
                    System.out.println("\n--- CONSULTAR ENTRADA ---");
                    System.out.print("Ingrese el ID de la entrada a consultar: ");
                    int idConsulta = scanner.nextInt();
                    scanner.nextLine(); 

                    miTeatro.consultarEntrada(idConsulta);
                    break;

                case 3:
                    System.out.println("\nGracias por usar el sistema. ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("\nOpción no válida. Por favor, intente de nuevo.\n");
            }
        } while (opcion != 3);

        scanner.close();
    }
}

class Zona {
    public String nombre;
    public int numeroLocalidades;
    public double precioNormal;
    public double precioAbonado;
    public int localidadesVendidas;

    public Zona(String nombre, int numeroLocalidades, double precioNormal, double precioAbonado) {
        this.nombre = nombre;
        this.numeroLocalidades = numeroLocalidades;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
        this.localidadesVendidas = 0;
    }

    public boolean venderLocalidad() {
        if (this.localidadesVendidas < this.numeroLocalidades) {
            this.localidadesVendidas++;
            return true;
        }
        return false;
    }

    public boolean estaCompleta() {
        return this.localidadesVendidas >= this.numeroLocalidades;
    }
}

class Entrada {
    public int id;
    public Zona zona;
    public String nombreComprador;
    public double precio;
    public int contadorID = 0;

    public Entrada(Zona zona, String nombreComprador) {
        this.zona = zona;
        this.nombreComprador = nombreComprador;
        this.id = ++contadorID;
    }
}

class EntradaNormal extends Entrada {
    public EntradaNormal(Zona zona, String nombreComprador) {
        super(zona, nombreComprador);
        this.precio = this.zona.precioNormal;
    }
}

class EntradaReducida extends Entrada {
    public EntradaReducida(Zona zona, String nombreComprador) {
        super(zona, nombreComprador);
        this.precio = this.zona.precioNormal - this.zona.precioNormal * 0.15;
    }
}

class EntradaAbonado extends Entrada {
    public EntradaAbonado(Zona zona, String nombreComprador) {
        super(zona, nombreComprador);
        this.precio = this.zona.precioAbonado;
    }
}

class Teatro {
    public ArrayList<Zona> zonas;
    public ArrayList<Entrada> entradasVendidas;

    public Teatro() {
        this.zonas = new ArrayList<>();
        this.entradasVendidas = new ArrayList<>();
    }

    public Zona encontrarZonaPorNombre(String nombreZona) {
        for (Zona z : this.zonas) {
            if (z.nombre.equalsIgnoreCase(nombreZona)) {
                return z;
            }
        }
        return null;
    }

    public void venderEntrada(String nombreZona, String nombreEspectador, String tipoEntrada) {
        Zona zonaSeleccionada = this.encontrarZonaPorNombre(nombreZona);

        if (zonaSeleccionada == null) {
            System.out.println("ERROR: No existe ninguna zona con el nombre '" + nombreZona + "'. Venta cancelada.");
            return;
        }

        if (zonaSeleccionada.estaCompleta()) {
            System.out.println("INFO: La zona '" + nombreZona + "' está completa. No se pueden vender más entradas.");
            return;
        }

        Entrada nuevaEntrada = null;
        if (zonaSeleccionada.venderLocalidad()) {
            switch (tipoEntrada.toLowerCase()) {
                case "normal":
                    nuevaEntrada = new EntradaNormal(zonaSeleccionada, nombreEspectador);
                    break;
                case "reducida":
                    nuevaEntrada = new EntradaReducida(zonaSeleccionada, nombreEspectador);
                    break;
                case "abonado":
                    nuevaEntrada = new EntradaAbonado(zonaSeleccionada, nombreEspectador);
                    break;
                default:
                    System.out.println("ERROR: Tipo de entrada no válido ('" + tipoEntrada + "'). Venta cancelada.");
                    zonaSeleccionada.localidadesVendidas--;
                    break;
            }
            this.entradasVendidas.add(nuevaEntrada);
            System.out.println("Venta exitosa!");
            System.out.println("--- Detalles de la Entrada ---");
            System.out.printf("  ID de Entrada: %d\n", nuevaEntrada.id);
            System.out.printf("  Comprador: %s\n", nuevaEntrada.nombreComprador);
            System.out.printf("  Zona: %s\n", nuevaEntrada.zona.nombre);
            System.out.printf("  Precio: $%.2f\n", nuevaEntrada.precio);
            System.out.println("------------------------------");
        }
    }

    public void consultarEntrada(int idEntrada) {
        Entrada entradaEncontrada = null;
        for (Entrada e : this.entradasVendidas) {
            if (e.id == idEntrada) {
                entradaEncontrada = e;
                break;
            }
        }

        if (entradaEncontrada == null) {
            System.out.println("INFO: No existe ninguna entrada con el identificador " + idEntrada + ".");
        } else {
            System.out.println("--- Detalles de la Entrada ---");
            System.out.printf("  ID de Entrada: %d\n", entradaEncontrada.id);
            System.out.printf("  Comprador: %s\n", entradaEncontrada.nombreComprador);
            System.out.printf("  Zona: %s\n", entradaEncontrada.zona.nombre);
            System.out.printf("  Precio: $%.2f\n", entradaEncontrada.precio);
            System.out.println("------------------------------");
        }
    }

    public void mostrarDisponibles() {
        System.out.println("Zonas disponibles:");
        for (Zona z : this.zonas) {
            System.out.printf("  - %s (Disponibles: %d/%d, Precio Normal: $%.2f, Precio Abonado: $%.2f)\n",
                    z.nombre, (z.numeroLocalidades - z.localidadesVendidas), z.numeroLocalidades, z.precioNormal,
                    z.precioAbonado);
        }
    }
}
