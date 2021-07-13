package tree.java.esercizi_14_2.Entity;

import java.util.UUID;

public class Match {
    private final UUID id;
    private final Player player1, player2;
    private Field field;
    private Player turn;

    public Match(Player player1, Player player2) {
        id = UUID.randomUUID();
        this.player1 = player1;
        this.player2 = player2;
        this.field = new Field();
        turn = player1;
    }
    public void addMove(Move move, Short x, Short y){
        field.setField(move, x, y);
        turn = turn.equals(player1)?player2:player1;
    }

    public void removeMove(){
        field.undoLastMove();
        turn = turn.equals(player1)?player2:player1;
    }

    public UUID getId() {
        return id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Field getField() {
        return field;
    }

    public void resetField(){
        field = new Field();
    }

    public Player getTurn() {
        return turn;
    }
}
