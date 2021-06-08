package Biblioteca;

import java.util.Arrays;

public class Biblioteca {
    int[] indiceLibri;
    Biblioteca(int[] indiceLibri){
        this.indiceLibri = mergeSort(indiceLibri.clone());
    }

    private int[] mergeSort(int[] arr) {
        return divide(arr);
    }

    public boolean esisteLibro(int index){
        return binSearch(index, 0, indiceLibri.length) != -1;
    }
    public int[] getIndiciLibriOrdinati(){
        return indiceLibri;
    }

    private int binSearch(int x, int base, int length) {
        int i = ((length-base)/2)+base;
        if(length<=base && indiceLibri[i]!=x){
            return -1;
        }
        if(indiceLibri[i]<x){
            return binSearch(x, i+1, length);
        }
        if(indiceLibri[i]>x){
            return binSearch(x, base, i-1);
        }
        return i;
    }
    private int[] divide(int[] arr){
        if(arr.length<=1){
            return arr;
        }
        int[] pt1 = divide(Arrays.copyOfRange(arr, 0, arr.length/2));
        int[] pt2 = divide(Arrays.copyOfRange(arr, arr.length/2, arr.length));
        return impera(pt1, pt2);
    }
    private static int[] impera(int[] arr, int[] arr2){
        int[] out = new int[arr.length+arr2.length];
        for(int i = 0, j = 0, k = 0; i<out.length; ++i){
            if(j<arr.length && k<arr2.length){
                if(arr[j]>arr2[k]){
                    out[i]=arr2[k++];
                }else{
                    out[i]=arr[j++];
                }
            }else if(j<arr.length){
                out[i]=arr[j++];
            }else{
                out[i]=arr2[k++];
            }
        }
        return out;
    }
}
