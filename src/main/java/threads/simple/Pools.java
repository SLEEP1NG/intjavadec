package threads.simple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

class MyCallable implements Callable<String> {
    private static int nextJobId = 0;
    private int myJobId = nextJobId++;

    public String call() throws Exception {
        System.out.println("Starting job " + myJobId);
        Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 4000));
        System.out.println("job " + myJobId + " ending");
        return "Result of job " + myJobId;
    }
}

public class Pools {
    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(2);
        List<Future<String>> handles = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            handles.add(es.submit(new MyCallable()));
        }
        System.out.println("All jobs submitted");

        while (handles.size() > 0) {
            Iterator<Future<String>> iter = handles.iterator();
            while (iter.hasNext()) {
                Future<String> handle = iter.next();
                if (handle.isDone()) {
                    try {
                        System.out.println("job finished: " + handle.get());
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted???");;
                    } catch (ExecutionException e) {
                        System.out.println("Job threw exception " + e.getCause());;
                    }
                    iter.remove();
                }
            }
        }
        es.shutdown(); // no new jobs, and finish when all work is done
        System.out.println("All jobs completed...");
    }
}
