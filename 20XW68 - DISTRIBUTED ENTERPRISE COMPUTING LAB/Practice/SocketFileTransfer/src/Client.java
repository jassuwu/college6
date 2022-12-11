import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 65456);
        byte[] buffer = new byte[1000];

        FileOutputStream fos = new FileOutputStream(new File(".").getCanonicalPath() + "/src/rcvd.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        InputStream is = socket.getInputStream();

        int bytesRead = 0;
        while ((bytesRead = is.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }
        bos.flush();
        bos.close();
        socket.close();
        System.out.println("File receival successful.");
    }
}
