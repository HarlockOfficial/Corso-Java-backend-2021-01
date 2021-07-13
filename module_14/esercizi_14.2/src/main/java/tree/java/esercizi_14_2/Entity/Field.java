package tree.java.esercizi_14_2.Entity;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Stack;

public class Field {
    private final Box[][] field;
    private final Stack<Position> positionStack;

    public Field(){
        positionStack = new Stack<>();
        field = new Box[3][3];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = new Box();
            }
        }
    }

    public void setField(Move move, Short x, Short y){
        positionStack.add(new Position(x, y));
        field[x][y].setMove(move);
    }

    public void undoLastMove(){
        if(positionStack.size()<=0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot remove any move");
        }
        Short x = positionStack.peek().getX(), y = positionStack.pop().getY();
        field[x][y].clear();
    }

    public Box[][] getField() {
        return field;
    }
}
