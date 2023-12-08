package org.anne.common;

public class MathsUtils {

    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    public static long lcm(long a, long b) {
        return Math.abs(a * b) / gcd(a, b);
    }

}
