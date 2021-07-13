package tree.java.esercizi_14_2.Entity;

public enum Move {
    X ('x'),
    O ('o'),
    NONE (' ');
    private char move;
    private Move(char move){
        this.move = move;
    }
}
