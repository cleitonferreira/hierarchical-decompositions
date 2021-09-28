

import java.util.Arrays;

public class LogNMain {

    public static void main(String[] args) {

        System.out.println(logN(12));

    }

    /* A.4.1 Code lengths for integers
   l(n) = log(n) + 2log X log(n + 1) + 1 */
    private static int logN(int n) {
        int resultadofn;

        resultadofn = log(2, n) + 2 * log(2) * log(2, n + 1) + 1;

        return resultadofn;
    }

    private static int log(double base, double valor) {
        return (int) (Math.log(valor) / Math.log(base));
    }

    private static int log(double base) {
        return (int) (Math.log(base));
    }



}


