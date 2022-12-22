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
        int[] row =
                {-1, 0, 0, 1};
        int[] col =
                {0, -1, 1, 0};

        boolean[][] visited = new boolean[R][C];
        int[][] step_matrix = matrix.clone();
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

    private static int[][] path(int[][] rawMatrix, int[][] bfsMatrix,  int i, int j, int x, int y)
    {
        int[][] pathMatrix = rawMatrix.clone();

        return pathMatrix;
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
//        for (int[] ints : matrix) {
//            System.out.println(Arrays.toString(ints));
//        }
        int[][] waves = bfs(matrix, 2, 7, 8, 5);
//        int[][] waves = bfs(matrix, 8, 5, 2, 7);
//        for (int[] ints : waves) {
//            System.out.println(Arrays.toString(ints));
//        }
    }
}
