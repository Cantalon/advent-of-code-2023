import java.io.*;
import java.util.*;

public class day17 {
    static int N = 141;
    static int[][] map = new int[N][N];
    static int[][][][] weightToNextNode = new int[N][N][2][14]; //row, col, 0U/D and 1L/R, dist(1, 2, 3)

    static Nd[][][] graph = new Nd[N][N][2];
    static ArrayList<Nd> unvisited = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        for (int line = 0; line < N; line++) {
            String s = r.readLine();
            for (int col = 0; col < N; col++) {
                map[line][col] = Integer.parseInt(s.substring(col, col + 1));
            }
        } //read input

        for (int[][][] cube : weightToNextNode) {
            for (int[][] grid : cube) {
                for (int[] arr : grid) {
                    Arrays.fill(arr, -1);
                }
            }
        }
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (row >= 4) {
                    int sum = map[row - 1][col] + map[row - 2][col] + map[row - 3][col];
                    for (int i = 4; i <= 10; i++) {
                        if (row >= i) {
                            sum += map[row - i][col];
                            weightToNextNode[row][col][0][10 - i] = sum;
                        } else {
                            break;
                        }
                    }
                }
                if (row <= N - 4) {
                    int sum = map[row + 1][col] + map[row + 2][col] + map[row + 3][col];
                    for (int i = 4; i <= 10; i++) {
                        if (row < N - i) {
                            sum += map[row + i][col];
                            weightToNextNode[row][col][0][i + 3] = sum;
                        } else {
                            break;
                        }
                    }
                }

                if (col >= 4) {
                    int sum = map[row][col - 1] + map[row][col - 2] + map[row][col - 3];
                    for (int i = 4; i <= 10; i++) {
                        if (col >= i) {
                            sum += map[row][col - i];
                            weightToNextNode[row][col][1][10 - i] = sum;
                        } else {
                            break;
                        }
                    }
                }
                if (col <= N - 4) {
                    int sum = map[row][col + 1] + map[row][col + 2] + map[row][col + 3];
                    for (int i = 4; i <= 10; i++) {
                        if (col < N - i) {
                            sum += map[row][col + i];
                            weightToNextNode[row][col][1][i + 3] = sum;
                        } else {
                            break;
                        }
                    }
                }
            }
        } //initialize edge weights

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (row == 0 && col == 0) {
                    graph[row][col][0] = new Nd(row, col, 0, 0);
                    graph[row][col][1] = new Nd(row, col, 1, 0);
                } else {
                    graph[row][col][0] = new Nd(row, col, 0, Integer.MAX_VALUE);
                    graph[row][col][1] = new Nd(row, col, 1, Integer.MAX_VALUE);
                }
                unvisited.add(graph[row][col][0]);
                unvisited.add(graph[row][col][1]);
            }
        } //initialize Dijkstra's

        while (!unvisited.isEmpty()) {
            Nd cur = unvisited.remove(0);
            cur.visited = true;

            if (cur.id == 1) {//search LR
                for (int i = 0; i < 14; i++) {
                    if (weightToNextNode[cur.row][cur.col][cur.id][i] == -1) {
                        continue;
                    }
                    if (i < 7) {
                        if (graph[cur.row][cur.col - 10 + i][0].visited) {
                            continue;
                        }
                        graph[cur.row][cur.col - 10 + i][0].value = Math.min(graph[cur.row][cur.col - 10 + i][0].value, cur.value + weightToNextNode[cur.row][cur.col][cur.id][i]);
                    } else {
                        if (graph[cur.row][cur.col + i - 3][0].visited) {
                            continue;
                        }
                        graph[cur.row][cur.col + i - 3][0].value = Math.min(graph[cur.row][cur.col + i - 3][0].value, cur.value + weightToNextNode[cur.row][cur.col][cur.id][i]);
                    }
                }
            } else {//search UD
                for (int i = 0; i < 14; i++) {
                    if (weightToNextNode[cur.row][cur.col][cur.id][i] != -1) {
                        if (i < 7) {
                            if (graph[cur.row - 10 + i][cur.col][1].visited) {
                                continue;
                            }
                            graph[cur.row - 10 + i][cur.col][1].value = Math.min(graph[cur.row - 10 + i][cur.col][1].value, cur.value + weightToNextNode[cur.row][cur.col][cur.id][i]);
                        } else {
                            if (graph[cur.row + i - 3][cur.col][1].visited) {
                                continue;
                            }
                            graph[cur.row + i - 3][cur.col][1].value = Math.min(graph[cur.row + i - 3][cur.col][1].value, cur.value + weightToNextNode[cur.row][cur.col][cur.id][i]);
                        }
                    }
                }
            }
            Collections.sort(unvisited);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                pw.print("(" + graph[i][j][0].value + "," + graph[i][j][1].value + ") ");
            } pw.println();
        } pw.println();

        pw.println(Math.min(graph[N - 1][N - 1][0].value, graph[N - 1][N - 1][1].value));

        r.close();
        pw.close();
    }

    static class Nd implements Comparable<Nd> { //first three vars for node pos
        int row, col, id;
        int value;
        boolean visited;

        public Nd(int r, int c, int i, int v) {
            row = r;
            col = c;
            id = i;

            visited = false;
            value = v;
        }

        @Override
        public int compareTo(Nd o) {
            return Integer.compare(value, o.value);
        }
    }
}
