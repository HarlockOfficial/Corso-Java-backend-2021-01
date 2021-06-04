import java.util.*;

public class Maze {
    private static final Scanner sc = new Scanner(System.in);

    public enum MazeElement{
        Wall ('W'),
        Exit ('E'),
        Player ('P'),
        None ('-');

        private char c;
        private static Random generator = new Random();
        MazeElement(char c){
            this.c = c;
        }

        @Override
        public String toString() {
            return c+"";
        }
        public static MazeElement getRandom(){
            MazeElement[] arr = values();
            int n = generator.nextInt(100);
            if(n>60){
                return Wall;
            }else if(n>20){
                return None;
            }else if(n>10){
                return Player;
            }
            return Exit;
        }
    }

    public static void main(String[] args) {
        System.out.println("Creating a cool map may require some time, please wait");
        MazeElement[][] maze = getMaze(5, 4);
        boolean mazeCompleted = false;
        while (!mazeCompleted) {
            try {
                printMaze(maze);
                char movement = getMovement();
                mazeCompleted = move(maze, movement);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.err.println(Arrays.toString(e.getStackTrace()));
            }
        }
        printMaze(maze);
        System.out.println("You've completed the game");
    }

    private static char getMovement(){
        String s = sc.nextLine();
        return s.length()>0?s.charAt(0):'\0';
    }

    public static MazeElement[][] getMaze(int row, int col) {
        int exitCounter, playerCounter, wallCounter, i, j;
        MazeElement[][] maze = new MazeElement[row][col];
        do {
            exitCounter = 0;
            playerCounter = 0;
            wallCounter = 0;
            i = 0;
            while (i < row) {
                j = 0;
                while (j < col) {
                    maze[i][j] = MazeElement.getRandom();
                    exitCounter += maze[i][j] == MazeElement.Exit ? 1 : 0;
                    playerCounter += maze[i][j] == MazeElement.Player ? 1 : 0;
                    wallCounter += maze[i][j] == MazeElement.Wall ? 1 : 0;
                    if(maze[i][j]==MazeElement.Wall && wallCounter>=(maze.length*maze[0].length/2)){
                        wallCounter--;
                        maze[i][j] = MazeElement.None;
                    }
                    if (maze[i][j]==MazeElement.Player && playerCounter > 1) {
                        playerCounter--;
                    } else if (maze[i][j]==MazeElement.Exit && exitCounter > 1) {
                        exitCounter--;
                    } else{
                        j++;
                    }
                }
                i++;
            }
        }while(playerCounter<=0 || exitCounter<=0 ||!isSolvableMaze(getClone(maze)));
        return maze;
    }

    private static MazeElement[][] getClone(MazeElement[][] maze) {
        MazeElement[][] newMaze = new MazeElement[maze.length][maze[0].length];
        for(int i = 0; i<newMaze.length; ++i){
            System.arraycopy(maze[i], 0, newMaze[i], 0, newMaze[0].length);
        }
        return newMaze;
    }

    public static boolean isSolvableMaze(MazeElement[][] maze){
        try{
            String solve = solveMaze(maze);
            return solve.length()>1;
        }catch (Exception e){
            return false;
        }
    }

    public static class Pair{
        int x, y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static String solveMaze(MazeElement[][] maze) throws Exception{
        Queue<Pair> queue = new ArrayDeque<>();
        int[][] visited = new int[maze.length][maze[0].length];
        for(int i = 0; i<visited.length; ++i){
            for(int j = 0; j<visited[0].length; ++j){
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
        Pair curPos = getPlayerPosition(maze);
        visited[curPos.x][curPos.y] = 0;
        fillQueue(queue, maze, visited, curPos);
        Pair p = null;
        boolean exited = false;
        while (!queue.isEmpty()){
            p = queue.poll();
            if(p==null)
                continue;
            if(maze[p.x][p.y]==MazeElement.Exit){
                exited = true;
                break;
            }
            maze[p.x][p.y] = MazeElement.Player;
            curPos.x = p.x;
            curPos.y = p.y;
            fillQueue(queue, maze, visited, curPos);
        }
        if(exited){
            return mazeTraceback(visited, p);
        }
        return "";
    }

    private static String mazeTraceback(int[][] visited, Pair p) {
        int     upperBound = 0,
                lowerBound = visited.length-1,
                rightBound = visited[0].length-1,
                leftBound = 0;
        if(p.x-1>=upperBound && visited[p.x][p.y]-1==visited[p.x-1][p.y]){
            p.x -=1;
            return mazeTraceback(visited, p) + 's';
        }
        if(p.x+1<=lowerBound && visited[p.x][p.y]-1==visited[p.x+1][p.y]){
            p.x +=1;
            return mazeTraceback(visited, p) + 'w';
        }
        if(p.y-1>=leftBound && visited[p.x][p.y]-1==visited[p.x][p.y-1]){
            p.y -=1;
            return mazeTraceback(visited, p) + 'd';
        }
        if(p.y+1<=rightBound && visited[p.x][p.y]-1==visited[p.x][p.y+1]){
            p.y +=1;
            return mazeTraceback(visited, p) + 'a';
        }
        return "";
    }

    private static void fillQueue(Queue<Pair> queue, MazeElement[][] maze, int[][] visited, Pair curPos) {
        int x = curPos.x, y = curPos.y,upperBound = 0,
                lowerBound = maze.length-1,
                rightBound = maze[0].length-1,
                leftBound = 0;
        if(x+1<=lowerBound && maze[x+1][y]!=MazeElement.Wall && visited[x+1][y]>visited[x][y]+1){
            queue.add(new Pair(x+1, y));
            visited[x+1][y] = visited[x][y]+1;
        }
        if(x-1>=upperBound && maze[x-1][y]!=MazeElement.Wall && visited[x-1][y]>visited[x][y]+1){
            queue.add(new Pair(x-1, y));
            visited[x-1][y] = visited[x][y]+1;
        }
        if(y+1<=rightBound && maze[x][y+1]!=MazeElement.Wall && visited[x][y+1]>visited[x][y]+1){
            queue.add(new Pair(x, y+1));
            visited[x][y+1] = visited[x][y]+1;
        }
        if(y-1>=leftBound && maze[x][y-1]!=MazeElement.Wall && visited[x][y-1]>visited[x][y]+1){
            queue.add(new Pair(x, y-1));
            visited[x][y-1] = visited[x][y]+1;
        }

    }

    private static boolean move(MazeElement[][] maze, char position) throws Exception{
        boolean mazeCompleted = false;
        Pair currPlayerPos = getPlayerPosition(maze);
        int x = currPlayerPos.x,
                y = currPlayerPos.y,
                upperBound = 0,
                lowerBound = maze.length-1,
                rightBound = maze[0].length-1,
                leftBound = 0;
        switch(position){
            case 'w':
                if(x-1>=upperBound){
                    --x;
                }
                break;
            case 'a':
                if(y-1>=leftBound){
                    --y;
                }
                break;
            case 's':
                if(x+1<=lowerBound){
                    ++x;
                }
                break;
            case 'd':
                if(y+1<=rightBound){
                    ++y;
                }
                break;
            default:
                break;
        }
        if(maze[x][y]!=MazeElement.Wall) {
            if(maze[x][y] == MazeElement.Exit){
                mazeCompleted = true;
            }
            maze[currPlayerPos.x][currPlayerPos.y] = MazeElement.None;
            maze[x][y] = MazeElement.Player;
        }
        return mazeCompleted;
    }

    public static Pair getPlayerPosition(MazeElement[][] maze) throws Exception{
        for(int i = 0; i<maze.length; ++i){
            for(int j = 0; j<maze[0].length; ++j){
                if(maze[i][j]==MazeElement.Player){
                    return new Pair(i,j);
                }
            }
        }
        //impossible
        throw new Exception("Player not Found");
    }

    public static void printMaze(MazeElement[][] maze){
        for(MazeElement[] arr: maze){
            System.out.println(Arrays.toString(arr));
        }
    }
    /*
     * possono esistere più di 1 uscita?
     *   SI, anche mostri che ti seguono o portali
     */
}
