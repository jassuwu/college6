import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// Singleton class that implements serializable interface also
public class M7SerializationAndSingleton implements Serializable {
    private static final long serialVersionUID = -7604766932017737115L;

    private M7SerializationAndSingleton() {
    }

    private static class SingletonHelper {
        private static final M7SerializationAndSingleton INSTANCE = new M7SerializationAndSingleton();
    }

    public static M7SerializationAndSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    // Implemented to prevent the destruction of Singleton
    protected Object readResolve() {
        return getInstance();
    }

    // Test client
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        M7SerializationAndSingleton instanceOne = M7SerializationAndSingleton.getInstance();
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("file.ser"));
        out.writeObject(instanceOne);
        out.close();

        // deserialize the file to object
        ObjectInput in = new ObjectInputStream(new FileInputStream("file.ser"));
        M7SerializationAndSingleton instanceTwo = (M7SerializationAndSingleton) in.readObject();
        in.close();

        System.out.println(instanceOne.hashCode());
        System.out.println(instanceTwo.hashCode());

        // Running the above results in two different hashcodes unless readResolve()
        // method is implemented.
    }
}