package Lista;

public class MiaStringa implements Lista<Character>{
    private char[] arr;
    private int position;
    private static final double INCRESE_FACTOR = 1.5;
    private static final int BASE_LENGTH = 10;
    MiaStringa(String s){
        arr = s.toCharArray();
        position = s.length();
    }
    MiaStringa(){
        arr = new char[BASE_LENGTH];
        position = 0;
    }

    @Override
    public Character get(int index) {
        return arr[index];
    }

    @Override
    public boolean contains(Character o) {
        for(int i = 0; i<position; ++i){
            if(o.equals(arr[i])){
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return position;
    }

    @Override
    public boolean add(Character o) {
        if(position>=arr.length){
            char[] tmp = arr;
            arr = new char[(int)(arr.length*INCRESE_FACTOR)];
            if (position + 1 >= 0) System.arraycopy(tmp, 0, arr, 0, position + 1);
        }
        arr[position++] = o;
        return true;
    }

    @Override
    public boolean remove(int index) {
        if(index>=arr.length){
            return false;
        }
        if (position - index >= 0) System.arraycopy(arr, index + 1, arr, index, position - index);
        --position;
        return true;
    }

    @Override
    public void clear() {
        position = 0;
    }
}
