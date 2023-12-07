import java.io.*;
import java.util.*;

public class day7 {

    public static HashMap<Character, Integer> tied;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        tied = new HashMap<>();
        tied.put('A', 14);
        tied.put('K', 13);
        tied.put('Q', 12);
        tied.put('J', 1);
        tied.put('T', 10);
        for (int i = '9'; i >= '2'; i--) {
            tied.put((char) i, i - 48);
        }

        Hand[] list = new Hand[1000];

        for (int line = 0; line < 1000; line++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            list[line] = new Hand(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(list);
        long total = 0;
        for (int i = 1; i <= 1000; i++) {
            total += (long) (1001 - i) * (list[i - 1].bid);
        }
        pw.println(total);

        pw.close();
        r.close();
    }

    public static class Hand implements Comparable<Hand> {
        String cards;
        int bid;
        int type;

        public Hand(String s, int b) {
            cards = s;
            bid = b;
            int jokers = 0;
            int[] counts = new int[12];
            for (int i = 0; i < 5; i++) {
                if (cards.charAt(i) == 'J') {
                    jokers++;
                } else if (cards.charAt(i) == 'A') {
                    counts[0]++;
                } else if (cards.charAt(i) == 'K') {
                    counts[1]++;
                } else if (cards.charAt(i) == 'Q') {
                    counts[2]++;
                } else if (cards.charAt(i) == 'T') {
                    counts[3]++;
                } else {
                    counts[(cards.charAt(i) - 48) + 2]++; //num + 2
                }
            }
            Arrays.sort(counts);
            counts[11] += jokers;
            if (counts[11] == 5) {
                type = 6;
            } else if (counts[11] == 4) {
                type = 5;
            } else if (counts[11] == 3) {
                if (counts[10] == 2) {
                    type = 4;
                } else {
                    type = 3;
                }
            } else if (counts[11] == 2) {
                if (counts[10] == 2) {
                    type = 2;
                } else {
                    type = 1;
                }
            } else {
                type = 0;
            }
        }

        @Override
        public int compareTo(Hand o) {
            if (type != o.type) {
                return Integer.compare(o.type, type);
            } else {
                for (int i = 0; i < 5; i++) {
                    if (cards.charAt(i) != o.cards.charAt(i)) {
                        return Integer.compare(tied.get(o.cards.charAt(i)), tied.get(cards.charAt(i)));
                    }
                }
            }
            return 0;
        }
    }
}
