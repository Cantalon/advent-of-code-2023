import java.io.*;
import java.util.*;

public class day6 {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(r.readLine());
        st.nextToken();
        long time = Long.parseLong(st.nextToken());
        st = new StringTokenizer(r.readLine());
        st.nextToken();
        long dist = Long.parseLong(st.nextToken());
        int count = 0;
        for (long i = 0; i <= time; i++) {
            if (i * (time - i) > dist) {
                count++;
            }
        }
        pw.println(count);

        pw.close();
        r.close();
    }
}
