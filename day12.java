import java.io.*;
import java.util.*;

public class day12 {

    static int N = 1000;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        long ans = 0;
        for (int line = 0; line < N; line++) {
            //System.out.println(line); //check speed of algo
            StringTokenizer st = new StringTokenizer(r.readLine());
            String temp = st.nextToken();
            String possible = temp + "?" + temp + "?" + temp + "?" + temp + "?" + temp + "...";
            temp = st.nextToken();
            st = new StringTokenizer(temp, ",");
            ArrayList<Integer> arrangements = new ArrayList<>();
            while (st.hasMoreTokens()) {
                arrangements.add(Integer.parseInt(st.nextToken()));
            }
            int store = arrangements.size();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < store; j++) {
                    arrangements.add(arrangements.get(j));
                }
            }
            //pw.println(possible);
            //pw.println(arrangements);
            dp = new long[arrangements.size() + 1][possible.length() + 1];
            for (long[] l : dp) {
                Arrays.fill(l, -1);
            }
            pw.println(solve(possible, arrangements));
            ans += solve(possible, arrangements);
        }
        pw.println(ans);

        pw.close();
        r.close();
    }

    public static long solve(String match, ArrayList<Integer> arr) {
        if (dp[arr.size()][match.length()] != -1) {
            return dp[arr.size()][match.length()];
        }
        // cur and match must have the same len
        if (arr.size() == 0 && clear(match)) {
            //System.out.println(" solution found!");
            dp[arr.size()][match.length()] = 1;
            return 1;
        } else if (arr.size() == 0) {
            dp[arr.size()][match.length()] = 0;
            return 0;
        }

        long ans = 0;
        int first = arr.remove(0);
        for (int i = 0; i <= match.length() - first; i++) {
            if (i - 1 >= 0 && match.charAt(i - 1) == '#') {
                break;
            }
            if (fits(i, i + first - 1, match)) {
                if ((i - 1 < 0 || match.charAt(i - 1) != '#') && (i + first >= match.length() || match.charAt(i + first) != '#')) {
                    //place!
                    //System.out.println("iter " + arr.size() + ":  " + i + "!, ");
                    ans += solve(match.substring(i + first + 1), arr);
                }
            } //else {
                //System.out.println("iter " + arr.size() + ":  " + i + ",  no solution");
            //}
        }
        arr.add(0, first);
        dp[arr.size()][match.length()] = ans;
        return ans;
    }

    public static boolean clear(String test) {
        for (char c : test.toCharArray()) {
            if (c == '#') {
                return false;
            }
        }
        return true;
    }

    public static boolean fits(int a, int b, String test) {
        for (int i = a; i <= b; i++) {
            if (test.charAt(i) == '.') {
                return false;
            }
        }
        return true;
    }
}
