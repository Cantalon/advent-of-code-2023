import java.io.*;
import java.awt.*;
import java.util.*;

public class day23 {
    static int N = 141;

    static int[] xs = {-1, 1, 0, 0};
    static int[] ys = {0, 0, -1, 1};

    static char[][] map = new char[N][N];
    static boolean[][] visited = new boolean[N][N];
    static HashMap<Point, Boolean> visited2 = new HashMap<>();
    static HashMap<Point, HashMap<Point, Integer>> map2 = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        for (int line = 0; line < N; line++) {
            map[line] = r.readLine().toCharArray();
            for (int i = 0; i < N; i++) {
                if (map[line][i] == '#') {
                    visited[line][i] = true;
                }
            }
        }

        visited[0][1] = true;
        constructGraph(1, 1, 0, 1);
        for (Point p : map2.keySet()) {
            visited2.put(p, false);
        }

        pw.println(dfs(0, new Point(0, 1)));

        pw.close();
        r.close();
    }

    static int dfs(int len, Point cur) {
        if (cur.x == N - 1 && cur.y == N - 2) {
            return len;
        }
        if (visited2.get(cur)) { //already visited
            return 0;
        }
        visited2.put(cur, true);
        int ans = 0;
        for (Point p : map2.get(cur).keySet()) {
            ans = Math.max(ans, dfs(len + map2.get(cur).get(p), p));
        }

        visited2.put(cur, false);
        return ans;
    }

    static void constructGraph(int row, int col, int prevRow, int prevCol) {
        Point original = new Point(prevRow, prevCol);
        int len = 1;
        while (possiblePaths(row, col) == 1) {
            visited[row][col] = true;
            len++;
            for (int i = 0; i < 4; i++) {
                if (row + xs[i] == prevRow && col + ys[i] == prevCol) {
                    continue;
                }
                if (row + xs[i] < 0 || row + xs[i] >= N || col + ys[i] < 0 || col + ys[i] >= N ||
                        visited[row + xs[i]][col + ys[i]]) {
                    continue;
                }
                row += xs[i];
                col += ys[i];
                break;
            }
        }
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            if (map2.containsKey(new Point(row + xs[i], col + ys[i]))) {
                len++;
                row += xs[i];
                col += ys[i];
                break;
            }
        }
        Point here = new Point(row, col);
        map2.putIfAbsent(original, new HashMap<>());
        map2.putIfAbsent(here, new HashMap<>());
        map2.get(here).put(original, len);
        map2.get(original).put(here, len);

        for (int i = 0; i < 4; i++) {
            if (row + xs[i] < 0 || row + xs[i] >= N || col + ys[i] < 0 || col + ys[i] >= N ||
                    visited[row + xs[i]][col + ys[i]]) {
                continue;
            }
            constructGraph(row + xs[i], col + ys[i], row, col);
        }
    }

    static int possiblePaths(int row, int col) {
        int ans = 4;
        for (int i = 0; i < 4; i++) {
            if (row + xs[i] < 0 || row + xs[i] >= N || col + ys[i] < 0 || col + ys[i] >= N ||
                    visited[row + xs[i]][col + ys[i]]) {
                ans--;
            }
        }
        return ans;
    }
}
