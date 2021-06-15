package Labirinto2;

import java.util.*;

public class Maze {
    private static Maze instance = null;
    private final List<Player> player;
    private final List<Monster> monster;
    private final List<Portal> portal;
    private final Entity[][] maze;

    private Maze(int height, int width) {
        maze = new Entity[height][width];
        player = new ArrayList<>();
        monster = new ArrayList<>();
        portal = new ArrayList<>();
        generateMaze();
    }

    public static Maze getInstance(int height, int width) throws Exception {
        if (height <= 0 || width <= 0) {
            throw new Exception("Parameters not valid");
        }
        if (instance == null) {
            instance = new Maze(height, width);
        }
        return instance;
    }

    private void generateMaze() {
        int i, j;
        boolean loop;
        do {
            int countP, countE, countPort, countM;
            countP = countE = countPort = countM = 0;
            player.clear();
            monster.clear();
            portal.clear();
            loop = false;
            i = 0;
            while (i < maze.length) {
                j = 0;
                while (j < maze[0].length) {
                    Entity e = getRandomEntity(new Pair<>(i, j));
                    if (e instanceof Exit) {
                        if (countE >= 1) {
                            continue;
                        }
                        ++countE;
                    } else if (e instanceof Player p) {
                        if (countP >= 1) {
                            continue;
                        }
                        player.add(p);
                        ++countP;
                    } else if (e instanceof Portal p) {
                        portal.add(p);
                        ++countPort;
                    } else if (e instanceof Monster m) {
                        if (countM > countP) {
                            continue;
                        }
                        monster.add(m);
                        ++countM;
                    }
                    maze[i][j] = e;
                    ++j;
                }
                ++i;
            }
            if (countP != 1 || countE != 1 || countM < 1 || portal.size()<=1 || countPort % 2 != 0) {
                loop = true;
            }
        } while (loop || !isSolvableMaze() || !connectPortals());
    }

    private boolean connectPortals() {
        Collections.shuffle(portal);
        for (int i = 0; i < portal.size() - 1; i += 2) {
            Portal a = portal.get(i);
            Portal b = portal.get(i + 1);
            a.setPortal(b);
            b.setPortal(a);
        }
        boolean isNull = false;
        for(Portal p : portal){
            if(p.getDestination()==null){
                isNull = true;
                break;
            }
        }
        return !isNull;
    }

    private boolean isSolvableMaze() {
        try {
            String solve = solveMaze(player.get(0).getPosition(), Exit.class);
            boolean solvable2 = false;
            for (Monster m : monster) {
                solvable2 |= solveMaze(m.getPosition(), Player.class).length() > 0;
            }
            return solve.length() > 1 && solvable2;
        } catch (Exception e) {
            return false;
        }
    }

    private void fillQueue(Queue<Pair<Integer>> queue, int[][] visited, Pair<Integer> curPos) {
        int x = curPos.x, y = curPos.y, upperBound = 0,
                lowerBound = maze.length - 1,
                rightBound = maze[0].length - 1,
                leftBound = 0;
        if (x + 1 <= lowerBound && !(maze[x + 1][y] instanceof Wall) && visited[x + 1][y] > visited[x][y] + 1) {
            queue.add(new Pair<>(x + 1, y));
            visited[x + 1][y] = visited[x][y] + 1;
        }
        if (x - 1 >= upperBound && !(maze[x - 1][y] instanceof Wall) && visited[x - 1][y] > visited[x][y] + 1) {
            queue.add(new Pair<>(x - 1, y));
            visited[x - 1][y] = visited[x][y] + 1;
        }
        if (y + 1 <= rightBound && !(maze[x][y + 1] instanceof Wall) && visited[x][y + 1] > visited[x][y] + 1) {
            queue.add(new Pair<>(x, y + 1));
            visited[x][y + 1] = visited[x][y] + 1;
        }
        if (y - 1 >= leftBound && !(maze[x][y - 1] instanceof Wall) && visited[x][y - 1] > visited[x][y] + 1) {
            queue.add(new Pair<>(x, y - 1));
            visited[x][y - 1] = visited[x][y] + 1;
        }
    }

    private String mazeTraceback(int[][] visited, Pair<Integer> p) {
        int upperBound = 0,
                lowerBound = visited.length - 1,
                rightBound = visited[0].length - 1,
                leftBound = 0;
        if (p.x - 1 >= upperBound && visited[p.x][p.y] - 1 == visited[p.x - 1][p.y]) {
            p.x -= 1;
            return mazeTraceback(visited, p) + 's';
        }
        if (p.x + 1 <= lowerBound && visited[p.x][p.y] - 1 == visited[p.x + 1][p.y]) {
            p.x += 1;
            return mazeTraceback(visited, p) + 'w';
        }
        if (p.y - 1 >= leftBound && visited[p.x][p.y] - 1 == visited[p.x][p.y - 1]) {
            p.y -= 1;
            return mazeTraceback(visited, p) + 'd';
        }
        if (p.y + 1 <= rightBound && visited[p.x][p.y] - 1 == visited[p.x][p.y + 1]) {
            p.y += 1;
            return mazeTraceback(visited, p) + 'a';
        }
        return "";
    }

    private Entity getRandomEntity(Pair<Integer> position) {
        int n = new Random().nextInt(100);
        if (n > 80) {
            return new Wall();
        } else if (n > 40) {
            return new None();
        } else if (n > 30) {
            return new Portal(position);
        } else if (n > 20) {
            return new Player(this, position);
        } else if (n > 10) {
            return new Monster(this, position);
        }
        return new Exit();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Entity[] row : maze) {
            sb.append(Arrays.toString(row)).append('\n');
        }
        return sb.toString();
    }

    public boolean move(char position, Pair<Integer> currPlayerPos, Class<? extends Entity> exitCondition) {
        boolean mazeCompleted = false;
        int x = currPlayerPos.x,
                y = currPlayerPos.y,
                upperBound = 0,
                lowerBound = maze.length - 1,
                rightBound = maze[0].length - 1,
                leftBound = 0;
        switch (position) {
            case 'w':
                if (x - 1 >= upperBound) {
                    --x;
                }
                break;
            case 'a':
                if (y - 1 >= leftBound) {
                    --y;
                }
                break;
            case 's':
                if (x + 1 <= lowerBound) {
                    ++x;
                }
                break;
            case 'd':
                if (y + 1 <= rightBound) {
                    ++y;
                }
                break;
            default:
                break;
        }
        if (maze[x][y].getClass().equals(exitCondition)) {
            mazeCompleted = true;
        } else if (maze[x][y] instanceof Portal p) {
            Pair<Integer> pos = p.getDestination().getPosition();
            if (pos.x - 1 >= upperBound && !(maze[pos.x-1][pos.y] instanceof Wall)) {
                --pos.x;
            } else if (pos.x + 1 <= lowerBound && !(maze[pos.x+1][pos.y] instanceof Wall)) {
                ++pos.x;
            } else if (pos.y - 1 >= leftBound && !(maze[pos.x][pos.y-1] instanceof Wall)) {
                --pos.y;
            } else if (pos.y + 1 <= rightBound && !(maze[pos.x][pos.y+1] instanceof Wall)) {
                ++pos.y;
            }else{
                return mazeCompleted;
            }
            maze[pos.x][pos.y] = maze[currPlayerPos.x][currPlayerPos.y];
            if (maze[x][y] instanceof Player pla) {
                pla.setPosition(pos);
            } else if (maze[x][y] instanceof Monster m) {
                m.setPosition(pos);
            }
            maze[currPlayerPos.x][currPlayerPos.y] = new None();
        } else if (maze[x][y] instanceof None) {
            maze[x][y] = maze[currPlayerPos.x][currPlayerPos.y];
            maze[currPlayerPos.x][currPlayerPos.y] = new None();
            if (maze[x][y] instanceof Player p) {
                p.setPosition(new Pair<>(x, y));
            } else if (maze[x][y] instanceof Monster m) {
                m.setPosition(new Pair<>(x, y));
            }
        }
        return mazeCompleted;
    }

    public String solveMaze(Pair<Integer> playerPosition, Class<? extends Entity> exitCondition) {
        Entity[][] clone = getCloneMaze();
        Pair<Integer> curPos = new Pair<>(playerPosition.x, playerPosition.y);
        Queue<Pair<Integer>> queue = new ArrayDeque<>();
        int[][] visited = new int[clone.length][clone[0].length];
        for (int i = 0; i < visited.length; ++i) {
            for (int j = 0; j < visited[0].length; ++j) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
        visited[curPos.x][curPos.y] = 0;
        fillQueue(queue, visited, curPos);
        Pair<Integer> p = null;
        boolean exited = false;
        while (!queue.isEmpty()) {
            p = queue.poll();
            if (p == null)
                continue;
            if (clone[p.x][p.y].getClass().equals(exitCondition)) {
                exited = true;
                break;
            }
            if(clone[p.x][p.y] == null){
                continue;
            }
            if (clone[p.x][p.y] instanceof Portal portal) {
                portal = portal.getDestination();
                if(portal == null){
                    continue;
                }
                Pair<Integer> pos = portal.getPosition();
                int upperBound = 0, lowerBound = maze.length - 1,
                        rightBound = maze[0].length - 1, leftBound = 0;
                if (pos.x - 1 >= upperBound && !(maze[pos.x-1][pos.y] instanceof Wall)) {
                    --pos.x;
                } else if (pos.x + 1 <= lowerBound && !(maze[pos.x+1][pos.y] instanceof Wall)) {
                    ++pos.x;
                } else if (pos.y - 1 >= leftBound && !(maze[pos.x][pos.y-1] instanceof Wall)) {
                    --pos.y;
                } else if (pos.y + 1 <= rightBound && !(maze[pos.x][pos.y+1] instanceof Wall)) {
                    ++pos.y;
                }else{
                    continue;
                }
                clone[pos.x][pos.y] = clone[curPos.x][curPos.y];
                fillQueue(queue, visited, pos);
            }
            clone[p.x][p.y] = clone[curPos.x][curPos.y];
            curPos.x = p.x;
            curPos.y = p.y;
            fillQueue(queue, visited, curPos);
        }
        if (exited) {
            return mazeTraceback(visited, p);
        }
        return "";
    }

    private Entity[][] getCloneMaze() {
        Entity[][] tmp = new Entity[maze.length][maze[0].length];
        for (int i = 0; i < tmp.length; ++i) {
            for (int j = 0; j < tmp[0].length; ++j) {
                tmp[i][j] = (Entity) maze[i][j].clone();
            }
        }
        return tmp;
    }

    public Player getPlayer() {
        return player.get(0);
    }

    public List<Monster> getMonster() {
        return monster;
    }
}
