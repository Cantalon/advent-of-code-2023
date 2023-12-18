import java.io.*;
import java.util.*;

public class day18 {
    static int N = 720;

    static int[] xc = {+1, 0, -1, 0}; //col
    static int[] yc = {0, +1, 0, -1}; //row

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        ArrayList<Long> xs = new ArrayList<>();
        ArrayList<Long> ys = new ArrayList<>();

        long perimeter = 0;
        long curX = 0; xs.add(curX);
        long curY = 0; ys.add(curY);

        for (int line = 0; line < N; line++) {
            String hex = r.readLine().split(" ")[2].substring(2);
            long a = parseHex(hex.substring(0, 5));
            int b = Integer.parseInt(hex.substring(5, 6));

            perimeter += a;
            curX += xc[b] * a;
            curY += yc[b] * a;
            xs.add(curX); ys.add(curY);
        }

        long sum1 = 0;
        long sum2 = 0;
        for (int i = 0; i < xs.size() - 1; i++) {
            //i, i + 1
            sum1 += xs.get(i) * ys.get(i + 1);
            sum2 += xs.get(i + 1) * ys.get(i);
        }

        pw.println(Math.abs(sum1 - sum2) / 2 + perimeter / 2 + 1);

        r.close();
        pw.close();
    }

    static long parseHex(String s) {
        long ans = 0;
        for (char c : s.toCharArray()) {
            ans *= 16;
            if (c == 'f') {
                ans += 15;
            } else if (c == 'e') {
                ans += 14;
            } else if (c == 'd') {
                ans += 13;
            } else if (c == 'c') {
                ans += 12;
            } else if (c == 'b') {
                ans += 11;
            } else if (c == 'a') {
                ans += 10;
            } else {
                ans += c - 48;
            }
        }
        return ans;
    }
}
