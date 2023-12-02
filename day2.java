import java.io.*;
import java.util.*;

public class day2 {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        int count = 0;

        outer:
        for (int line = 1; line <= 100; line++) {
            String inp = r.readLine();
            inp = inp.substring(5);
            inp = inp.substring(inp.indexOf(" ") + 1);

            //for day 1, ref goes outside for loop and is updated to 12, 13, and 14
            HashMap<String, Integer> ref = new HashMap<>();
            ref.put("red", 0);
            ref.put("green", 0);
            ref.put("blue", 0);

            StringTokenizer perDraw = new StringTokenizer(inp, ";");
            while (perDraw.hasMoreTokens()) {
                StringTokenizer game = new StringTokenizer(perDraw.nextToken());
                while (game.hasMoreTokens()) {
                    int x = Integer.parseInt(game.nextToken());
                    String s = game.nextToken();
                    if (s.charAt(s.length() - 1) == ',') {
                        s = s.substring(0, s.length() - 1);
                    }
                    //for day 1, if (ref.get(s) < x) break;
                    ref.put(s, Math.max(ref.get(s), x));
                }
            }
            count += ref.get("red") * ref.get("green") * ref.get("blue");
            //for day 1, count += line;
        }

        pw.println(count);

        pw.close();
        r.close();
    }
}
