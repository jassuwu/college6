import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MethodImplementation<T> extends Remote {
    public int getCount() throws RemoteException;

    public void insert(String key, T value) throws RemoteException;

    public void delete(String key) throws RemoteException;

    public T lookup(String key) throws RemoteException;
}
