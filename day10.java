import java.io.*;
import java.util.*;

public class day10 {

    static int size = 140;

    static char[][] map = new char[size][size];
    static boolean[][] visited = new boolean[size][size];

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        int startRow = 0, startCol = 0;
        for (int line = 0; line < size; line++) {
            map[line] = r.readLine().toCharArray();
            for (int i = 0; i < size; i++) {
                if (map[line][i] == 'S') {
                    startRow = line;
                    startCol = i;
                }
            }
        }

        int prevRow = startRow;
        int prevCol = startCol;
        int curRow = startRow + 1;
        int curCol = startCol;
        int dist = 1;
        while (curRow != startRow || curCol != startCol) {
            visited[curRow][curCol] = true;
            dist++;
            char c = map[curRow][curCol];
            if (c == '|') {
                if (prevRow < curRow) {
                    prevRow = curRow;
                    curRow++;
                } else {
                    prevRow = curRow;
                    curRow--;
                }
            } else if (c == '-') {
                if (prevCol < curCol) {
                    prevCol = curCol;
                    curCol++;
                } else {
                    prevCol = curCol;
                    curCol--;
                }
            } else if (c == 'F') {
                if (curCol == prevCol) {
                    prevRow = curRow;
                    curCol++;
                } else {
                    prevCol = curCol;
                    curRow++;
                }
            } else if (c == 'J') {
                if (curCol == prevCol) {
                    prevRow = curRow;
                    curCol--;
                } else {
                    prevCol = curCol;
                    curRow--;
                }
            } else if (c == 'L') {
                if (curCol == prevCol) {
                    prevRow = curRow;
                    curCol++;
                } else {
                    prevCol = curCol;
                    curRow--;
                }
            } else if (c == '7') {
                if (curCol == prevCol) {
                    prevRow = curRow;
                    curCol--;
                } else {
                    prevCol = curCol;
                    curRow++;
                }
            }
        }
        pw.println(dist / 2); //part 1
        visited[startRow][startCol] = true;

        map[startRow][startCol] = '7';

        int ans = 0;
        for (int row = 0; row < size; row++) {
            boolean in = false;
            int state = 0;
            //|, 7, and J cause bitflips
            for (int col = 0; col < size; col++) {
                if (visited[row][col]) {
                    if (map[row][col] == '|') {
                        in = !in;
                    } else if (map[row][col] == 'L') {
                        state = 1;
                    } else if (map[row][col] == 'F') {
                        state = 2;
                    } else if (map[row][col] == 'J') {
                        if (state == 2) {
                            in = !in;
                        }
                        state = 0;
                    } else if (map[row][col] == '7') {
                        if (state == 1) {
                            in = !in;
                        }
                        state = 0;
                    }
                } else if (in) {
                    pw.println(row + " " + col);
                    ans++;
                }
            }
        }
        pw.println(ans); //part 2

        pw.close();
        r.close();
    }
}
