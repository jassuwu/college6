public class PrintSpooler {
    public static PrintSpooler instance;

    private PrintSpooler() {

    }

    public static PrintSpooler getInstance() {
        if (instance == null) {
            synchronized (PrintSpooler.class) {
                if (instance == null) instance = new PrintSpooler();
            }
        }
        return instance;
    }
}
