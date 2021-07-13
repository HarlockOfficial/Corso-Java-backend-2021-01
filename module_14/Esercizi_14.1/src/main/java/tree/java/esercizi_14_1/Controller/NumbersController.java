package tree.java.esercizi_14_1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tree.java.esercizi_14_1.Repository.StringObject;
import tree.java.esercizi_14_1.Service.CounterService;

import java.util.Random;

@RestController
public class NumbersController {
    private final Random generator;
    private final CounterService counterService;

    @Autowired
    public NumbersController(CounterService counterService) {
        this.generator = new Random();
        this.counterService = counterService;
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public Number generateRandom(){
        return generator.nextInt();
    }

    @RequestMapping(value = "/length/{string}", method = RequestMethod.GET)
    public StringObject getStringLength(@PathVariable String string){
        return new StringObject(string);
    }

    @RequestMapping(value = "/counter", method = RequestMethod.GET)
    public Number getCounter(){
        return counterService.getCounter();
    }

    @RequestMapping(value = "/counter", method = RequestMethod.POST)
    public Number increaseCounter(){
        return counterService.increaseCounter();
    }

    @RequestMapping(value = "/counter", method = RequestMethod.DELETE)
    public Number decreaseCounter(){
        return counterService.decreaseCounter();
    }
}
