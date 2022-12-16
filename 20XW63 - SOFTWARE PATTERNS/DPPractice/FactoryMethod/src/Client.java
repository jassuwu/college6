import factory.Dialog;
import factory.HtmlDialog;
import factory.WindowsDialog;

public class Client {
    private static Dialog dialog;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    static void configure() {
        if (System.getProperty("os.name").equals("Windows 10")) {
            dialog = new WindowsDialog();
        } else if (System.getProperty("os.name").equals("Chromium")) {
            dialog = new HtmlDialog();
        } else {
            throw new RuntimeException("Error! Unknown operating system.");
        }
    }

    static void runBusinessLogic() {
        dialog.renderWindow();
    }
}
