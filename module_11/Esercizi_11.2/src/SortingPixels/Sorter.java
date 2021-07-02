package SortingPixels;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;

public class Sorter {
    private final BufferedImage output;
    private final String fileName;
    private Integer[] arr;

    public Sorter(String inputImageName, String outputImageName) throws IOException {
        BufferedImage input = ImageIO.read(new File(inputImageName));
        output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        fileName = outputImageName;
        int arrLength = input.getWidth()*input.getHeight();
        arr = new Integer[arrLength];
        for(int i = 0; i<input.getHeight(); ++i){
            for(int j=0; j<input.getWidth(); ++j){
                arr[i*input.getWidth()+j] = input.getRGB(i,j);
            }
        }
    }

    public void sort() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        Future<Integer[]> call = pool.submit(new Divide(arr, pool, 0, arr.length));
        while(!call.isDone()){
            Thread.yield();
        }
        arr = call.get();
    }

    public void createOutput() throws IOException {
        for(int i = 0; i<output.getHeight(); ++i){
            for(int j = 0; j<output.getWidth(); ++j){
                Color c = new Color(arr[i*output.getWidth()+j]);
                output.setRGB(c.getRed(), c.getGreen(), c.getBlue());
            }
        }
        String[] tmp = fileName.split("\\.");
        ImageIO.write(output, tmp[tmp.length-1], new File(fileName));
    }

    private static class Divide implements Callable<Integer[]>{
        private final Integer[] localArr;
        private final ExecutorService localPool;
        int startIndex, endIndex;

        Divide(Integer[] localArr, ExecutorService localPool, int startIndex, int endIndex){
            this.localArr = localArr;
            this.localPool = localPool;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public Integer[] call() throws Exception {
            if(endIndex-startIndex<=1){
                return new Integer[]{localArr[startIndex]};
            }
            Future<Integer[]> f1 = localPool.submit(new Divide(localArr, localPool, startIndex, endIndex / 2));
            Future<Integer[]> f2 = localPool.submit(new Divide(localArr, localPool, endIndex, endIndex / 2));
            while(!f1.isDone() || !f2.isDone()){
                Thread.yield();
            }
            Future<Integer[]> fin = localPool.submit(new Merge(f1.get(), f2.get()));
            while(!fin.isDone()){
                Thread.yield();
            }
            return fin.get();
        }
    }
    private static class Merge implements Callable<Integer[]> {
        private final Integer[] arr1, arr2;

        public Merge(Integer[] arr1, Integer[] arr2) {
            this.arr1=arr1;
            this.arr2=arr2;
        }

        @Override
        public Integer[] call() throws Exception {
            Integer[] out = new Integer[arr1.length+arr2.length];
            for(int i = 0, j = 0, k = 0; i<out.length; ++i){
                if(j<arr1.length && k<arr2.length){
                    if(arr1[j]>arr2[k]){
                        out[i]=arr2[k++];
                    }else{
                        out[i]=arr1[j++];
                    }
                }else if(j<arr1.length){
                    out[i]=arr1[j++];
                }else{
                    out[i]=arr2[k++];
                }
            }
            return out;
        }
    }
}
