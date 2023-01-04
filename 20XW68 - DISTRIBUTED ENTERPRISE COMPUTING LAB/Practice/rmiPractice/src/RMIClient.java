import java.rmi.*;
import java.rmi.registry.*;
public class RMIClient
{
    public static void main(String[] arguments)
    {
        try
        {
            System.setProperty("java.rmi.server.hostname","127.0.0.1");
            MethodImpl methodImpl=(MethodImpl)Naming.lookup("rmi://localhost:7775/sqrt");
            double dbl=methodImpl.getSQRT(100);
            System.out.println("SQRT:"+dbl);
        }
        catch(Exception exec)
        {
            System.out.println("Error--" +exec.toString());
        }
    }//End of main() method
}//End of RMIClient class