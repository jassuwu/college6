package Document;
import Page.Page;
import java.util.ArrayList;

public abstract class Document {
    public ArrayList<Page> pages = new ArrayList<Page>();
    public void addPages() {
        for(int i = 0; i<3; i++) {
           Page newPage = createInstance();
           newPage.writeSomething();
           pages.add(newPage);
        }
    }

    public void displayDocument() {
        for (Page page: pages) {
            page.printContent();
        }
    }

    public abstract Page createInstance();
}
