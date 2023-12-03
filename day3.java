import java.io.*;
import java.util.*;

public class day3 {

    static int N = 140;

    public static boolean[][] visited = new boolean[N][N];
    public static boolean[][] toBeCounted = new boolean[N][N];
    public static char[][] matrix = new char[N][N];

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        for (int line = 0; line < N; line++) {
            matrix[line] = r.readLine().toCharArray();
        }

        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == '*') {
                    int countNums = 0;
                    int gearRatio = 1;
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            if (x == 0 && y == 0) {
                                continue;
                            }
                            int var = getValue(i + x, j + y);
//                            pw.print(var + " ");
                            if (var != -1) {
                                countNums++;
                                gearRatio *= var;
                            }
                        }
                    }
//                    pw.println();
//                    pw.println(countNums);
                    if (countNums == 2) {
                        total += gearRatio;
                    }
                    visited = new boolean[N][N];
                }
            }
        }
        pw.println(total);

        pw.close();
        r.close();
    }

    public static int getValue(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N || visited[x][y] || matrix[x][y] - 48 < 0 || matrix[x][y] - 48 > 9) {
            return -1;
        }
        visited[x][y] = true;
        int left = y, right = y;
        while (0 < left && !(matrix[x][left - 1] - 48 < 0 || matrix[x][left - 1] - 48 > 9)) {
            visited[x][left] = true;
            left--;
        }
        visited[x][left] = true;

        while (right < N - 1 && !(matrix[x][right + 1] - 48 < 0 || matrix[x][right + 1] - 48 > 9)) {
            visited[x][right] = true;
            right++;
        }
        visited[x][right] = true;

        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum *= 10;
            sum += matrix[x][i] - 48;
        }

        return sum;
    }

    public static void dfs(int x, int y) { //used for part 1
        if (x < 0 || x >= N || y < 0 || y >= N) {
            return;
        }
        if (visited[x][y] || (matrix[x][y] - 48 < 0 || matrix[x][y] - 48 > 9)) {
            //already came here or not a number
            return;
        }
        visited[x][y] = true;
        dfs(x, y + 1);
        dfs(x, y - 1);
    }
}
