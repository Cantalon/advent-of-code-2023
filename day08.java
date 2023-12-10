import java.io.*;
import java.util.*;

public class day8 {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        HashMap<String, String> right = new HashMap<>();
        HashMap<String, String> left = new HashMap<>();

        String instructions = r.readLine();
        int mod = instructions.length();
        r.readLine();

        ArrayList<String> startPos = new ArrayList<>();

        for (int line = 1; line <= 802; line++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            String from = st.nextToken();
            st.nextToken();
            String le = st.nextToken().substring(1, 4);
            String ri = st.nextToken().substring(0, 3);
            right.put(from, ri);
            left.put(from, le);
            if (from.charAt(2) == 'A') {
                startPos.add(from);
            }
        }

        int[] firstOcc = new int[startPos.size()];
        String[] ends = new String[firstOcc.length];
        for (int i = 0; i < firstOcc.length; i++) {
            int steps = 0;
            String curPos = startPos.get(i);
            while (curPos.charAt(2) != 'Z') {
                if (instructions.charAt(steps % mod) == 'R') {
                    curPos = right.get(curPos);
                } else {
                    curPos = left.get(curPos);
                }
                steps++;
            }
            ends[i] = curPos;
            firstOcc[i] = steps;
        }

        //haha print out the LCM of the numbers in firstOcc[i], that's the answer to part 2

        pw.close();
        r.close();
    }
}
