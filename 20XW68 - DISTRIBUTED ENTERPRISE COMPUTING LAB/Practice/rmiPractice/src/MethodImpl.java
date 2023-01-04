import java.rmi.*;
public interface MethodImpl extends Remote {
    double getSQRT(double dbl) throws RemoteException;
}
