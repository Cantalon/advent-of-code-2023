import java.io.*;
import java.util.*;

public class day19 {
    static int N = 512;
    static int M = 200;

    static HashMap<String, Workflow> workflows;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        workflows = new HashMap<>();

        for (int line = 0; line < N; line++) {
            StringTokenizer st = new StringTokenizer(r.readLine(), "{");
            String name = st.nextToken();
            String input = st.nextToken();
            input = input.substring(0, input.length() - 1);

            String[] arr = input.split(",");
            Workflow w = new Workflow(name);

            for (int i = 0; i < arr.length - 1; i++) {
                int val;
                boolean less = true;
                if (arr[i].charAt(0) == 'x') {
                    val = 0;
                } else if (arr[i].charAt(0) == 'm') {
                    val = 1;
                } else if (arr[i].charAt(0) == 'a') {
                    val = 2;
                } else {
                    val = 3;
                }
                if (arr[i].charAt(1) == '>') {
                    less = false;
                }
                String[] temp = arr[i].split(":");
                w.addCondition(val, less, Integer.parseInt(temp[0].substring(2)), temp[1]);
            }
            w.addFinal(arr[arr.length - 1]);
            workflows.put(w.name, w);
        } //read input

        long[][] range = new long[4][2];
        for (long[] arr : range) {
            arr[0] = 1; arr[1] = 4000;
        }
        pw.println(dfs("in", range));

        r.close();
        pw.close();
    }

    static class Workflow {
        String name;
        ArrayList<Integer> val;
        ArrayList<Boolean> less;
        ArrayList<Integer> compare;
        ArrayList<String> action;
        String fin;

        public Workflow(String n) {
            name = n;

            val = new ArrayList<>();
            less = new ArrayList<>();
            compare = new ArrayList<>();
            action = new ArrayList<>();
        }

        public void addFinal(String s) {
            fin = s;
        }

        public void addCondition(int a, boolean b, int c, String d) {
            val.add(a);
            less.add(b);
            compare.add(c);
            action.add(d);
        }
    } //class

    static long dfs(String search, long[][] ranges) {
        if (search.equals("R")) {
            return 0;
        }
        if (search.equals("A")) {
            long ans = 1;
            for (int i = 0; i < 4; i++) {
                ans *= (ranges[i][1] - ranges[i][0] + 1);
            } return ans;
        }

        long[] copy = new long[8];
        for (int i = 0; i < 4; i++) {
            copy[2 * i] = ranges[i][0];
            copy[2 * i + 1] = ranges[i][1];
        }

        long ans = 0;
        Workflow w = workflows.get(search);
        for (int i = 0; i < w.val.size(); i++) {
            if (w.less.get(i)) {
                if (ranges[w.val.get(i)][0] >= w.compare.get(i)) {continue;} //keep testing
                if (ranges[w.val.get(i)][1] < w.compare.get(i)) { //whole part is diverted
                    ans += dfs(w.action.get(i), ranges);
                } else { //part of it is diverted
                    long original = ranges[w.val.get(i)][1];
                    ranges[w.val.get(i)][1] = (long) w.compare.get(i) - 1;
                    ans += dfs(w.action.get(i), ranges);
                    ranges[w.val.get(i)][1] = original;
                    ranges[w.val.get(i)][0] = w.compare.get(i);
                }
            } else {
                if (ranges[w.val.get(i)][1] <= w.compare.get(i)) {continue;} //keep testing
                if (ranges[w.val.get(i)][0] > w.compare.get(i)) { //whole part is diverted
                    ans += dfs(w.action.get(i), ranges);
                } else { //part of it is diverted
                    long original = ranges[w.val.get(i)][0];
                    ranges[w.val.get(i)][0] = (long) w.compare.get(i) + 1;
                    ans += dfs(w.action.get(i), ranges);
                    ranges[w.val.get(i)][0] = original;
                    ranges[w.val.get(i)][1] = w.compare.get(i);
                }
            }
        }
        ans += dfs(w.fin, ranges);

        for (int i = 0; i < 4; i++) {
            ranges[i][0] = copy[2 * i];
            ranges[i][1] = copy[2 * i + 1];
        }//restore ranges so that dfs doesn't mess up

        return ans;
    }
}
