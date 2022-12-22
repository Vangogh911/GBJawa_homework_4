import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class node
{
    int x, y, distance;

    node(int x, int y, int dist)
    {
        this.x = x;
        this.y = y;
        this.distance = dist;
    }
}

public class lee
{
    static boolean isValid(int[][] mat, boolean[][] visited, int row, int col)
    {
        int R = mat.length;
        int C = mat[0].length;
        return ((row >= 0) && (row < R)) && ((col >= 0) && (col < C)) && (mat[row][col] == 0) && (!visited[row][col]);
    }

    private static int[][] bfs(int[][] matrix, int i, int j, int x, int y) {
        int R = matrix.length;
        int C = matrix[0].length;
        int[] row = {-1, 0, 0, 1};
        int[] col = {0, -1, 1, 0};

        boolean[][] visited = new boolean[R][C];
        int[][] step_matrix = new int[R][C];
        for(int l = 0; l < R; l++){
            System.arraycopy(matrix[l], 0, step_matrix[l], 0, C);
        }

        Queue<node> q = new LinkedList<node>();
        visited[i][j] = true;
        q.add(new node(i, j, 0));
        int minimum_distance = Integer.MAX_VALUE;
        boolean flag = true;
        while (flag)
        {
            node node = q.poll();
            i = node.x;
            j = node.y;
            int dist = node.distance;
            if (i == x && j == y)
            {
                minimum_distance = dist;
                flag = false;
            }

            for (int k = 0; k < 4; k++) {
                if (isValid(matrix, visited, i + row[k], j + col[k])) {
                    visited[i + row[k]][j + col[k]] = true;
                    node n = new node(i + row[k], j + col[k], dist + 1);
                    q.add(n);
                    step_matrix[i + row[k]][j + col[k]] = dist + 1;
                }
            }
        }

        if (minimum_distance == Integer.MAX_VALUE) {
            System.out.println("Невозможно проложить маршрут");
        } else {
            System.out.println("Кратчайший путь занимает " + minimum_distance + " шагов");
        }
        return step_matrix;
    }

    private static void path(int[][] rawMatrix, int[][] bfsMatrix,  int i, int j, int x, int y)
    {
        int[] row = {-1, 0, 0, 1};
        int[] col = {0, -1, 1, 0};
        int[][] pathMatrix = new int[rawMatrix.length][rawMatrix[0].length];
        for(int l = 0; l < rawMatrix.length; l++){
            System.arraycopy(rawMatrix[l], 0, pathMatrix[l], 0, rawMatrix[0].length);
        }
        pathMatrix[x][y] = bfsMatrix[x][y];
        while (!(i == x && j == y))
        {
            for (int k = 0; k < 4; k++) {
                if (bfsMatrix[x + row[k]][y + col[k]] == bfsMatrix[x][y] - 1){
                    x += row[k];
                    y += col[k];
                    pathMatrix[x][y] = bfsMatrix[x][y];
                    break;
                }
            }
        }
        for (int[] ints : pathMatrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static void main(String[] args)
    {
        int WALL = -1;
        int EMPT = 0;
        int[][] matrix = {
                {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
                {WALL, WALL, WALL, EMPT, EMPT, EMPT, EMPT, EMPT, EMPT, EMPT, EMPT, WALL, WALL, WALL},
                {WALL, WALL, WALL, EMPT, EMPT, EMPT, EMPT, EMPT, EMPT, EMPT, EMPT, WALL, WALL, WALL},
                {WALL, EMPT, EMPT, EMPT, EMPT, WALL, WALL, WALL, WALL, EMPT, EMPT, EMPT, EMPT, WALL},
                {WALL, EMPT, EMPT, WALL, WALL, EMPT, EMPT, EMPT, EMPT, WALL, WALL, EMPT, EMPT, WALL},
                {WALL, EMPT, EMPT, WALL, WALL, EMPT, EMPT, EMPT, EMPT, WALL, WALL, EMPT, EMPT, WALL},
                {WALL, EMPT, EMPT, EMPT, EMPT, WALL, WALL, WALL, WALL, EMPT, EMPT, EMPT, EMPT, WALL},
                {WALL, WALL, WALL, EMPT, EMPT, EMPT, EMPT, EMPT, EMPT, EMPT, EMPT, WALL, WALL, WALL},
                {WALL, WALL, WALL, EMPT, EMPT, EMPT, EMPT, EMPT, EMPT, EMPT, EMPT, WALL, WALL, WALL},
                {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL}
        };
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
        int[][] waves = bfs(matrix, 2, 7, 8, 5);
        for (int[] ints : waves) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println();
        path(matrix, waves, 2, 7, 8, 5);
    }
}
