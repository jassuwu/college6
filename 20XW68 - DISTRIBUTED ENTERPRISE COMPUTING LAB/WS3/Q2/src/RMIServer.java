import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer extends UnicastRemoteObject implements MethodImplementation {
    public RMIServer() throws RemoteException {
        System.out.println("[ " + this + " ] Initialized.");
    }

    @Override
    public double add(double a, double b) {
        return a + b;
    }

    @Override
    public double sub(double a, double b) {
        return a - b;
    }

    @Override
    public double mul(double a, double b) {
        return a * b;
    }

    @Override
    public double div(double a, double b) {
        return a / b;
    }

    public static void main(String[] args) {
        try {
            RMIServer server = new RMIServer();
            Registry registry = LocateRegistry.createRegistry(7775);
            registry.bind("calc", server);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
