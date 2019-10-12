package model;

import environment.Bar;

import static java.lang.Thread.*;

public class Client implements Runnable {
    private Bar bar;
    private String name;

    public Client(Bar bar, String name) {
        this.bar = bar;
        this.name = name;
    }


    @Override
    public void run() {
        while (true) {
            try {
                String drink = bar.take();
                System.out.println(name + " " + drink);
                sleep(1000 * drink.length());
            } catch (InterruptedException e) {
                // e.printStackTrace();
                break;
            }

        }

    }
}

