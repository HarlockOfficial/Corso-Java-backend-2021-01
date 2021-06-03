public class SommaDiagonali {
    public static long calculateDiagPrinc(int[][] matr){
        int somma = 0;
        for(int i = 0; i<matr.length; ++i){
            somma += matr[i][i];
        }
        return somma;
    }
    public static long calculateDiagMin(int[][] matr){
        int somma = 0;
        for(int i = 0; i<matr.length; ++i){
            somma += matr[i][matr.length-i];
        }
        return somma;
    }
}
