package Iterable;

public class MiaStringa implements Iterabile{
    private final char[] arr;
    private int index = 0;
    MiaStringa(String s){
        arr = s.toCharArray();
    }

    @Override
    public boolean hasNext() {
        return index<arr.length;
    }

    @Override
    public Object next(){
        return arr[index++];
    }

    @Override
    public void reset() {
        index = 0;
    }
}
