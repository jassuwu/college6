import java.lang.*;
import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        try{
            Socket clientSocket = new Socket("localhost", 6789);
            BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            String sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');
            clientSocket.close();
            inFromUser.close();
            outToServer.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}