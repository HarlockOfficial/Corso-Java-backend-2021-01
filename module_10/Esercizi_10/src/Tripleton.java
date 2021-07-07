public class Tripleton {
    private static final Tripleton[] instances = new Tripleton[3];
    private static final boolean[] instantiated = new boolean[]{false, false, false};
    private final int id;
    private Tripleton(int instanceId){
        id = instanceId;
    }

    public static Tripleton getInstance(int index) {
        Tripleton instance = null;
        if(index>=0 && index<3){
            if(instantiated[index]){
                instance = instances[index];
            }else{
                instantiated[index] = true;
                instance = instances[index] = new Tripleton(index);
            }
        }
        return instance;
    }

    @Override
    public String toString() {
        return "Instance id: " + id;
    }
}
