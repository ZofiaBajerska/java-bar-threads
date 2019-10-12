package environment;

import java.util.LinkedList;
import java.util.Queue;

public class Bar {
    private Queue<String> drinks;
    private boolean multiple;

    public Bar(boolean multiple) {
        drinks = new LinkedList<String>();
        this.multiple = multiple;
    }

    public synchronized String take() throws InterruptedException {
        while (drinks.isEmpty()) {
            wait();
        }
        String drink = drinks.poll();
        return drink;
    }
    public synchronized void put(String value) {
        if (!multiple){
            drinks.clear();
        }
        drinks.offer(value);
        System.out.println("drinks waiting: " + drinks.size()); // sprawdzenie czy dostawia drinki!
        notifyAll();
    }
}
