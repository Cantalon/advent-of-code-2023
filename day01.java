import java.io.*;
import java.util.*;

public class day1 {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        String[] nums = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        int sum = 0;

        for (int i = 0; i < 1000; i++) {
            String s = r.readLine();

            //get rid of this below and you will get part one answer
            int first_occ = Integer.MAX_VALUE;
            int amt = 0;
            for (int j = 0; j < 9; j++) {
                int pos = s.indexOf(nums[j]);
                if (pos == -1) {
                    pos = Integer.MAX_VALUE;
                }
                if (pos < first_occ) {
                    first_occ = pos;
                    amt = j + 1;
                }
            }
            if (first_occ != Integer.MAX_VALUE) {
                s = s.substring(0, first_occ) + amt + s.substring(first_occ + 1);
            }

            int last_occ = -1;
            amt = 0;
            for (int j = 0; j < 9; j++) {
                int pos = s.lastIndexOf(nums[j]);
                if (pos > last_occ) {
                    last_occ = pos;
                    amt = j + 1;
                }
            }
            if (last_occ != -1) {
                s = s.substring(0, last_occ) + amt + s.substring(last_occ + 1);
            }
            //get rid of this above and you will get part one answer

            int first = 0, last = 0;
            for (int j = 0; j < s.length(); j++) {
                if (0 <= s.charAt(j) - 48 && s.charAt(j) - 48 < 10) {
                    first = Integer.parseInt(s.substring(j, j+1));
                    break;
                }
            }
            for (int j = s.length()-1; j >= 0; j--) {
                if (0 <= s.charAt(j) - 48 && s.charAt(j) - 48 < 10) {
                    last = Integer.parseInt(s.substring(j, j+1));
                    break;
                }
            }

            pw.println(first + "" + last);

            sum += 10 * first + last;
        }
        pw.println(sum);

        pw.close();
        r.close();
    }
}
