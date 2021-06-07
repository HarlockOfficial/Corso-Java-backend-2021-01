import java.util.ArrayDeque;
import java.util.Queue;
//TODO try DFS
public class MaxIslandArea {
    public static void main(String[] args) {
        int[][] map = new int[][]{
                {0,1,1,0,0,0,0,0,0},
                {0,0,1,1,0,0,1,1,0},
                {0,1,1,1,0,1,1,1,0},
                {0,1,1,0,0,1,1,1,0},
                {0,0,0,1,0,0,0,0,0},
        };
        System.out.println(findMaxArea(map));
    }

    private static int findMaxArea(int[][] grid) {
        int max = 0;
        for(int i = 0; i<grid.length; ++i){
            for(int j = 0; j<grid[0].length; ++j){
                if(grid[i][j]!=0){
                    int tmp = getIslandArea(grid, i, j);
                    max = Math.max(max, tmp);
                }
            }
        }
        return max;
    }
    static class Pair{
        int x, y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    private static int getIslandArea(int[][] map, int x, int y) {
        int sum = 0;
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(x, y));

        while(!queue.isEmpty()){
            Pair elem = queue.poll();
            x = elem.x;
            y = elem.y;
            sum += map[x][y];
            map[x][y] = 0;
            if(x-1>=0 && map[x-1][y]>0){
                queue.add(new Pair(x-1,y));
            }
            if(x+1<map.length && map[x+1][y]>0){
                queue.add(new Pair(x+1,y));
            }
            if(y-1>=0 && map[x][y-1]>0){
                queue.add(new Pair(x,y-1));
            }
            if(y+1<map[0].length && map[x][y+1]>0){
                queue.add(new Pair(x,y+1));
            }
        }
        return sum;
    }
}
