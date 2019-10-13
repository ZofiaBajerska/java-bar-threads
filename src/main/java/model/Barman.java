package model;

import environment.Bar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Barman class with txt menu for drink management.
 *
 * @author Zofia B.
 */
public class Barman implements Runnable{
    /**
     * Logger for logging unusual situation and errors.
     */
    private static final Logger log = Logger.getLogger(Barman.class.getName());
    /**
     * Reference to a bar where Barman is putting drinks on.
      */
    private Bar bar;
    /**
     * List of the drinks to put on a bar read from a file.
     */
    private ArrayList<String> menu = new ArrayList<>();

    /**
     * Constructor for the class.
     *
     * @param bar reference to a bar where Barman is putting drinks on.
     */
    public Barman(Bar bar) {
        this.bar = bar;
    }
    /**
     * Method that is running in a Thread putting a drink every 2 sek from menu.
     */
    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                break;
            }
            int p = random.nextInt(menu.size());
            String drink = menu.get(p);
            bar.put(drink);
        }
    }

    /**
     * Method which is reading menu from file.
     *
     * @param file name of txt file with menu in it.
     */
    public void loadMenuFromFile (String file) {
        try {
            Reader freader = new FileReader(file);
            BufferedReader breader = new BufferedReader(freader);
            String line;

            while ((line = breader.readLine()) != null){
                menu.add(line);
            }
            freader.close();
        } catch (IOException e) {
            log.log(Level.WARNING, e.getMessage(), e);
        }
    }
}
