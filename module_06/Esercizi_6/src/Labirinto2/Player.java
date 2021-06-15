package Labirinto2;

public class Player extends Entity{
    private final Pair<Integer> position;
    private final Maze maze;
    private boolean solved;
    Player(Maze maze, Pair<Integer> position){
        super(true);
        this.position = position;
        this.maze = maze;
        solved = false;
    }

    public Pair<Integer> getPosition() {
        return position;
    }

    @Override
    public void action() {
        if(position == null){
            return;
        }
        String pos = maze.solveMaze(position, Exit.class);
        char move = pos.length()>0?pos.charAt(0):'q';
        solved |= maze.move(move, position, Exit.class);
        switch (move) {
            case 'w' -> --position.x;
            case 'a' -> --position.y;
            case 's' -> ++position.x;
            case 'd' -> ++position.y;
        }
        if(solved){
            System.out.println("Exit reached, game ends");
        }
    }
    public void setPosition(Pair<Integer> p){
        position.x = p.x;
        position.y = p.y;
    }
    @Override
    public String toString() {
        return "P";
    }

    @Override
    protected Object clone() {
        return new Player(maze, position);
    }
}
