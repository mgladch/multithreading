package main;

import java.util.Random;

public class Util {
    public static int[] fillArray() {
        int[] arr = new int[300000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(300);
        }
        return arr;
    }

    public static Integer[] numbersFrequencies(int[] array) {
        int numbersAmount = 300;
        Integer[] frequencies = new Integer[numbersAmount];
        for (int i = 0; i < numbersAmount; i++) {
            frequencies[i] = 0;
        }
        for (int number : array) {
            if (frequencies[number] != 0) {
                // Пропускаєм ті числа, для яких ми вже порахували кількість входжень
                continue;
            }
            int numberFrequency = frequency(array, number);
            frequencies[number] = numberFrequency;
        }
        return frequencies;
    }

    static int frequency(int[] arr, int x) {
        int n = 0;
        for (int temp : arr) {
            if (x == temp)
                n++;
        }
        return n;
    }

    static void findFreq(int[] arr) {
        int n = 0, frec = 0;
        for (int temp : arr) {
            int narr = frequency(arr, temp);
            if (frec < narr) {
                n = temp;
                frec = narr;
            }
        }
        System.out.println("Число " + n + " повторюється " + frec + " разів");
    }
}
