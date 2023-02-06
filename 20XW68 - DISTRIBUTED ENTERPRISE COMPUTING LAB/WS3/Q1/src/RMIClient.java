import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            @SuppressWarnings("unchecked") // Don't do this, this is really bad. I did it so there'd be no ugly
                                           // warnings.
            MethodImplementation<String> methodImplementation = (MethodImplementation<String>) Naming
                    .lookup("rmi://localhost:7775/dictionary");
            System.out.println("Displaying the dictionary size before doing anything: ");
            System.out.println(methodImplementation.getCount());
            System.out.println("Adding few words...");
            methodImplementation.insert("one", "one");
            methodImplementation.insert("two", "two");
            methodImplementation.insert("three", "three");
            methodImplementation.insert("four", "four");
            methodImplementation.insert("five", "five");
            System.out.println("Displaying the dictionary size after 5 insertions: ");
            System.out.println(methodImplementation.getCount());
            System.out.println("Removing last two values and displaying count:");
            methodImplementation.delete("four");
            methodImplementation.delete("five");
            System.out.println(methodImplementation.getCount());
            System.out.println("Looking up the first and the third additions:");
            System.out.println("First: " + methodImplementation.lookup("one"));
            System.out.println("Third: " + methodImplementation.lookup("three"));
            System.out.println("End of run---");
        } catch (Exception exec) {
            System.out.println("Error--" + exec.toString());
        }
    }
}
