package Labirinto2;

public class Monster extends Entity{
    private final Maze maze;
    private final Pair<Integer> position;
    private boolean solved;
    public Monster(Maze maze, Pair<Integer> position){
        super(true);
        this.maze = maze;
        this.position = position;
        solved = false;
    }
    @Override
    public void action() {
        if(position == null){
            return;
        }
        String pos = maze.solveMaze(position, Player.class);
        char move = pos.length()>0?pos.charAt(0):'q';
        solved |= maze.move(move, position, Player.class);
        switch (move) {
            case 'w' -> --position.x;
            case 'a' -> --position.y;
            case 's' -> ++position.x;
            case 'd' -> ++position.y;
        }
    }

    @Override
    public String toString() {
        return "M";
    }

    @Override
    protected Object clone() {
        return new Monster(maze, position);
    }

    public boolean isSolved() {
        return solved;
    }

    public Pair<Integer> getPosition() {
        return position;
    }
    public void setPosition(Pair<Integer> p){
        position.x = p.x;
        position.y = p.y;
    }
}
