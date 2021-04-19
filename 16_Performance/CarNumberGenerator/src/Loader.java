import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

public class Loader {

  private static final int CAPACITY = 100000;

  public static void main(String[] args) throws Exception {
    long start = System.currentTimeMillis();

    List<FutureTask> taskList = new ArrayList<>();

    ExecutorService executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    for (int i = 0; i < 100; i++) {
      taskList.add((FutureTask) executor.submit(new NumberWriterTask(i*100 + 1, i*100 + 100)));
    }
    for (FutureTask task: taskList
    ) {
      task.get();
    }
    executor.shutdown();
    System.out.println((System.currentTimeMillis() - start) + " ms");
  }



  static class NumberWriterTask implements Runnable {

    private int startIndex, endIndex;

    public NumberWriterTask(int startIndex, int endIndex) {
      this.startIndex = startIndex;
      this.endIndex = endIndex;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used to create a thread,
     * starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
      try {
        generate();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    private void generate() throws IOException {
      FileOutputStream writer = new FileOutputStream(String.format(
          "./16_Performance/CarNumberGenerator/res/%s.txt", UUID.randomUUID().toString()));

      StringBuffer stringBuffer = new StringBuffer();

      char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

      int regionCode = 199;
      char[] regionSymbols = padNumber(regionCode, 2);

      for (int number = startIndex; number <= endIndex; number++) {

        char[] numberSymbols = padNumber(number, 3);

        for (char firstLetter : letters) {
          for (char secondLetter : letters) {
            for (char thirdLetter : letters) {
              stringBuffer.append(firstLetter);
              stringBuffer.append(numberSymbols);
              stringBuffer.append(secondLetter);
              stringBuffer.append(thirdLetter);
              stringBuffer.append(regionSymbols);
              stringBuffer.append('\n');
            }
          }
        }

        if (stringBuffer.length() > 1000000) {
          writer.write(stringBuffer.toString().getBytes(StandardCharsets.UTF_8));
          stringBuffer.setLength(0);
        }
      }

      if (stringBuffer.length() > 0) {
        writer.write(stringBuffer.toString().getBytes(StandardCharsets.UTF_8));
        stringBuffer.setLength(0);
      }

      writer.flush();
      writer.close();
    }

    /**
     * @param number number
     * @param number finalNumberLength
     * @return
     */
    private char[] padNumber(int number, int finalNumberLength) {
      int numberLength = (int) (Math.log10(number) + 1);
      char[] padNumber = new char[(finalNumberLength - numberLength) > 0 ? finalNumberLength
          : numberLength];
      Arrays.fill(padNumber, '0');
      int charIndex = padNumber.length - 1;
      while (number > 0) {
        padNumber[charIndex--] = Character.forDigit(number % 10, 10);
        number = number / 10;
      }
      return padNumber;
    }

  }

}
