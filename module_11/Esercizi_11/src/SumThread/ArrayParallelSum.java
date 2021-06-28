package SumThread;

import java.util.ArrayList;
import java.util.List;

public class ArrayParallelSum extends Thread {
    private final int[] arr;
    private boolean completed;
    private int totalSum;

    public ArrayParallelSum(int[] arr) throws IllegalArgumentException {
        if (arr == null) {
            throw new IllegalArgumentException();
        }
        this.arr = arr;
        completed = false;
        totalSum = 0;
    }

    @Override
    public void run() {
        List<Thread> spownedThreads = new ArrayList<>();
        for (int i = 0; i < arr.length; i += 100) {
            int finalI = i;
            Thread t = new Thread(() -> {
                int tmpSum = 0;
                for (int j = finalI; j < finalI + 100 && j < arr.length; ++j) {
                    tmpSum += arr[j];
                }
                sum(tmpSum);
            });
            t.start();
            spownedThreads.add(t);
        }
        try {
            for (Thread spownedThread : spownedThreads) {
                if(spownedThread!=null){
                    spownedThread.join();
                }
            }
            completed = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void sum(int val) {
        totalSum += val;
        System.out.println(totalSum);
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getTotalSum() {
        if (!completed) {
            throw new IllegalThreadStateException("Sum did not end yet");
        }
        return totalSum;
    }
}
