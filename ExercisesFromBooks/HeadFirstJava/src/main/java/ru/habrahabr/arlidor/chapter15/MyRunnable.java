package ru.habrahabr.arlidor.chapter15;

public class MyRunnable implements Runnable {

    public static void main(String[] args) {
        Runnable threadJob = new MyRunnable();
        Thread myThread = new Thread(threadJob);
        myThread.start();
        System.out.println("back in main");
    }

    @Override
    public void run() {
        go();
    }

    public void go() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        doMore();
    }

    public void doMore() {
        System.out.println("top o' the stack");
    }

}
