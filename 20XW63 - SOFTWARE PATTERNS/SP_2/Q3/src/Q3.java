import Document.*;

public class Q3 {
        // UNCOMMENT ONE OF THE FOLLOWING
//    private static final String CONFIG = "REPORT";
   private static final String CONFIG = "RESUME";
    private static Document document;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    static void configure() {
        if (CONFIG.equals("REPORT")) {
            document = new Report();
        } else if (CONFIG.equals("RESUME")) {
            document = new Resume();
        } else {
            throw new RuntimeException("Error! Unknown doc CONFIG.");
        }
    }

    static void runBusinessLogic() {
        document.addPages();
        document.displayDocument();
    }
}