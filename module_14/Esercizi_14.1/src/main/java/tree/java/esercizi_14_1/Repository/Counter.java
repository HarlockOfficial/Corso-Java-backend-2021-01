package tree.java.esercizi_14_1.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Counter {
    private Long counter;

    @Autowired
    public Counter() {
        this.counter = 0L;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    public Long getCounter(){
        return counter;
    }
}
