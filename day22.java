import java.io.*;
import java.util.*;

public class day22 {
    static int N = 1249;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        Brick[] bricks = new Brick[N];
        int[][] grid = new int[1000][1000]; //actual coordinates
        int[][] brickNum = new int[1000][1000];

        for (int line = 0; line < N; line++) {
            String[] input = r.readLine().split("~");
            String[] c1 = input[0].split(",");
            String[] c2 = input[1].split(",");
            bricks[line] = new Brick(Integer.parseInt(c1[0]), Integer.parseInt(c1[1]), Integer.parseInt(c1[2]),
                    Integer.parseInt(c2[0]), Integer.parseInt(c2[1]), Integer.parseInt(c2[2]));
        }

        HashSet<Integer> possible = new HashSet<>();
        ArrayList<HashSet<Integer>> supports = new ArrayList<>(); //1st is the base, 2nd is the top
        ArrayList<HashSet<Integer>> backward = new ArrayList<>();
        backward.add(new HashSet<>());
        supports.add(new HashSet<>());
        for (int i = 1; i <= N; i++) {
            supports.add(new HashSet<>());
            backward.add(new HashSet<>());
            possible.add(i);
        }

        Arrays.sort(bricks);
        for (int in = 0; in < bricks.length; in++) {
            int curBrickNum = in + 1;

            int maxHeight = 0;
            Brick b = bricks[in];
            for (int x = Math.min(b.x1, b.x2); x <= Math.max(b.x1, b.x2); x++) {
                for (int y = Math.min(b.y1, b.y2); y <= Math.max(b.y1, b.y2); y++) {
                    maxHeight = Math.max(maxHeight, grid[x][y]);
                }
            }//determine max height

            if (maxHeight != 0) {
                HashSet<Integer> all = new HashSet<>();
                for (int x = Math.min(b.x1, b.x2); x <= Math.max(b.x1, b.x2); x++) {
                    for (int y = Math.min(b.y1, b.y2); y <= Math.max(b.y1, b.y2); y++) {
                        if (grid[x][y] == maxHeight) {
                            all.add(brickNum[x][y]);
                        }
                    }
                }
                for (int i : all) {
                    supports.get(i).add(curBrickNum);
                    backward.get(curBrickNum).add(i);
                }
                if (all.size() == 1) {
                    possible.removeAll(all);
                }
            }
            for (int x = Math.min(b.x1, b.x2); x <= Math.max(b.x1, b.x2); x++) {
                for (int y = Math.min(b.y1, b.y2); y <= Math.max(b.y1, b.y2); y++) {
                    grid[x][y] = maxHeight + Math.abs(b.z1 - b.z2) + 1;
                    brickNum[x][y] = curBrickNum;
                }
            }//update grid
        }

        pw.println(possible.size());

        int total = 0;
        for (int start = 1; start <= N; start++) {
            boolean[] disintegrated = new boolean[N + 1];
            disintegrated[start] = true;

            ArrayList<Integer> front = new ArrayList<>(supports.get(start));

            while (!front.isEmpty()) {
                ArrayList<Integer> temp = new ArrayList<>(); //list of actual ones

                for (int cur : front) {
                    boolean supported = false;
                    for (int back : backward.get(cur)) {
                        if (!disintegrated[back]) {
                            supported = true;
                            break;
                        }
                    }
                    if (!supported) {
                        temp.add(cur);
                        disintegrated[cur] = true;
                    }
                }

                front.clear();
                for (int cur : temp) {
                    front.addAll(supports.get(cur));
                }
            }
            
            for (boolean b : disintegrated) {
                if (b) {total++;}
            } total--;
        }
        pw.println(total);

        pw.close();
        r.close();
    }

    public static class Brick implements Comparable<Brick> {
        int x1, x2, y1, y2, z1, z2;

        public Brick(int a, int b, int c, int d, int e, int f) {
            x1 = a; y1 = b; z1 = c;
            x2 = d; y2 = e; z2 = f;
        }

        @Override
        public int compareTo(Brick b) {
            return Integer.compare(Math.min(z1, z2), Math.min(b.z1, b.z2));
        }
    }
}
