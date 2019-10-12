package model;

import environment.Bar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Random;

public class Barman implements Runnable{
        private Bar bar;
        private ArrayList<String> menu = new ArrayList<String>();

        public Barman(Bar bar) {
            this.bar = bar;
        }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                break;
            }
            int p = random.nextInt(menu.size());
            String drink = menu.get(p);
            bar.put(drink);
        }
    }
    public void loadMenuFromFile (String file) {
        try {
            Reader freader = new FileReader(file);

            BufferedReader breader = new BufferedReader(freader);
            String line;
            while ((line = breader.readLine()) !=null){
                menu.add(line);
            }
            freader.close();
        } catch (IOException e) {
        }
    }

}
