package FormeGeometriche;

public class Rettangolo extends Poligono{
    private final double base, altezza;
    Rettangolo(double base, double altezza){
        this.base = base;
        this.altezza = altezza;
    }
    @Override
    public double getArea() {
        return base*altezza;
    }

    @Override
    public double getPerimetro() {
        return 2*base+2*altezza;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
