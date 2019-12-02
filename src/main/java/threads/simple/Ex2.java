package threads.simple;

class MyJob2 implements Runnable {
    private int i = 0;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Running job");
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " i is " + i);
        }
        System.out.println(Thread.currentThread().getName() + " Finishing job");
    }
}

public class Ex2 {
    public static void main(String[] args) {
        MyJob2 mj = new MyJob2();

        Thread t1 = new Thread(mj);
        t1.start();
        Thread t2 = new Thread(mj);
        t2.start();
        System.out.println(Thread.currentThread().getName() + " Main exiting");
    }
}
