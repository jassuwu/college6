import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class RMIServer extends UnicastRemoteObject implements MethodImplementation<String> {

    private HashMap<String, String> dictionary;

    public RMIServer() throws RemoteException {
        this.dictionary = new HashMap<>();
        System.out.println("[ " + this + " ] Initialized.");
    }

    @Override
    public int getCount() throws RemoteException {
        return this.dictionary.size();
    }

    @Override
    public void insert(String key, String value) throws RemoteException {
        this.dictionary.put(key, value);
    }

    @Override
    public void delete(String key) throws RemoteException {
        this.dictionary.remove(key);
    }

    @Override
    public String lookup(String key) throws RemoteException {
        return this.dictionary.get(key);
    }

    public static void main(String[] args) {
        try {
            RMIServer server = new RMIServer();
            Registry registry = LocateRegistry.createRegistry(7775);
            registry.bind("dictionary", server);
        } catch (RemoteException | AlreadyBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
