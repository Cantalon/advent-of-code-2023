import java.io.*;
import java.util.*;

public class day4 {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);
        
        long[] arr = new long[209];
        for (int i = 1; i <= 208; i++) {
            arr[i] = 1;
        }
        for (int line = 1; line <= 208; line++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            st.nextToken(); st.nextToken();
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < 10; i++) {
                set.add(Integer.parseInt(st.nextToken()));
            }
            st.nextToken();
            int ct = 0;
            while (st.hasMoreTokens()) {
                if (set.contains(Integer.parseInt(st.nextToken()))) {
                    ct++;
                }
            }
            for (int i = 1; i <= ct; i++) {
                arr[line + i] += arr[line];
            }
        }
        long count = 0;
        for (int i = 1; i <= 208; i++) {
            pw.print(arr[i] + " ");
            count += arr[i];
        }pw.println();
        pw.println(count);
        pw.close();
        r.close();
    }
}
