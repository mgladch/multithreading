package main;

import java.util.concurrent.Callable;

import static main.Util.numbersFrequencies;

public class MyThread implements Callable<Integer[]> {

    private final int[] array;

    public MyThread(int[] array) {
        this.array = array;
    }

    @Override
    public Integer[] call() {
        return numbersFrequencies(array);
    }
}
