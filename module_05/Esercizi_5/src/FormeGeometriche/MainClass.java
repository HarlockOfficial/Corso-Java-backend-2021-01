package FormeGeometriche;

public class MainClass {
    public static void main(String[] args) {
        Poligono p = new Rettangolo(3., 5.);
        Poligono p1 = new Quadrato(3.);
        System.out.println(p+" "+p1);
    }
}
