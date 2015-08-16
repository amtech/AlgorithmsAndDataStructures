package main.other;

/**
 * Created by koxa on 02.08.2015.
 */
public class Util {

    public static int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }
}
