/**
 * Problema 3 - Sistema de envío de mensajes a móviles
Implemente un sistema de envío de mensajes a móviles. Existen dos tipos de 
* mensajes que se pueden enviar entre móviles, mensajes de texto (SMS) y 
* mensajes que contienen imágenes (MMS). Por un lado, los mensajes de texto 
* contienen un mensaje en caracteres que se desea enviar de un móvil a otro. 
* Por otro lado, los mensajes que contienen imágenes almacenan información 
* sobre la imagen a enviar, la cual se representará por el nombre del fichero 
* que la contiene. Independientemente del tipo de mensaje, cada mensaje tendrá 
* asociado un remitente de dicho mensaje y un destinatario. Ambos estarán 
* definidos obligatoriamente por un número de móvil, y opcionalmente se 
* podrá guardar información sobre su nombre. Además, los métodos enviarMensaje 
* y visualizarMensaje deben estar definidos.

Note:
Para probar el diseño jerarquico de clases, instancia en el clase de prueba 
* Ejecutor, con datos ficticios.
* 
 * @author juani
 */

public class Problema_3_Mensaje {
    public static void main(String[] args) {
        Movil remitente = new Movil(997846080, "Mateo Gonzales");
        Movil destinatario = new Movil(99121762, "Pitufa Cruz");

        System.out.println("Testeando envio de SMS");
        SMS miSms = new SMS(remitente, destinatario, "How are you?");
        miSms.enviarSMS();
        miSms.verSMS();
        System.out.println("Testeando envio de MSM");
        MMS miMms = new MMS(remitente, destinatario, "picture.png");
        miMms.enviarMMS();
        miMms.verMMS();
    }
}

class Movil {
    public int numero;
    public String nombre;

    public Movil(int numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Movil [numero=" + numero + ", nombre=" + nombre + "]";
    }

}

class Mensaje {
    public Movil remitente;
    public Movil destinatario;

    public Mensaje() {
    }

    public Mensaje(Movil remitente, Movil destinatario) {
        this.destinatario = destinatario;
        this.remitente = remitente;
    }

    public void enviar() {
        System.out.println("Mensaje a enviar");
    }

    public void recibir() {
        System.out.println("Visor del mensaje");
        System.out.printf("De: %15s", remitente.toString());
        System.out.printf("Para: %15s", destinatario.toString());
        System.out.println("-".repeat(destinatario.toString().length()));
    }
}

class SMS extends Mensaje {
    public String texto;    

    public SMS(Movil remitente, Movil destinatario, String texto) {
        super(remitente, destinatario);
        this.texto = texto;
    }

    public void enviarSMS() {
        System.out.printf("Enviando SMS de [%10s] a [%10s]", remitente.numero, destinatario.numero);
        System.out.println("¡SMS Enviado!");
    }

    public void verSMS() {
        System.out.println("----- Visualizando SMS -----");
        System.out.println("De: " + remitente.toString());
        System.out.println("Para: " + destinatario.toString());
        System.out.println("Mensaje: " + texto);
        System.out.println("--------------------------");
    }
}

class MMS extends Mensaje {
    public String nombreImagen;

    public MMS(Movil remitente, Movil destinatario, String nombreImagen) {
        super(remitente, destinatario);
        this.nombreImagen = nombreImagen;
    }

    public void enviarMMS() {
        System.out.printf("Enviando MMS de [%10s] a [%10s]", remitente.numero, destinatario.numero);
        System.out.println("¡MMS Enviado!");
    }

    public void verMMS() {
        System.out.println("----- Visualizando MMS -----");
        System.out.printf("\nDe: %15s ", remitente.toString());
        System.out.printf("\nPara: %15s", destinatario.toString());
        System.out.printf("\nMensaje: %15s", nombreImagen);
        System.out.println();
        System.out.println("-".repeat(nombreImagen.length()));
    }
}