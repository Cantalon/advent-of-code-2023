import java.io.*;
import java.util.*;

public class day5 {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer seedLine = new StringTokenizer(r.readLine());
        seedLine.nextToken();

        long[][] seeds = new long[10][2];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                seeds[i][j] = Long.parseLong(seedLine.nextToken());
            }
        }

        r.readLine(); r.readLine();
        long[][] seedsToSoil = new long[32 - 4 + 1][3];
        for (int i = 4; i <= 32; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            seedsToSoil[i - 4][0] = Long.parseLong(st.nextToken());
            seedsToSoil[i - 4][1] = Long.parseLong(st.nextToken());
            seedsToSoil[i - 4][2] = Long.parseLong(st.nextToken());
        }

        r.readLine(); r.readLine();
        long[][] soilToFert = new long[53-35 + 1][3];
        for (int i = 35; i <= 53; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            soilToFert[i - 35][0] = Long.parseLong(st.nextToken());
            soilToFert[i - 35][1] = Long.parseLong(st.nextToken());
            soilToFert[i - 35][2] = Long.parseLong(st.nextToken());
        }

        r.readLine(); r.readLine();
        long[][] fertToWater = new long[97-56 + 1][3];
        for (int i = 56; i <= 97; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            fertToWater[i - 56][0] = Long.parseLong(st.nextToken());
            fertToWater[i - 56][1] = Long.parseLong(st.nextToken());
            fertToWater[i - 56][2] = Long.parseLong(st.nextToken());
        }

        r.readLine(); r.readLine();
        long[][] waterToLight = new long[117-100 + 1][3];
        for (int i = 100; i <= 117; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            waterToLight[i - 100][0] = Long.parseLong(st.nextToken());
            waterToLight[i - 100][1] = Long.parseLong(st.nextToken());
            waterToLight[i - 100][2] = Long.parseLong(st.nextToken());
        }

        r.readLine(); r.readLine();
        long[][] lightToTemp = new long[132-120 + 1][3];
        for (int i = 120; i <= 132; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            lightToTemp[i - 120][0] = Long.parseLong(st.nextToken());
            lightToTemp[i - 120][1] = Long.parseLong(st.nextToken());
            lightToTemp[i - 120][2] = Long.parseLong(st.nextToken());
        }

        r.readLine(); r.readLine();
        long[][] tempToHum = new long[144-135 + 1][3];
        for (int i = 135; i <= 144; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            tempToHum[i - 135][0] = Long.parseLong(st.nextToken());
            tempToHum[i - 135][1] = Long.parseLong(st.nextToken());
            tempToHum[i - 135][2] = Long.parseLong(st.nextToken());
        }

        r.readLine(); r.readLine();
        long[][] humToLoc = new long[190-147 + 1][3];
        for (int i = 147; i <= 190; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            humToLoc[i - 147][0] = Long.parseLong(st.nextToken());
            humToLoc[i - 147][1] = Long.parseLong(st.nextToken());
            humToLoc[i - 147][2] = Long.parseLong(st.nextToken());
        }

        outer:
        for (long i = 0; i < 1000000000; i++) {
            long test = i;
            for (long[] transform : humToLoc) {
                if (transform[0] <= test && transform[0] + transform[2] > test) {
                    test = test - transform[0] + transform[1];
                    break;
                }
            }
            for (long[] transform : tempToHum) {
                if (transform[0] <= test && transform[0] + transform[2] > test) {
                    test = test - transform[0] + transform[1];
                    break;
                }
            }
            for (long[] transform : lightToTemp) {
                if (transform[0] <= test && transform[0] + transform[2] > test) {
                    test = test - transform[0] + transform[1];
                    break;
                }
            }
            for (long[] transform : waterToLight) {
                if (transform[0] <= test && transform[0] + transform[2] > test) {
                    test = test - transform[0] + transform[1];
                    break;
                }
            }
            for (long[] transform : fertToWater) {
                if (transform[0] <= test && transform[0] + transform[2] > test) {
                    test = test - transform[0] + transform[1];
                    break;
                }
            }
            for (long[] transform : soilToFert) {
                if (transform[0] <= test && transform[0] + transform[2] > test) {
                    test = test - transform[0] + transform[1];
                    break;
                }
            }
            for (long[] transform : seedsToSoil) {
                if (transform[0] <= test && transform[0] + transform[2] > test) {
                    test = test - transform[0] + transform[1];
                    break;
                }
            }
            for (int j = 0; j < 10; j++) {
                if (seeds[j][0] <= test && seeds[j][0] + seeds[j][1] > test) {
                    pw.println(i);
                    break outer;
                }
            }
        }

        pw.close();
        r.close();
    }
}
