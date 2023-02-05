import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class RMIServer extends UnicastRemoteObject implements MethodImpl {
    public RMIServer() throws RemoteException {
        System.out.println("The server is instantiated.");
    }

    public double getSQRT(double dbl) {
        return Math.sqrt(dbl);
    }

    public static void main(String[] arguments) {
        try {
            RMIServer server = new RMIServer();
            Registry registry = LocateRegistry.createRegistry(7775);
            registry.bind("sqrt", server);
        } catch (Exception e) {
            System.out.println("Error -- " + e.toString());
        }
    }// End of main() method
}// End of RMIServer class
