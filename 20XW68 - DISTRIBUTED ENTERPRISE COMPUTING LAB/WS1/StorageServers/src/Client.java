import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private ObjectOutputStream objOut;
    private ObjectInputStream objIn;
    private String username;

    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("What will you be known as? ");
        String username = sc.nextLine();
        Socket socket = new Socket("localhost", 65456);
        Client client = new Client(socket, username);
        client.sendMessage();
        sc.close();
    }

    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.objOut = new ObjectOutputStream(socket.getOutputStream());
            this.objIn = new ObjectInputStream(socket.getInputStream());
            this.username = username;
        } catch (IOException e) {
            System.err.println("IOEXCEPTION IN CLIENT CONSTRUCTOR.");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (this.bufferedReader != null) {
                bufferedReader.close();
            }
            if (this.bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (this.socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            Scanner sc = new Scanner(System.in);
            while (socket.isConnected()) {
                System.out.println("What do you want to do then?");
                System.out.println("1. Store message.");
                System.out.println("2. Retrieve message.");
                System.out.println("3. Exit.");
                String key = sc.nextLine();
                switch (key) {
                    case "1":
                        bufferedWriter.write("1");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        System.out.println("Enter the filename inside the src folder: ");
                        objOut.writeObject(new File(new File(".").getCanonicalPath() + "/src/" + sc.nextLine()));
                        System.out.println("The file has been sent from here.");
                        break;
                    case "2":
                        bufferedWriter.write("2");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        System.out.println("Retrieving the file...");
                        File returnedFile = (File) objIn.readObject();
                        System.out.println("Retrieved the file.");
                        System.out.println("What do you want to do with the file?");
                        System.out.println("1. Save it in the '/rcvd' directory. (Make sure it exists)");
                        System.out.println("2. Discard it. I only wanted to clear my storage on the server.");
                        Integer choice = Integer.parseInt(sc.nextLine());
                        switch (choice) {
                            case 1:
                                System.out.println("Saving it in the '/rcvd' directory...");
                                FileOutputStream fos = new FileOutputStream(
                                        new File(".").getCanonicalPath() + "/rcvd/" + returnedFile.getName());
                                FileInputStream fis = new FileInputStream(returnedFile);
                                byte[] buf = new byte[1024];
                                int hasRead = 0;
                                while ((hasRead = fis.read(buf)) > 0) {
                                    fos.write(buf, 0, hasRead);
                                }
                                System.out.println("Saved it in the '/rcvd' directory.");
                                fis.close();
                                fos.close();
                                break;
                            case 2:
                                System.out.println("cool. I just threw it away. it went poof.");
                                break;
                            default:
                                System.out.println("Bad choice I'm throwing it away anyways.");
                                break;
                        }
                        break;
                    case "3":
                        bufferedWriter.write("3");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        System.out.println("EXITING...");
                        return;
                    default:
                        System.err.println("Invalid choice broski.");
                        break;
                }
            }
            sc.close();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("IOEXCEPTION or CLASSNOTFOUNDEXCEPTION IN CLIENT SENDMESSAGE.");
            e.printStackTrace();
            closeEverything(this.socket, this.bufferedReader, this.bufferedWriter);
        }
    }
}
