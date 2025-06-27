/**
 * Problema 6 - Sistema Un Banco
El banco UN BANCO mantiene las cuentas de varios clientes. Los datos que 
* describen a cada una de las cuentas consisten en el número de cuenta, el 
* nombre del cliente y el balance actual. Escriba una clase para implementar
* dicha cuenta bancaria. El método constructor debe aceptar como parámetros el 
* número de cuenta y el nombre. Debe proporcionarse métodos para depositar o 
* retirar una cantidad de dinero y obtener el balance actual.

El banco ofrece a sus clientes dos tipos de cuentas, una de CHEQUES y una de 
* AHORROS. Una cuenta de cheques puede sobregirarse (el balance puede ser menor
* que cero), pero una cuenta de ahorros no. Al final de cada mes, se calcula el 
* interés sobre la cantidad que tenga la cuenta de ahorros. Este interés se suma
* al balance. Escriba clases para describir cada uno de estos tipos de cuentas,
* haciendo un máximo uso de la herencia. La clase de la cuenta de ahorros debe 
* proporcionar un método que sea invocado para calcular el interés. Además, el
* banco está pensando en implementar una cuenta PLATINO que viene siendo similar
* a los otros dos tipos anteriores de cuentas bancarias, ésta tiene el interés 
* del 10%, sin cargos ni castigos por sobregiro.

Note

Ud. debe implementar una clase de PRUEBA (Clase de ejecución) desde la cual se 
* pueda evidenciar el correcto funcionamiento de cada clase con n clientes, y
* que además se pueda mostrar el balance (estado de cuenta) para cada cliente.
 * @author juani
 */

import java.util.ArrayList;

public class Problema_6_Banco {
    public static void main(String[] args) {
        CuentaBancaria cuenta1 = new Cheque("CHQ-023", "Pitufaa Cruz");
        CuentaBancaria cuenta2 = new Ahorro("AHO-013", "Lalangui Martin");
        CuentaBancaria cuenta3 = new Platino("AHP-020", "Jimenez Bladimir");

        cuenta1.depositar(5000);
        cuenta2.depositar(1000);
        cuenta3.depositar(5000);
        ((Ahorro) cuenta2).calcularInteres();
        ((Platino) cuenta3).calcularInteres();
        cuenta1.retirar(1000);
        cuenta2.retirar(500);
        cuenta3.retirar(550);

        Banco banco = new Banco();
        banco.agregarCuenta(cuenta1);
        banco.agregarCuenta(cuenta2);
        banco.agregarCuenta(cuenta3);
        banco.mostrarEstadosCuenta();
    }
}

class Banco {
    public ArrayList<CuentaBancaria> cuentas;

    public Banco() {
        cuentas = new ArrayList<>();
    }

    public void agregarCuenta(CuentaBancaria cuenta) {
        cuentas.add(cuenta);
    }

    public void mostrarEstadosCuenta() {
        System.out.println("\n=== ESTADOS DE CUENTA DEL BANCO ===");
        for (CuentaBancaria cuenta : cuentas) {
            System.out.printf("Cliente: %s\n", cuenta.nombreCliente);
            System.out.printf("Número de cuenta: %s\n", cuenta.numeroCuenta);
            System.out.printf("Balance actual: %.2f\n", cuenta.saldo);
            System.out.printf("Tipo de cuenta: %s\n", cuenta.tipoCuenta);
            System.out.println("---------------------------");
        }
    }
}

class CuentaBancaria {
    public String numeroCuenta;
    public String nombreCliente;
    public double saldo;
    public String tipoCuenta;

    public CuentaBancaria(String numeroCuenta, String nombreCliente) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
        this.saldo = 0.0;
        this.tipoCuenta = null;
    }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            System.out.printf("Se ha agregado %.2f a la cuenta de %s\n", cantidad, nombreCliente);
        } else {
            System.out.println("No se puede agregar 0 dólares.");
        }
    }

    public void retirar(double cantidad) {
        if (cantidad > 0) {
            saldo -= cantidad;
            System.out.printf("Se ha retirado %.2f de la cuenta %s. Saldo actual: %.2f\n", cantidad, nombreCliente,
                    saldo);
        } else {
            System.out.println("No puedes retirar 0 dólares.");
        }
    }
}

class Cheque extends CuentaBancaria {
    public Cheque(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
        this.tipoCuenta = "Cheque";
    }

    @Override
    public void retirar(double cantidad) {
        super.retirar(cantidad);
    }
}

class Ahorro extends CuentaBancaria {
    public double interes = 0.05;

    public Ahorro(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
        this.tipoCuenta = "Ahorros";
    }

    public void calcularInteres() {
        double interesTotal = saldo * interes;
        depositar(interesTotal);
        System.out.printf("Interés del %.2f%% aplicado. Saldo actual: %.2f\n", (interes * 100), saldo);
    }

    @Override
    public void retirar(double cantidad) {
        if (cantidad > 0) {
            if (saldo - cantidad >= 0) {
                super.retirar(cantidad);
            } else {
                System.out.println("No puede retirar un monto mayor a su saldo.");
            }
        }
    }
}

class Platino extends CuentaBancaria {
    public double interes = 0.10;

    public Platino(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
        this.tipoCuenta = "Platino";
    }

    public void calcularInteres() {
        double interesTotal = saldo * interes;
        depositar(interesTotal);
        System.out.printf("Interés Platino del %.2f%% aplicado. Saldo actual: %.2f\n", (interes * 100), saldo);
    }

    @Override
    public void retirar(double cantidad) {
        if (cantidad > 0) {
            saldo -= cantidad;
            System.out.printf("Se ha retirado %.2f de la cuenta %s. Saldo actual: %.2f\n", cantidad, nombreCliente,
                    saldo);
        } else {
            System.out.println("No puedes retirar 0 dólares.");
        }
    }

}
