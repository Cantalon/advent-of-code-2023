import java.io.*;
import java.util.*;

public class day15 {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(r.readLine(), ",");
        ArrayList<ArrayList<Lens>> boxes = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            boxes.add(new ArrayList<>());
        }//initialize

        while (st.hasMoreTokens()) {
            String instruction = st.nextToken();
            if (instruction.contains("=")) {
                String[] s = instruction.split("=");
                int boxNum = hash(s[0]);

                Lens newLens = new Lens(Integer.parseInt(s[1]), s[0]);
                if (boxes.get(boxNum).contains(newLens)) {
                    boxes.get(boxNum).set(boxes.get(boxNum).indexOf(newLens), newLens);
                } else {
                    boxes.get(boxNum).add(newLens);
                }
            } else {
                String[] s = instruction.split("-");
                int boxNum = hash(s[0]);
                Lens newLens = new Lens(-1, s[0]);
                boxes.get(boxNum).remove(newLens);
            }
        }

        long sum = 0;
        for (int i = 0; i < boxes.size(); i++) {
            for (int j = 0; j < boxes.get(i).size(); j++) {
                sum += (long) (i + 1) * (j + 1) * (boxes.get(i).get(j).focal);
            }
        }
        pw.println(sum);

        r.close();
        pw.close();
    }

    static class Lens {
        int focal;
        String label;

        public Lens(int a, String b) {
            focal = a;
            label = b;
        }

        @Override
        public boolean equals(Object l) {
            if (l == null) return false;
            if (!(l instanceof Lens l2)) return false;
            return label.equals(l2.label);
        }
    }

    static int hash(String s) {
        int cur = 0;
        for (char c : s.toCharArray()) {
            cur = ((cur + c) * 17) % 256;
        }
        return cur;
    }
}
