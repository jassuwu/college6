import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Folder implements Item {
    private String name;
    private ArrayList<Item> itemList;
    public Folder(String name) {
        this.name = name;
        this.itemList = new ArrayList<>();
    }
    @Override
    public String getName() {
        return this.name;
    }

    public ArrayList<Item> getContent() {
        return this.itemList;
    }
    @Override
    public void delete(){
        System.out.println( this.getName() + " deleted from universe.");
    }
    public void Add(Item item) {
        this.itemList.add(item);
    }

    public void Remove(Item item) {
        item.delete();
        this.itemList.remove(item);
    }

    public Item getItem(String name) throws FileNotFoundException {
        for(Item item: this.itemList) {
            if(item.getName().equals(name)) return item;
        }
        throw new FileNotFoundException("you idiot. that don't exist.");
    }
}
