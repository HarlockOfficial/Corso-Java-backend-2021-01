import java.util.*;

public class Maze {
    private static final Scanner sc = new Scanner(System.in);

    public static class Pair{
        int x, y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public enum MazeElement{
        Wall ('W'),
        Exit ('E'),
        Player ('P'),
        None ('-'),
        Monster ('M');

        private final char c;
        private static final Random generator = new Random();
        MazeElement(char c){
            this.c = c;
        }

        @Override
        public String toString() {
            return c+"";
        }
        public static MazeElement getRandom(){
            int n = generator.nextInt(100);
            if(n>70){
                return Wall;
            }else if(n>30){
                return None;
            }else if(n>20){
                return Player;
            }else if(n>10){
                return Monster;
            }
            return Exit;
        }
    }

    public static void main(String[] args) {
        System.out.print("Insert row count > ");
        int row = Integer.parseInt(sc.nextLine());
        System.out.print("Insert column count > ");
        int col = Integer.parseInt(sc.nextLine());
        System.out.println("Creating a cool map may require some time, please wait");
        MazeElement[][] maze = getMaze(row, col);
        boolean mazeCompleted1 = false;
        boolean mazeCompleted2 = false;
        while (!mazeCompleted1 && !mazeCompleted2) {
            try {
                printMaze(maze);
                char movement = getMovement();
                mazeCompleted1 = move(maze, movement, getPlayerPosition(maze), MazeElement.Exit);
                mazeCompleted2 = moveMonster(maze);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.err.println(Arrays.toString(e.getStackTrace()));
            }
        }
        printMaze(maze);
        if(mazeCompleted1)
            System.out.println("You've completed the game");
    }

    private static char getMovement(){
        String s = sc.nextLine();
        return s.length()>0?s.charAt(0):'\0';
    }

    public static MazeElement[][] getMaze(int row, int col) {
        int exitCounter, playerCounter, wallCounter, monsterCounter, i, j;
        MazeElement[][] maze = new MazeElement[row][col];
        do {
            exitCounter = 0;
            playerCounter = 0;
            wallCounter = 0;
            monsterCounter = 0;
            i = 0;
            while (i < row) {
                j = 0;
                while (j < col) {
                    maze[i][j] = MazeElement.getRandom();
                    exitCounter += maze[i][j] == MazeElement.Exit ? 1 : 0;
                    playerCounter += maze[i][j] == MazeElement.Player ? 1 : 0;
                    wallCounter += maze[i][j] == MazeElement.Wall ? 1 : 0;
                    monsterCounter += maze[i][j] == MazeElement.Monster ? 1 : 0;
                    if(maze[i][j]==MazeElement.Wall && wallCounter>=(maze.length*maze[0].length/2)){
                        --wallCounter;
                        maze[i][j] = MazeElement.None;
                    }
                    if (maze[i][j]==MazeElement.Player && playerCounter > 1) {
                        --playerCounter;
                    } else if(maze[i][j]==MazeElement.Monster && monsterCounter>wallCounter/2){
                        --monsterCounter;
                    }else if (maze[i][j]==MazeElement.Exit && exitCounter > 1) {
                        --exitCounter;
                    } else{
                        ++j;
                    }
                }
                ++i;
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
            String solve = solveMaze(maze, getPlayerPosition(maze), MazeElement.Exit);
            boolean solvable2 = false;
            List<Pair> monsterPos = getMonsterPosition(maze);
            if(monsterPos!=null) {
                for (Pair p :monsterPos) {
                    solvable2 |= solveMaze(maze, p, MazeElement.Player).length()>0;
                }
            }
            return solve.length()>1 && solvable2;
        }catch (Exception e){
            return false;
        }
    }

    public static String solveMaze(MazeElement[][] maze, Pair origPos, MazeElement exitCondition) {
        Pair curPos = new Pair(origPos.x, origPos.y);
        Queue<Pair> queue = new ArrayDeque<>();
        int[][] visited = new int[maze.length][maze[0].length];
        for(int i = 0; i<visited.length; ++i){
            for(int j = 0; j<visited[0].length; ++j){
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
        visited[curPos.x][curPos.y] = 0;
        fillQueue(queue, maze, visited, curPos);
        Pair p = null;
        boolean exited = false;
        while (!queue.isEmpty()){
            p = queue.poll();
            if(p==null)
                continue;
            if(maze[p.x][p.y]==exitCondition){
                exited = true;
                break;
            }
            maze[p.x][p.y] = maze[curPos.x][curPos.y];
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

    private static boolean move(MazeElement[][] maze, char position, Pair currPlayerPos, MazeElement exitCondition) {
        boolean mazeCompleted = false;
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
        if(maze[x][y]==MazeElement.None || maze[x][y] == exitCondition) {
            if(maze[x][y] == exitCondition){
                mazeCompleted = true;
            }
            if(maze[x][y] != MazeElement.Exit){
                maze[x][y] = maze[currPlayerPos.x][currPlayerPos.y];
            }
            maze[currPlayerPos.x][currPlayerPos.y] = MazeElement.None;
        }
        return mazeCompleted;
    }

    private static boolean moveMonster(MazeElement[][] maze) {
        List<Pair> currMonsterPos = getMonsterPosition(maze);
        if(currMonsterPos == null){
            return false;
        }
        boolean solved = false;
        for(Pair monster: currMonsterPos){
            String pos = solveMaze(getClone(maze), monster, MazeElement.Player);
            char position = pos.length()>0?pos.charAt(0):'q';
            solved |= move(maze, position, monster, MazeElement.Player);
        }
        if(solved){
            System.out.println("A monster killed you, game ends");
        }
        return solved;
    }

    public static List<Pair> getMonsterPosition(MazeElement[][] maze){
        try {
            return getPosition(maze, MazeElement.Monster);
        }catch (Exception e){
            return null;
        }
    }

    public static Pair getPlayerPosition(MazeElement[][] maze) throws Exception{
        //there is only one player
        return getPosition(maze, MazeElement.Player).get(0);
    }

    private static List<Pair> getPosition(MazeElement[][] maze, MazeElement object) throws Exception{
        List<Pair> arr = new ArrayList<>();
        for(int i = 0; i<maze.length; ++i){
            for(int j = 0; j<maze[0].length; ++j){
                if(maze[i][j]==object){
                    arr.add(new Pair(i,j));
                }
            }
        }
        if(arr.size()>0){
            return arr;
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
     *   SI, anche portali
     */
}
