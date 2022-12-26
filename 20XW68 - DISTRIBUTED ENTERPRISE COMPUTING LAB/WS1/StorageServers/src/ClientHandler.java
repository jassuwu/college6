/*
 * @20PW14
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;

public class ClientHandler implements Runnable {

    private HashMap<String, File> storage;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private ObjectOutputStream objOut;
    private ObjectInputStream objIn;
    private String username;

    public ClientHandler(Socket socket) {
        try {
            this.storage = new HashMap<>();
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.objOut = new ObjectOutputStream(socket.getOutputStream());
            this.objIn = new ObjectInputStream(socket.getInputStream());
            this.username = this.bufferedReader.readLine();
            System.out.println("[CLIENTHANDLER] initialized storage for " + this.username + ".");
        } catch (IOException e) {
            System.err.println("IOEXCEPTION IN CLIENTHANDLER CONSTRUCTOR");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (socket != null) {
                socket.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (this.socket.isConnected()) {
            try {
                String choiceFromClient = bufferedReader.readLine();
                if (choiceFromClient.equals("1")) {
                    File fileFromClient = (File) objIn.readObject();
                    this.storage.put(this.username, fileFromClient);
                    System.out.println("[" + this.username + "'s CH" + "]File from " + this.username
                            + " '" + fileFromClient.getName() + "' stored in storage.");
                } else if (choiceFromClient.equals("2")) {
                    objOut.writeObject(this.storage.get(this.username));
                    System.out.println("File sent from storage to " + this.username + ".");
                    this.storage.remove(this.username);
                    System.out.println("Deallocated the storage space that was allocated for " + this.username + ".");
                } else {
                    continue;
                }
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("IOEXCEPTION or CLASSNOTFOUNDEXCEPTION IN CLIENTHANDLER RUN");
                closeEverything(this.socket, this.bufferedReader, this.bufferedWriter);
                break;
            }
        }
    }
}
