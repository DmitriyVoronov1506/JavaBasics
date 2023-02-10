package itstep.learning.asyncs;

import java.util.Date;

public class ThreadDemo {
    private Runnable runnable1;
    private Runnable runnable2;

    public void run() throws InterruptedException {

        System.out.println("Thread Demo start");
        int x = 10;
        // region runnable1
        runnable1 = new Runnable() {
            private int y = x;
            @Override
            public void run() {

                System.out.println("runnable1 start, y = " + y);

                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException ex) {
                    System.out.println("Thread interrupted " + ex.getMessage());
                }

                System.out.println("runnable1 finish");
            }
        };

        // endregion

        runnable2 = () -> {

            System.out.println("runnable2 start");

            try {
                Thread.sleep(100);
            }
            catch (InterruptedException ex) {
                System.out.println("Thread2 interrupted " + ex.getMessage());
            }

            System.out.println("runnable2 finish");
        };

        Runnable runnable3 = () -> {

            System.out.println("runnable3 start");
            long w = 0;

            try {
                while(w < 1e10) {

                    w++;

                    if(Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }
                }
            }
            catch (Exception ex) {
                System.out.println("Thread3 interrupted " + ex.getMessage());
            }

            System.out.println("runnable3 finish");
        };

        //Thread thread3 = new Thread(runnable3);
        //thread3.start();
        //Thread thread2 = new Thread(runnable2);
        //thread2.start();
        //new ParamThread(10).start();
        //Thread thread1 = new Thread(runnable1);
        //thread1.setPriority(Thread.MIN_PRIORITY);
        //thread1.start();
        //Thread.sleep(10);
        //thread1.interrupt();
        //thread3.interrupt();
        //thread2.join();

        Runnable runnable4 = () -> {

            System.out.println("Start preparing Tea. Seconds start: " + new Date().getSeconds());

            try {
                Thread.sleep(3000);
            }
            catch (Exception ex) {
                System.out.println("Thread4 interrupted " + ex.getMessage());
            }

            System.out.println("End preparing Tea. Seconds end: " + new Date().getSeconds());
        };

        Runnable runnable5 = () -> {

            System.out.println("Start preparing toasts and fry eggs. Seconds start: " + new Date().getSeconds());

            try {
                Thread.sleep(2000);
                System.out.println("End frying eggs. Seconds end: " + new Date().getSeconds());

                Thread.sleep(1000);
                System.out.println("End preparing toast. Seconds end: " + new Date().getSeconds());
            }
            catch (Exception ex) {
                System.out.println("Thread5 interrupted " + ex.getMessage());
            }

            System.out.println("End cooking. Seconds end: " + new Date().getSeconds());
        };

        Thread thread4 = new Thread(runnable4);
        thread4.start();

        Thread thread5 = new Thread(runnable5);
        thread5.start();

        thread4.join();
        thread5.join();

        System.out.println("Thread Demo finish");
    }

    static class ParamThread extends Thread {
        private final int param;

        public ParamThread(int param) {
            this.param = param;
        }

        @Override
        public void run() {
            System.out.println("ParamThread with " + param);
        }
    }
}
