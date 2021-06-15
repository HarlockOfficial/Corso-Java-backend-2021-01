package Lista;

public interface Lista<K> {
    K get(int index);
    boolean contains(K o);
    int size();
    boolean add(K o);
    boolean remove(int index);
    void clear();
}
