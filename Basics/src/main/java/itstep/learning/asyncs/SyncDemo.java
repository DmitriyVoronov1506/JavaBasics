package itstep.learning.asyncs;

import java.util.Random;

public class SyncDemo {
    private Random random;
    long time1;
    long time2;

    public void run() {

        //random = new Random();
        //sum = 100;
        //activeThreads = 12;

        time1 = System.nanoTime();

        //for(int i = 0; i < 12; ++i) {
        //    new Thread(this::plusPercent2).start();
        //}

        getQEAnswers(2,15,-20);
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

    private final Object qELocker = new Object();
    private final Object tLocker = new Object();
    private int count = 2;

    private void getQEAnswers(int a, int b, int c) {

        System.out.println(a + "x^2+" + b + "x" + c + "=0");

        int D = b * b - (4 * a * c);

        System.out.println("Discriminant: " + D);

        Runnable firstRoot = () -> {

            double firstAnswer;
            double temp1;

            if (D >= 0) {

                synchronized(qELocker) {

                    temp1 = (-b - Math.sqrt(D)) / (2 * a);
                    firstAnswer = temp1;
                }

                System.out.println("Answer 1: " + firstAnswer);
            }
            else {
                System.out.println("Doesn't exist");
            }

            synchronized (tLocker) {

                count -= 1;
                if(count == 0) onFinish();
            }
        };

        Runnable secondRoot = () -> {

            double secondAnswer;
            double temp2;

            if (D >= 0) {

                synchronized(qELocker) {

                    temp2 = (-b + Math.sqrt(D)) / (2 * a);
                    secondAnswer = temp2;
                }

                System.out.println("Answer 2: " + secondAnswer);
            }
            else {
                System.out.println("Doesn't exist");
            }

            synchronized (tLocker) {

                count -= 1;
                if(count == 0) onFinish();
            }
        };

        new Thread(firstRoot).start();
        new Thread(secondRoot).start();
    }
}
