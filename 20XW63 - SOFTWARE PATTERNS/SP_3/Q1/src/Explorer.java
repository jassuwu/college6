import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class Explorer implements Item {
    private Item item;
    @Override
    public void delete() {
        this.item.delete();
    }

    @Override
    public String getName() {
        return item.getName();
    }


    public static void main(String[] args) {
        Folder root = new Folder("root");
        Folder system = new Folder("system");
        Folder data = new Folder("data");
        system.Add(new File("os.img"));
        data.Add(new File("appdata.zip"));
        root.Add(system);
        root.Add(data);


         // Let's go thru root and display everything
        System.out.println("Content of root:");
        for(Item item: root.getContent()) {
             System.out.print(item.getName() + ", ");
         }
        System.out.println();
        System.out.println();

        // We delete everything from the system
        System.out.println("Purging /system:");
         for(Item item: system.getContent()) {
             item.delete();
         }
        System.out.println();

         // We delete everything from the data
        System.out.println("Purging /data:");
        for(Item item: data.getContent()) {
            item.delete();
        }
        System.out.println();
    }
}
