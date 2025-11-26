package problems.p11444_피보나치_수_6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    static BigInteger F1 = new BigInteger("0"), F2 = new BigInteger("1");
    static long N, f1 = 0L, f2 = 1L, temp;
    static int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Long.parseLong(br.readLine());

        System.out.println(fibo(N)[0]);
    }

    public static long[] fibo(long n) {
        if (n == 0) return new long[]{0, 1};
        long k = n / 2;
        long[] farr = fibo(k); //f_k, f_k+1

        long c = (farr[0] * (2 * farr[1] - farr[0] + MOD)) % MOD; //f(2k)
        long d = ((farr[1] * farr[1]) % MOD + farr[0] * farr[0] % MOD) % MOD; //f(2k+1)

        if (n % 2 == 0) {
            return new long[]{c, d};
        } else {
            return new long[]{d, (c + d) % MOD};
        }

    }
}