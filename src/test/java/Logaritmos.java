class Logaritmos {

    public static void main(String[] args) {
        System.out.println(log(2, 128));
        System.out.println(log(5, 625));
        System.out.println(log(100, 1000));
        System.out.println(log(7, 49));


    }

    public static double log(double base, double valor) {
        return Math.log(valor) / Math.log(base);
    }
}