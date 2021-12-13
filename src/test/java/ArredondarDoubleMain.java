import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ArredondarDoubleMain {

    public static void main(String[] args) {
        truncar(25.356);

        double d = 7435.9876;
        double roundDbl = Math.round(d*100.0)/100.0;
        System.out.println("Rounded Double value: "+roundDbl);

        System.out.println(truncateDecimal(0, 2));
        System.out.println(truncateDecimal(9.62, 2));
        System.out.println(truncateDecimal(9.621, 2));
        System.out.println(truncateDecimal(9.629, 2));
        System.out.println(truncateDecimal(9.625, 2));
        System.out.println(truncateDecimal(9.999, 2));
        System.out.println(truncateDecimal(-9.999, 2));
        System.out.println(truncateDecimal(-9.0, 2));

        double teste = truncateDecimal(-9.999, 2);
        System.out.println("teste: "+ teste);
    }

    private static double truncateDecimal(double x, int numberofDecimals)
    {
        if ( x > 0) {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR).doubleValue();
        } else {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING).doubleValue();
        }
    }

    private static void truncar(Double valor) {
        System.out.println( valor );
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        System.out.println( decimalFormat.format(valor) );
    }
}