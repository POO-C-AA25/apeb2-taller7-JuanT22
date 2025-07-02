import java.util.ArrayList;
public class EjecutorFiguraGeometrica {
    public static void main(String[] args) {
        ArrayList<FiguraGeometrica> figuras = new ArrayList<FiguraGeometrica>();
        FiguraGeometrica rectangulo = new Rectangulo(10, 5);
        Circunferencia circunferencia = new Circunferencia(4);
        figuras.add(rectangulo);
        figuras.add(circunferencia);
        for(FiguraGeometrica figura : figuras){
            System.out.println();
            System.out.println(figura);
        }
    }
}

abstract class FiguraGeometrica{
    public double area;
    
    public abstract double obtenerArea();

    @Override
    public String toString() {
        return "figuraGeometrica{" + "area=" + area + '}';
    }
}

class Rectangulo extends figuraGeometrica{

    public Rectangulo(double longitud, double ancho) {
        this.longitud = longitud;
        this.ancho = ancho;
    }
    
    public double longitud, ancho;
    public double obtenerArea(){
        area = longitud * ancho;
        return area;
    }

    @Override
    public String toString() {
        return "Rectangulo{" + "longitud=" + longitud + ", ancho=" + ancho + "} " + super.toString();
    }
  
} 

class Circunferencua extends figuraGeometrica{

    public Circunferencua(double radio) {
        this.radio = radio;
    }
    
    public double radio;
    public double obtenerArea(){
        area = Math.PI * Math.pow(radio, 2);
        return area;
    }

    @Override
    public String toString() {
        return "Circunferencua{" + "radio=" + radio + "} " + super.toString();
    }
    
}
