import java.io.*;
import java.util.*;

public class day9 {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        int ans = 0;
        for (int line = 0; line < 200; line++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            ArrayList<Long> nums = new ArrayList<>();
            while (st.hasMoreTokens()) {
                nums.add(Long.parseLong(st.nextToken()));
            }
            ArrayList<Long> nums_transform = new ArrayList<>(nums);
            ArrayList<Long> maintain = new ArrayList<>();
            while (!allZeros(nums_transform)) {
                for (int i = nums_transform.size() - 1; i >= 1; i--) {
                    nums_transform.set(i, nums_transform.get(i) - nums_transform.get(i - 1));
                }
                maintain.add(nums_transform.remove(0));
            }
            long res = 0;
            for (int i = maintain.size() - 1; i >= 0; i--) {
                res = maintain.get(i) - res;
            }
            ans += res;
        }

        pw.println(ans);

        pw.close();
        r.close();
    }

    static boolean allZeros(ArrayList<Long> arr) {
        for (Long l : arr) {
            if (l != 0) {
                return false;
            }
        }
        return true;
    }
}
