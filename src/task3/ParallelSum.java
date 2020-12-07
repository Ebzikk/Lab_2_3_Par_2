package task3;

import java.util.concurrent.CyclicBarrier;

public class ParallelSum {

    private ParallelSum() {
    }

    public static void sum(int[] array, int numberOfThreads) {
        PrintSumThread barrierAction = new PrintSumThread();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(numberOfThreads, barrierAction);

        int step = (int) Math.ceil(array.length * 1.0 / numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            int firstIndex = i * step;
            int secondIndex = (i + 1) * step;
            new MyThread(cyclicBarrier, barrierAction, array, firstIndex, secondIndex);
        }
    }
}