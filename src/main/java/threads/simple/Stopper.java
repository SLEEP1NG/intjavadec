package threads.simple;

public class Stopper {
    static volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("Job starting...");

            while (! stop)
                ;

            System.out.println("Job shutting down...");
        }).start();

        Thread.sleep(1_000);
        System.out.println("Stopping job...");
        stop = true;
        System.out.println("Job flagged to stop, main exiting...");
    }
}
