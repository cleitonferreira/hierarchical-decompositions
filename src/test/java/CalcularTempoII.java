import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class CalcularTempoII {

  public static void main(String[] args) throws InterruptedException {

    long startTimestamp = System.currentTimeMillis();
    DecimalFormat df4 = new DecimalFormat("0.0000");

    String classCount = "10";
    double fitness = 152.24942367645815;

    TimeUnit.SECONDS.sleep(10);

    //TimeUnit.MINUTES.sleep(2);

    long finishTimestamp = System.currentTimeMillis();
    long seconds = (finishTimestamp - startTimestamp);

    long memory = Runtime.getRuntime().freeMemory() / (1024 * 1024);
    System.out.println(padLeft("CalcularTempoII.class", 20) + " " + padRight("" + classCount, 10) + " " + padRight(df4.format(fitness), 10) + " " + padRight("" + seconds, 10) + " ms " + padRight("" + memory, 10) + " MB");
    //System.out.println(projectName + ";" + project.getClassCount() + ";" + df4.format(ils.getBestFitness()));
  }

  public static String padLeft(String s, int length)
  {
    StringBuilder sb = new StringBuilder();
    sb.append(s);

    while (sb.length() < length)
      sb.append(' ');

    return sb.toString();
  }

  public static String padRight(String s, int length)
  {
    StringBuilder sb = new StringBuilder();

    while (sb.length() < length - s.length())
      sb.append(' ');

    sb.append(s);
    return sb.toString();
  }

}
