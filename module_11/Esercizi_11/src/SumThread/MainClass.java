package SumThread;

public class MainClass {
    public static void main(String[] args) {
        ArrayGenerator generator = new ArrayGenerator(10000000);
        generator.start();
        try {
            generator.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int[] arr = generator.getFinalValue();
        ArrayParallelSum summer = new ArrayParallelSum(arr);
        summer.start();
        int procValue = 0;
        for (int j : arr) {
            procValue += j;
        }
        try {
            summer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(procValue + " " + summer.getTotalSum());
    }
}
