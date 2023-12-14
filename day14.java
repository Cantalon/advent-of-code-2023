import java.io.*;
import java.util.*;

public class day14 {
    static int N = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        HashMap<String, Integer> strings = new HashMap<>();

        char[][] map = new char[N][N];
        for (int line = 0; line < 100; line++) {
            map[line] = r.readLine().toCharArray();
        }

        int counter = 0;
        String s = mapToString(map);
        while (!strings.containsKey(s)) {
            strings.put(s, counter);
            cycle(map);
            counter++;
            s = mapToString(map);
        }

        int mod = counter - strings.get(s);
        int rem = strings.get(s);

        int amt = rem + (1000000000 - rem) % mod;
        for (String str : strings.keySet()) {
            if (strings.get(str) == amt) {
                for (int i = 0; i < N; i++) {
                    map[i] = str.substring(N * i, N * i + N).toCharArray();
                }
                break;
            }
        }

        int ans = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] == 'O') {
                    ans += 100 - i;
                }
            }
        }
        pw.println(ans);

        r.close();
        pw.close();
    }

    static void cycle(char[][] map) {
        for (int turn = 0; turn < 100; turn++) {
            for (int i = 0; i < 100; i++) {
                for (int j = 1; j < 100; j++) {
                    //j - 1, j
                    if (map[j][i] == 'O' && map[j - 1][i] == '.') {
                        map[j][i] = '.';
                        map[j - 1][i] = 'O';
                    }
                }
            }
        } //north
        for (int turn = 0; turn < 100; turn++) {
            for (int i = 0; i < 100; i++) {
                for (int j = 1; j < 100; j++) {
                    //j - 1, j
                    if (map[i][j] == 'O' && map[i][j - 1] == '.') {
                        map[i][j] = '.';
                        map[i][j - 1] = 'O';
                    }
                }
            }
        } //west
        for (int turn = 0; turn < 100; turn++) {
            for (int i = 0; i < 100; i++) {
                for (int j = 1; j < 100; j++) {
                    //j - 1, j
                    if (map[j][i] == '.' && map[j - 1][i] == 'O') {
                        map[j][i] = 'O';
                        map[j - 1][i] = '.';
                    }
                }
            }
        } //south
        for (int turn = 0; turn < 100; turn++) {
            for (int i = 0; i < 100; i++) {
                for (int j = 1; j < 100; j++) {
                    //j - 1, j
                    if (map[i][j] == '.' && map[i][j - 1] == 'O') {
                        map[i][j] = 'O';
                        map[i][j - 1] = '.';
                    }
                }
            }
        } //south
    }

    static String mapToString(char[][] arr) {
        StringBuilder s = new StringBuilder();
        for (char[] c : arr) {
            s.append(c);
        }
        return s.toString();
    }
}
