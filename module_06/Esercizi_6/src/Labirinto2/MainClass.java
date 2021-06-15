package Labirinto2;

import java.util.*;

public class MainClass {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        System.out.print("Insert row count > ");
        int row = Integer.parseInt(sc.nextLine());
        System.out.print("Insert column count > ");
        int col = Integer.parseInt(sc.nextLine());
        System.out.println("Creating a cool map may require some time, please wait");
        Maze maze = Maze.getInstance(row, col);
        boolean mazeCompleted1 = false;
        boolean mazeCompleted2 = false;
        while (!mazeCompleted1 && !mazeCompleted2) {
            try {
                System.out.println(maze);
                char movement = getMovement();
                mazeCompleted1 = maze.move(movement, maze.getPlayer().getPosition(), Exit.class);
                mazeCompleted2 = moveMonster(maze.getMonster());
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.err.println(Arrays.toString(e.getStackTrace()));
            }
        }
        System.out.println(maze);
        if(mazeCompleted1)
            System.out.println("You've completed the game");
        if(mazeCompleted2 && ! mazeCompleted1)
            System.out.println("A monster killed you, game ends");
    }

    private static boolean moveMonster(List<Monster> monster) {
        boolean solved = false;
        for(Monster m: monster){
            m.action();
            solved = m.isSolved();
            if(solved){
                break;
            }
        }
        return solved;
    }

    private static char getMovement(){
        String s = sc.nextLine();
        return s.length()>0?s.charAt(0):'\0';
    }
}