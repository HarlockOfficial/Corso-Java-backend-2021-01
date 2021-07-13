package tree.java.esercizi_14_2.Entity;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Box {
    private Move move;

    public Box() {
        this.move = Move.NONE;
    }

    public void setMove(Move move) {
        if(move != Move.NONE) throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Move already set at this place");
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    public void clear() {
        this.move = Move.NONE;
    }
}
