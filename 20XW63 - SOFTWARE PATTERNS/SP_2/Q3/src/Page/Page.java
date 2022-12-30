package Page;

public abstract class Page {
    public String content;
    public boolean isEmpty() {
        return content.length() == 0;
    }
    public abstract void writeSomething();

    public void printContent() {
        System.out.println(content);
    }
}
