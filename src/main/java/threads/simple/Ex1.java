package threads.simple;

class MyJob implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Running job");
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " i is " + i);
        }
        System.out.println(Thread.currentThread().getName() + " Finishing job");
    }
}
public class Ex1 {
    public static void main(String[] args) {
        MyJob mj = new MyJob();
//        mj.run();

        Thread t1 = new Thread(mj);
        t1.start();
        Thread t2 = new Thread(mj);
        t2.start();
        System.out.println(Thread.currentThread().getName() + " Main exiting");
    }
}
