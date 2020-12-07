package task3_2;

import java.util.Objects;
import java.util.concurrent.CyclicBarrier;

public class ParallelSum {

    private ParallelSum() {
    }

    public static int sum(int[] array) {
        Objects.requireNonNull(array);
        int numberOfThreads = halvingSizeOfArray(array);

        MyThread[] threads = new MyThread[numberOfThreads];
        runningThreads(threads, array, numberOfThreads);
        joiningThreads(threads);

        int[] results = getResultFromThreads(threads, numberOfThreads);
        return sumOfArrayUpToLastElement(results);
    }

    private static int halvingSizeOfArray(int[] array) {
        int length = array.length;
        if (length % 2 == 0) {
            return length / 2;
        } else {
            return length / 2 + 1;
        }
    }

    private static void runningThreads(MyThread[] threads, int[] array, int numberOfThreads) {
        PrintSumThread barrierAction = new PrintSumThread();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(numberOfThreads, barrierAction);
        int length = array.length;
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new MyThread(cyclicBarrier,barrierAction,array, i, length - i - 1);
            threads[i].start();
        }
    }

    private static void joiningThreads(MyThread[] threads) {
        try {
            for (MyThread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int[] getResultFromThreads(MyThread[] threads, int halfSizeOfArray) {
        int[] result = new int[halfSizeOfArray];
        for (int i = 0; i < halfSizeOfArray; i++) {
            result[i] = threads[i].getSum();
        }
        return result;
    }

    private static int sumOfArrayUpToLastElement(int[] resultsOfArray) {
        if (resultsOfArray.length > 1) {
            return sum(resultsOfArray);
        } else {
            return resultsOfArray[0];
        }
    }
}