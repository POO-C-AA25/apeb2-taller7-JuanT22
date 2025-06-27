/**
 * Problema 1 - Jerarquía de clases para el capítulo de libro
Dibujad un diagrama de clases que muestre la estructura de un capítulo de libro
* ; un capítulo está compuesto por varias secciones, cada una de las cuales 
* comprende varios párrafos y figuras. Un párrafo incluye varias sentencias, 
* cada una de las cuales contiene varias palabras.

Note:

Suponga que en un futuro se prevé que el sistema gestione además de párrafos y 
* figuras otros componentes, como tablas, listas, viñetas, etc.
Suponga además que una palabra puede aparecer en varias sentencias.
 * @author juani
 */
import java.util.ArrayList;

public class Problema_1_Libro {
    public static void main(String[] args) {
        Palabra p1 = new Palabra("Hola");
        Palabra p2 = new Palabra("soy");
        Palabra p3 = new Palabra("Juan");
        Sentencia s1 = new Sentencia();
        s1.addPalabra(p1);
        s1.addPalabra(p2);
        s1.addPalabra(p3);
        Parrafo parrafo1 = new Parrafo();
        parrafo1.addSentencia(s1);
        Figura figura1 = new Figura("Figura de programas en java");
        Seccion seccion1 = new Seccion("Indice");
        seccion1.addComponente(parrafo1);
        seccion1.addComponente(figura1);
        Capitulo capitulo1 = new Capitulo("Capitulo 1: El Amor");
        capitulo1.agregarSeccion(seccion1);
        capitulo1.mostrar();
    }
}

class Palabra {
    public String texto;

    public Palabra(String texto) {
        this.texto = texto;
    }
}

class Sentencia {
    public ArrayList<Palabra> palabras;

    public Sentencia() {
        palabras = new ArrayList<>();
    }

    public void addPalabra(Palabra palabra) {
        palabras.add(palabra);
    }

    public void mostrar() {
        for (Palabra p : palabras) {
            System.out.print(p.texto + " ");
        }
        System.out.println();
    }
}

class Componente {
    public void mostrar() {
    }
}

class Parrafo extends Componente {
    public ArrayList<Sentencia> sentencias;

    public Parrafo() {
        sentencias = new ArrayList<>();
    }

    public void addSentencia(Sentencia sentencia) {
        sentencias.add(sentencia);
    }

    public void mostrar() {
        System.out.print("    Párrafo: ");
        for (Sentencia s : sentencias) {
            s.mostrar();
        }
    }
}

class Figura extends Componente {
    public String descripcion;

    public Figura(String descripcion) {
        this.descripcion = descripcion;
    }

    public void mostrar() {
        System.out.printf("    Figura: %s\n", descripcion);
    }
}

class Seccion {
    public String titulo;
    public ArrayList<Componente> componentes;

    public Seccion(String titulo) {
        this.titulo = titulo;
        componentes = new ArrayList<>();
    }

    public void addComponente(Componente componente) {
        componentes.add(componente);
    }

    public void mostrar() {
        System.out.println("  Sección: " + titulo);
        for (Componente c : componentes) {
            c.mostrar();
        }
    }
}

class Capitulo {
    public String titulo;
    public ArrayList<Seccion> secciones;

    public Capitulo(String titulo) {
        this.titulo = titulo;
        secciones = new ArrayList<>();
    }

    public void agregarSeccion(Seccion seccion) {
        secciones.add(seccion);
    }

    public void mostrar() {
        System.out.println("Título del capítulo: " + titulo);
        for (Seccion s : secciones) {
            s.mostrar();
        }
    }
}