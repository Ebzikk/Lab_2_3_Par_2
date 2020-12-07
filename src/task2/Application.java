package task2;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        int size = 20;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        System.out.println("array = " + Arrays.toString(array));

        int sum = ParallelSum.sum(array);
        System.out.println("sum = " + sum);
    }
}