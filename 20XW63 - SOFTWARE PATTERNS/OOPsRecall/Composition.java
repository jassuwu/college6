public class Composition {
    public static void main(String[] args) {
        System.out.println("test?");
    }
}

class Page {
    private int number;
    private String material;

    public Page(int number, String material) {
        this.number = number;
        this.material = material;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMaterial() {
        return this.getMaterial;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}


class Book {
    private String title;
    private Page[] pages;

    public Book(String title, Page[] pages) {
        this.title = title;
        this.pages = pages;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Page[] getPages() {
        return this.pages;
    }

    public void setPages(Page[] pages) {
        this.pages = pages;
    }
}