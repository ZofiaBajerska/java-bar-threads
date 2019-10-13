import environment.Bar;
import model.Barman;
import model.Client;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Aplication Main class
 *
 * @author Zofia B.
 */
public class Main {

    /**
     * Logger for logging unusual situation and errors.
     */
    private static final Logger log = Logger.getLogger(Main.class.getName());

    /**
     * Application main method.
     *
     * @param args application command line arguments
     */
    public static void main(String[] args) {
        Bar bar = new Bar(true);
        Barman barman = new Barman(bar);
        barman.loadMenuFromFile("menu.txt");
        Client[]  clients = new Client[5];
        Thread[] threads = new Thread[6];
        threads[5] = new Thread(barman);
        threads[5].start();
        for (int i = 0; i <= 4; i++ ){
            clients[i] = new Client(bar, "Klient " + i);
            threads[i] = new Thread(clients[i]);
            threads[i].start();
        }

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        for (int i = 0; i <= 5; i++ ) {
            try{
                threads[i].interrupt();
                threads[i].join();
            } catch (InterruptedException e){
                log.log(Level.WARNING, e.getMessage(), e);
            }
        }
    }
}
