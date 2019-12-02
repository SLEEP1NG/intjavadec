package threads.simple;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProdCons {
    public static void main(String[] args) {
        final BlockingQueue<int[]> q = new ArrayBlockingQueue<>(10);

        new Thread(() -> {
            System.out.println("Starting producer..");
            for (int i = 0; i < 10_000; i++) {
                try {
                    int[] data = {i, 0};
                    if (i < 100) Thread.sleep(1);
                    data[1] = i;
                    if (i == 5_000) data[0] = -1;
                    q.put(data);
                    data = null;
                } catch (InterruptedException ie) {
                    System.out.println("WHAT!!! Interrupted!");
                }
            }
            System.out.println("Producer ending...");
        }).start();

        new Thread(() -> {
            System.out.println("Consumer starting...");
            for (int i = 0; i < 10_000; i++) {
                try {
                    if (i > 9_900) Thread.sleep(1);
                    int[] data = q.take();
                    if (data[0] != i || data[0] != data[1]) {
                        System.out.println("**** Error at index " + i);
                    }
                } catch (InterruptedException ie) {
                    System.out.println("WHAT!!!! Consumer interruped!");
                }
            }
            System.out.println("Consumer finished...");
        }).start();

        System.out.println("Main completed...");
    }
}
