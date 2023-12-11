import java.io.*;
import java.util.*;

public class day11 {

    static int N = 140;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        ArrayList<ArrayList<Character>> map = new ArrayList<>();

        for (int line = 0; line < N; line++) {
            map.add(new ArrayList<>());
        }

        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> cols = new ArrayList<>();

        HashSet<Integer> emptyRows = new HashSet<>();
        HashSet<Integer> emptyCols = new HashSet<>();

        for (int i = 0; i < N; i++) {
            char[] c = r.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map.get(i).add(c[j]);
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            boolean noGalaxies = true;
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j) == '#') {
                    noGalaxies = false;
                    break;
                }
            }
            if (noGalaxies) {
                emptyCols.add(i);
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            boolean noGalaxies = true;
            for (ArrayList<Character> characterArrayList : map) {
                if (characterArrayList.get(i) == '#') {
                    noGalaxies = false;
                    break;
                }
            }
            if (noGalaxies) {
                emptyRows.add(i);
            }
        }//expanded

        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(0).size(); j++) {
                if (map.get(i).get(j) == '#') {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        long total = 0;
        for (int g1 = 0; g1 < rows.size(); g1++) {
             for (int g2 = g1 + 1; g2 < rows.size(); g2++) {
                 int ans = 0;
                 for (int row = rows.get(g1); row < rows.get(g2); row++) {
                     if (emptyCols.contains(row)) {
                         ans += 1000000; // set this value (and others) to 2 to get the ans to part 1
                     } else {
                         ans += 1;
                     }
                 }
                 for (int row = rows.get(g2); row < rows.get(g1); row++) {
                     if (emptyCols.contains(row)) {
                         ans += 1000000;
                     } else {
                         ans += 1;
                     }
                 }
                 for (int col = cols.get(g1); col < cols.get(g2); col++) {
                     if (emptyRows.contains(col)) {
                         ans += 1000000;
                     } else {
                         ans += 1;
                     }
                 }
                 for (int col = cols.get(g2); col < cols.get(g1); col++) {
                     if (emptyRows.contains(col)) {
                         ans += 1000000;
                     } else {
                         ans += 1;
                     }
                 }
                 total += ans;
             }
        }
        pw.println(total);

        pw.close();
        r.close();
    }
}
