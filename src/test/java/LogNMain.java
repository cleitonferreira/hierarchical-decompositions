

import java.util.Arrays;

public class LogNMain {

    public static void main(String[] args) {

        System.out.println(logN(12));
        System.out.println(l(12));

    }

    /* A.4.1 Code lengths for integers
   l(n) = log(n) + 2log( log(n + 1)) + 1 */
    private static double logN(int n) {
        double resultadofn;

        resultadofn = log(2, n) + 2 * log(2) * log(2, n + 1) + 1;

        return resultadofn;
    }

    /* A.4.1 Code lengths for integers
    l(n) = log(n) + 2log * log(n + 1) + 1 */
    static double l(double n) {
        double resultadofn;

        //resultadofn = log(2, n) + 2 * log(2, log(2, (n + 1))) + 1;
        resultadofn = log(2, n) + 2 * log(2, n) * log(2, (n + 1)) + 1;

        return resultadofn;
    }

    private static double log(double base, double valor) {
        return (Math.log(valor) / Math.log(base));
    }

    private static double log(double base) {
        return (Math.log(base));
    }



}


