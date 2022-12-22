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
    // Количество строк (R) и столбцов (C) в матрице
    static int R = 9; // y
    static int C = 13; // x

    static boolean isValid(int[][] mat, boolean[][] visited, int row, int col)
    {
        return ((row >= 0) && (row < R)) && ((col >= 0) && (col < C)) && (mat[row][col] == 1) && (!visited[row][col]);
    }

    private static void bfs(int[][] matrix, int i, int j, int x, int y)
    {
        int[] row =
                { -1, 0, 0, 1 };
        int[] col =
                { 0, -1, 1, 0 };

        boolean[][] visited = new boolean[R][C];
        Queue<node> q = new LinkedList<node>();
        visited[i][j] = true;
        q.add(new node(i, j, 0));
        int minimum_distance = Integer.MAX_VALUE;
        while (!q.isEmpty())
        {
            node node = q.poll();
            i = node.x;
            j = node.y;
            int dist = node.distance;
            if (i == x && j == y)
            {
                minimum_distance = dist;
                break;
            }

            for (int k = 0; k < 4; k++)
            {
                if (isValid(matrix, visited, i + row[k], j + col[k]))
                {
                    visited[i + row[k]][j + col[k]] = true;
                    node n = new node(i + row[k], j + col[k], dist + 1);
                    q.add(n);
                }
            }
        }

        if (minimum_distance == Integer.MAX_VALUE)
        {
            System.out.println("Невозможно проложить маршрут");
        }
        else
        {
            System.out.println("Кратчайший путь занимает " + minimum_distance + " шагов");
        }
    }

    public static void main(String[] args)
    {
        int WALL = 0;
        int EMPT = 1;
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
        bfs(matrix, 2, 7, 8, 5);
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
}