package Queue;

import java.util.PriorityQueue;

public class Supermarket {
    private final PriorityQueue<Customer> coda;
    public Supermarket(){
        this.coda = new PriorityQueue<>();
    }
    public boolean addCustomer(Customer c){
        return coda.add(c);
    }
    public Customer getNextCustomer(){
        return coda.poll();
    }
    public Customer first(){
        return coda.peek();
    }
    public int getTotalCustomersCount(){
        return coda.size();
    }
}
