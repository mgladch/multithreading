package main;


import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


import static main.Util.fillArray;
import static main.Util.findFreq;

public class Main {
    static int[] array = fillArray();

    public static void main(String[] args) throws InterruptedException, ExecutionException {


        System.out.println("Choose how to run the program:\n" +
                "1 - Using multithreading\n" +
                "2 - Without multithreading");
        Scanner ini = new Scanner(System.in);
        int key = ini.nextInt();
        switch (key) {
            case (1): {
                long m = System.currentTimeMillis();
                int ts = 4;
                int size = array.length / ts;
                int[] part1 = new int[size];
                int[] part2 = new int[size];
                int[] part3 = new int[size];
                int[] part4 = new int[size];
                int [][] parts = new int[][] { part1, part2, part3, part4 };
                System.arraycopy(array, 0, part1, 0, size);
                System.arraycopy(array, size, part2, 0, size);
                System.arraycopy(array, size * 2, part3, 0, size);
                System.arraycopy(array, size * 3, part4, 0, size);

                ArrayList<Future<Integer[]>> tasks = new ArrayList<>(ts);
                ExecutorService executorService = Executors.newFixedThreadPool(ts);
                for (int i = 0; i < ts; i++) {
                    MyThread thread = new MyThread(parts[i]);
                    tasks.add(executorService.submit(thread));
                }
                Integer[][] results = new Integer[ts][size];
                for (int i = 0; i < ts; i++) {
                    results[i] = tasks.get(i).get();
                }
                executorService.shutdown();
                int maxFrequency = -1;
                int maxFrequencyNumber = -1;
                for (int i = 0; i < 300; i++) {
                    int numberFrequenciesSum = 0;
                    for (int j = 0; j < ts; j++) {
                        numberFrequenciesSum += results[j][i];
                    }
                    if (numberFrequenciesSum > maxFrequency) {
                        maxFrequency = numberFrequenciesSum;
                        maxFrequencyNumber = i;
                    }
                }
                System.out.println("The most frequency number: " + maxFrequencyNumber + ". Number of repetitions: " + maxFrequency);
                System.out.print("Program execution time ");
                System.out.println((double) (System.currentTimeMillis() - m));
                return;
            }
            case (2): {
                long m = System.currentTimeMillis();
                System.out.println("without multithreading");
                findFreq(array);
                System.out.print("Program execution time ");
                System.out.println((double) (System.currentTimeMillis() - m));
                return;
            }
            default: {
                System.out.println("The number entered is incorrect!");
            }
        }
    }
}