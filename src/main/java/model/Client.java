package model;

import environment.Bar;

import static java.lang.Thread.*;

/**
 * Client class consuming drinks putted on the bar.
 *
 * @author Zofia B.
 */
public class Client implements Runnable {
    /**
     * Reference to bar from which the Client is taking the drinks.
     */
    private Bar bar;
    /**
     * Name of the client.
     */
    private String name;

    /**
     * Constructor of the class.
     * @param bar reference to a bar.
     * @param name name of the client.
     */
    public Client(Bar bar, String name) {
        this.bar = bar;
        this.name = name;
    }

    @Override
    /**
     * Method that is running in a Thread taking drink from a bar and consuming  it as many seconds as the name length.
     */
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

