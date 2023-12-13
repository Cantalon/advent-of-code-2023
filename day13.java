import java.io.*;
import java.util.*;

public class day13 {
    static int N = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        int count = 0;

        for (int line = 0; line < N; line++) {
            String s;
            ArrayList<String> map = new ArrayList<>();
            while (!(s = r.readLine()).equals("")) {
                map.add(s);
            }

            boolean rowOffByOne = false;
            int ans = -1;
            for (int i = 1; i < map.size(); i++) {
                int rowTemp = 0;
                for (int j = 0; j <= Math.min(i - 1, map.size() - i - 1); j++) {
                    //i - 1 - j, i + j
                    int a = rowEqual(i - 1 - j, i + j, map);
                    rowTemp += a;
                }
                if (rowTemp == 1) {
                    rowOffByOne = true;
                    ans = i;
                    break;
                }
            }
            if (rowOffByOne) {
                count += 100 * ans;
                continue;
            }

            boolean colOffByOne = false;
            for (int i = 1; i < map.get(0).length(); i++) {
                int colTemp = 0;
                for (int j = 0; j <= Math.min(i - 1, map.get(0).length() - i - 1); j++) {
                    //i - 1 - j, i + j
                    int a = colEqual(i - 1 - j, i + j, map);
                    colTemp += a;
                }
                if (colTemp == 1) {
                    colOffByOne = true;
                    ans = i;
                    break;
                }
            }
            if (colOffByOne) {
                count += ans;
            }
        }
        pw.println(count);

        r.close();
        pw.close();

    }

    static int rowEqual(int a, int b, ArrayList<String> arr) { //now returns the DIFFERENCE between strings
        int ans = 0;
        for (int i = 0; i < arr.get(a).length(); i++) {
            ans += (arr.get(a).charAt(i) == arr.get(b).charAt(i)) ? 0 : 1;
        }
        return ans;
    }

    static int colEqual(int a, int b, ArrayList<String> arr) { //now returns the DIFFERENCE between strings
        int ans = 0;
        for (String s : arr) {
            ans += (s.charAt(a) == s.charAt(b)) ? 0 : 1;
        }
        return ans;
    }
}
