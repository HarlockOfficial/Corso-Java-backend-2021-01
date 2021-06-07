public class ListaOrdinata {
    public static void main(String[] args) {
        ListaOrdinata l = new ListaOrdinata();
        boolean x = l.insert(-100);
        if(!x){
            System.err.println("Error in insert");
        }
        l.insert(-180);
        l.insert(10);
        l.insert(0);
        l.insert(-21);
        l.insert(56);
        l.insert(98);
        l.insert(-123);
        l.insert(88);
        l.insert(75);
        l.insert(-965);
        l.insert(34);
        System.out.println(l);
        System.out.println(l.indexOf(0));
        l.remove(0);
        System.out.println(l);
        System.out.println(l.indexOf(0));
        x = l.remove(0);
        if(!x){
            System.err.println("Error in Remove");
        }
        System.out.println(l);
        System.out.println(l.indexOf(0));
    }
    private int[] arr;
    private static final double increseFactor = 1.5;
    private int index;
    public ListaOrdinata(){
        arr = new int[10];
        index = 0;
    }
    public boolean insert(int x){
        if(++index>=arr.length){
            int[] out = new int[(int) Math.ceil(arr.length*increseFactor)];
            System.arraycopy(arr, 0, out, 0, index);
            arr = out;
        }
        int i = 0;
        for(; i<index-1; ++i){
            if(x>arr[i])
                continue;
            shift(arr, +1, i);
            break;
        }
        arr[i] = x;
        return true;
    }
    public int indexOf(int x){
        return binSearch(x, 0, index);
    }
    public boolean remove(int x){
        int i = indexOf(x);
        if(i == -1){
            return false;
        }
        shift(arr, -1, i);
        --index;
        return true;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i<index; ++i){
            sb.append(arr[i]);
            if(i+1<index){
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }

    private void shift(int[] arr, int shift, int i1) {
        for(int i = shift>0?index-1:i1; (shift>0 && i>=i1+shift) || (shift<0 && i<=index); i+=(shift>0?-1:1)){
            arr[i] = arr[i-shift];
        }
    }
    private int binSearch(int x, int base, int length) {
        int i = ((length-base)/2)+base;
        if(length<=base && arr[i]!=x){
            return -1;
        }
        if(arr[i]<x){
            return binSearch(x, i+1, length);
        }
        if(arr[i]>x){
            return binSearch(x, base, i-1);
        }
        return i;
    }
}
