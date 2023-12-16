import java.io.*;
import java.util.*;

public class day16 {
    static int N = 110;
    static char[][] map;
    static boolean[][][] beam;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        map = new char[N][N];
        //0R, 1U, 2L, 3D

        for (int line = 0; line < N; line++) {
            map[line] = r.readLine().toCharArray();
        }

        int MAX_ANS = 0;
        for (int s = 0; s < N; s++) {
            for (int dir = 0; dir < 4; dir++) {
                beam = new boolean[4][N][N];
                if (dir == 0) {
                    search(0, s, 0);
                } else if (dir == 1) {
                    search(1, N - 1, s);
                } else if (dir == 2) {
                    search(2, s, N - 1);
                } else {
                    search(3, 0, s);
                }

                int ans = 0;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (beam[0][i][j] || beam[1][i][j] || beam[2][i][j] || beam[3][i][j]) {
                            ans++;
                        }
                    }
                }
                MAX_ANS = Math.max(MAX_ANS, ans);
            }
        }
        pw.println(MAX_ANS);

        r.close();
        pw.close();
    }

    public static void search(int dir, int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            return;
        }
        if (beam[dir][row][col]) {
            return;
        }
        beam[dir][row][col] = true;

        dir = getNewDir(dir, row, col);
        if (dir == 0) {
            search(dir, row, col + 1);
        } else if (dir == 1) {
            search(dir, row - 1, col);
        } else if (dir == 2) {
            search(dir, row, col - 1);
        } else if (dir == 3) {
            search(dir, row + 1, col);
        } else { //beam split
            if (map[row][col] == '|') {
                search(1, row - 1, col);
                search(3, row + 1, col);
            } else { // == '-'
                search(0, row, col + 1);
                search(2, row, col - 1);
            }
        }
    }

    public static int getNewDir(int prevDir, int row, int col) {
        if (map[row][col] == '.') {
            return prevDir;
        } else if (map[row][col] == '\\') {
            return 3 - prevDir;
        } else if (map[row][col] == '/') {
            if (prevDir % 2 == 1) {
                return prevDir - 1;
            }
            return prevDir + 1;
        } else if (map[row][col] == '|') {
            if (prevDir % 2 == 1) {
                return prevDir;
            }
            return -1;
        } else { // == '-'
            if (prevDir % 2 == 0) {
                return prevDir;
            }
            return -1;
        }
    }
}
