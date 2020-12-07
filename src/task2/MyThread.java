package task2;

public class MyThread extends Thread{
    private final int firstIndex;
    private final int secondIndex;
    private int[] array;
    private int sum;

    public MyThread(int[] array, int firstIndex, int secondIndex) {
        this.array = array;
        this.firstIndex = firstIndex;
        this.secondIndex = secondIndex;
    }

    @Override
    public void run() {
        if (firstIndex == secondIndex) {
            sum = array[firstIndex];
        } else {
            sum = array[firstIndex] + array[secondIndex];
        }
    }

    public int getSum() {
        return sum;
    }
}