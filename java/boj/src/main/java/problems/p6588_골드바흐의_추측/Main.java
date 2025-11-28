package problems.p6588_골드바흐의_추측;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static boolean[] prime = new boolean[1000001];
    static List<Integer> oddPrime = new ArrayList<>();
    static int n;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        eratosSieze();

        for (int i = 3; i <= 1000000; i += 2) {
            if (prime[i]) {
                oddPrime.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            boolean find = false;
            for (int p = 0; p < oddPrime.size(); p++) {
                int pr = oddPrime.get(p);
                if (prime[n - pr]) {
                    sb.append(String.format("%d = %d + %d%n", n, pr, n - pr));
                    find = true;
                    break;
                }
            }

            if (!find)
                sb.append("\"Goldbach's conjecture is wrong.\"");
        }

        System.out.println(sb.toString());
    }

    public static void eratosSieze() {
        Arrays.fill(prime, true);
        prime[1] = false;
        for (int i = 2; i * i <= 1000000; i++) {
            if (!prime[i]) continue;
            for (int j = i * i; j <= 1000000; j += i) {
                prime[j] = false;
            }
        }
    }
}
