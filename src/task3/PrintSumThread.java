package task3;

public class PrintSumThread implements Runnable {
    private int sum;

    public void printSum() {
        System.out.println(sum);
    }

    @Override
    public void run() {
        printSum();
    }

    public void add(int num) {
        sum += num;
    }
}