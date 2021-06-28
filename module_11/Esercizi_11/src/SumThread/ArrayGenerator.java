package SumThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrayGenerator extends Thread {
    private final Random generator;
    private final int[] arr;
    private boolean completed;

    public ArrayGenerator(int length) {
        arr = new int[length];
        generator = new Random();
        completed = false;
    }

    @Override
    public void run() {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i += 100) {
            int finalI = i;
            Thread t = new Thread(() -> {
                for (int j = finalI; j < finalI + 100 && j < arr.length; ++j) {
                    arr[j] = generator.nextInt(21) - 10;
                }
            });
            t.start();
            list.add(t);
        }
        try {
            for (Thread thread : list) {
                if(thread!=null) {
                    thread.join();
                }
            }
            completed = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isCompleted() {
        return completed;
    }

    public int[] getFinalValue() throws IllegalThreadStateException {
        if (!completed) {
            throw new IllegalThreadStateException("Generation si still running");
        }
        return arr;
    }
}
