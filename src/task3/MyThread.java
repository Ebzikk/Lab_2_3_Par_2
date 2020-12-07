package task3;

import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyThread implements Runnable {
    private final CyclicBarrier cyclicBarrier;
    private final PrintSumThread printSumThread;
    private final int[] array;
    private final int firstIndex;
    private final int secondIndex;

    public MyThread(CyclicBarrier cyclicBarrier,
                    PrintSumThread printSumThread,
                    int[] arr,
                    int firstIndex,
                    int secondIndex) {
        this.printSumThread = Objects.requireNonNull(printSumThread);
        this.cyclicBarrier = Objects.requireNonNull(cyclicBarrier);
        this.array = Objects.requireNonNull(arr);

        if (firstIndex <= 0 && secondIndex <= 0) {
            throw new IllegalArgumentException("Invalid indexes");
        }
        this.firstIndex = firstIndex;
        this.secondIndex = secondIndex;

        new Thread(this).start();
    }

    @Override
    public void run() {
        calculateSum();

        try {
            cyclicBarrier.await();
        }
        catch (BrokenBarrierException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void calculateSum() {
        int sum = 0;
        for (int i = firstIndex; i < secondIndex; i++) {
            sum += array[i];
        }
        printSumThread.add(sum);
    }
}
