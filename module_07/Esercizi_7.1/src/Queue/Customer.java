package Queue;

public class Customer implements Comparable<Customer>{
    private int eta;
    private String id, nome, cognome;
    public Customer(String id, String nome, String cognome, int eta){
        this.eta = eta;
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        if(eta>=0)
            this.eta = eta;
    }

    public String getName() {
        return nome;
    }

    @Override
    public int compareTo(Customer o) {
        int cmp = Integer.compare(o.eta, this.eta);
        if(cmp==0){
            cmp = id.compareTo(o.id);
        }
        return cmp;
    }
}
