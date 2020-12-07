package task3_2;

import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyThread extends Thread{
    private final CyclicBarrier cyclicBarrier;
    private final PrintSumThread printSumThread;
    private final int[] array;
    private final int firstIndex;
    private final int secondIndex;
    private int sum;

    public MyThread(CyclicBarrier cyclicBarrier,
                    PrintSumThread printSumThread,
                    int[] array,
                    int firstIndex,
                    int secondIndex) {
        this.cyclicBarrier = Objects.requireNonNull(cyclicBarrier);
        this.printSumThread = Objects.requireNonNull(printSumThread);
        this.array = Objects.requireNonNull(array);
        this.firstIndex = firstIndex;
        this.secondIndex = secondIndex;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public void run() {
        calculateSum();
        try {
            cyclicBarrier.await();
        }
        catch (BrokenBarrierException | InterruptedException e) {
            System.out.println(e);
        }
    }

    private void calculateSum() {
        if (firstIndex == secondIndex) {
            sum = array[firstIndex];
        } else {
            sum = array[firstIndex] + array[secondIndex];
        }
        printSumThread.add(sum);
    }
}