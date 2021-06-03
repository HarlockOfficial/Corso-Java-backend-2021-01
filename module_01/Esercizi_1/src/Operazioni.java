public class Operazioni {
    public static void main(String[] args) {
        int x = 4;
        int y = 6;
        calculateOperations(x,y);
    }
    public static void calculateOperations(int a, int b){
        int sum = a+b;
        int sub = a-b;
        double div = (b==0?0:((double)a)/b);
        int mul = a*b;
        System.out.println(sum);
        System.out.println(sub);
        System.out.println(div);
        System.out.println(mul);
    }
}
