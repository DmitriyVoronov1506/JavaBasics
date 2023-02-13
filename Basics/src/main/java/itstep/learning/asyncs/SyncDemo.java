package itstep.learning.asyncs;

import java.util.Random;

public class SyncDemo {
    private Random random;
    long time1;
    long time2;

    public void run() {

        random = new Random();
        sum = 100;
        activeThreads = 12;

        time1 = System.nanoTime();

        for(int i = 0; i < 12; ++i) {
            new Thread(this::plusPercent2).start();
        }
    }

    private double sum;
    private Integer activeThreads;
    private final Object locker = new Object();
    private final Object activeThreadsLocker = new Object();

    private void plusPercent() {

        synchronized (locker) {

            double base = sum;

            try {
                Thread.sleep(100 + random.nextInt(100));
            }
            catch (InterruptedException ignored) {
                System.err.println("Interruption");
            }
            double percent = 10;

            base *= 1 + percent / 100;
            sum = base;
        }

        System.out.println(sum);
    }

    private void plusPercent2() {

        try {
            Thread.sleep(100);
        }
        catch (InterruptedException ignored) {
            System.err.println("Interruption");
        }

        double percent = 10;
        double base;

        synchronized (locker) {

            base = sum;
            base *= 1 + percent / 100;
            sum = base;
        }

        //System.out.println(sum);
        System.out.println(base);

        synchronized (activeThreadsLocker) {

            activeThreads -= 1;

            if(activeThreads == 0) {
                onFinish();
            }
        }
    }

    private void onFinish() {
        time2 = System.nanoTime();
        System.out.println((time2 - time1) / 1e9);
    }
}
