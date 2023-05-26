import java.util.concurrent.TimeUnit;

public class CalcularTempo {

  public static void main(String[] args) throws InterruptedException {

    long beginTotalTime = System.currentTimeMillis();

    TimeUnit.SECONDS.sleep(10);

    //TimeUnit.MINUTES.sleep(2);

    // Calculating time of all executions
    long totalTime = System.currentTimeMillis() - beginTotalTime;

    System.out.println("Tempo execução todos arquivos: " + (totalTime / 1000D) + "seg \n"
        + "----------------------------------------------------- \n \n");
  }

}
