package threads.simple;

class MyRMW implements Runnable {
    long counter = 0;

    @Override
    public void run() {
        for (int i = 0; i < 100_000_000; i++) {
            counter++; // NOT an "atomic" operation!!!! (read/modify/write)
        }
        System.out.println("Finished...");
    }
}
public class ReadModifyWrite {
    public static void main(String[] args) throws InterruptedException {
        MyRMW job = new MyRMW();

        new Thread(job).start();
//        Thread.sleep(1_000);
        new Thread(job).start();
//        Thread.sleep(1_000);
        new Thread(job).start();

        Thread.sleep(1_000);
        System.out.println("Checking total counter value ");
        System.out.println("Counter is " + job.counter);
    }
}
