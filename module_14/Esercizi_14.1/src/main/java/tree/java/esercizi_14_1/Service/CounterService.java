package tree.java.esercizi_14_1.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tree.java.esercizi_14_1.Repository.Counter;

@Service
public class CounterService {
    private final Counter counter;

    @Autowired
    public CounterService(Counter counter){
        this.counter = counter;
    }

    public Long increaseCounter(){
        Long count = counter.getCounter();
        count += 1;
        counter.setCounter(count);
        return count;
    }

    public Long decreaseCounter(){
        Long count = counter.getCounter();
        count -= 1;
        counter.setCounter(count);
        return count;
    }

    public Long getCounter(){
        return counter.getCounter();
    }
}
