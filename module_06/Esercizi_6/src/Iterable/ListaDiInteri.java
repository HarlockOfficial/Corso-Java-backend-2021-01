package Iterable;

public class ListaDiInteri implements Iterabile{
    private final int[] arr;
    private int index = 0;
    public ListaDiInteri(int[] arr){
        this.arr = arr.clone();
    }

    @Override
    public boolean hasNext() {
        return index<arr.length;
    }

    @Override
    public Integer next(){
        return arr[index++];
    }
    @Override
    public void reset() {
        index = 0;
    }
}
