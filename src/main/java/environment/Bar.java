package environment;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Bar class for drink management.
 *
 * @author Zofia B.
 */
public class Bar {
    /**
     * Collection for storing drinks put by Barman and waiting to be taken by Client.
     */
    private Queue<String> drinks;
    /**
     * Stores mode of operation(single drink or multiple drinks on the bar).
     */
    private boolean multiple;

    /**
     * Constructor for the class.
     *
     * @param multiple configures mode of operation (single drink or multiple drinks on the bar).
     */
    public Bar(boolean multiple) {
        drinks = new LinkedList<String>();
        this.multiple = multiple;
    }

    /**
     * Method to take the drink, if drink is not ready - waits.
     *
     * @return String with a name of a drink taken from a bar.
     *
     * @throws InterruptedException when interrupted.
     */
    public synchronized String take() throws InterruptedException {
        while (drinks.isEmpty()) {
            wait();
        }
        String drink = drinks.poll();
        return drink;
    }

    /**
     * Method to put the drink on the bar.
     *
     * @param drink string with a name of a drink is put on a bar.
     */
    public synchronized void put(String drink) {
        if (!multiple){
            drinks.clear();
        }
        drinks.offer(drink);
        System.out.println("drinks waiting: " + drinks.size()); // sprawdzenie czy dostawia drinki!
        notifyAll();
    }
}
