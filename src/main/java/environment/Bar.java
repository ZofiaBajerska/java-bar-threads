package environment;

public class Bar {
    private String value = null;
        public synchronized String take() throws InterruptedException {
            while (value == null) {
                wait();
            }
            String drink = value;
            value = null;
            return drink;
        }
        public synchronized void put(String value) {
            this.value = value;
            notifyAll();
        }
    }
