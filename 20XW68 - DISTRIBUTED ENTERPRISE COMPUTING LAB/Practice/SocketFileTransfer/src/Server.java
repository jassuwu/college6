import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(65456);
        Socket socket = serverSocket.accept();
        System.out.println(socket.getInetAddress().getHostName() + " connected to the server.");

        // The file
        File file = new File(new File(".").getCanonicalPath() + "/src/file.txt");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        OutputStream os = socket.getOutputStream();
        byte[] buffer;
        long fLength = file.length();
        long current = 0;
        while (current != fLength) {
            int size = 10000;
            if (fLength - current >= size)
                current += size;
            else {
                size = ((int) (fLength - current));
                current = fLength;
            }
            buffer = new byte[size];
            bis.read(buffer, 0, size);
            os.write(buffer);
            System.out.println("Sending file ... " + (current * 100) / fLength + "% complete.");
        }
        os.flush();
        bis.close();
        socket.close();
        serverSocket.close();
        System.out.println("File transfer complete.");
    }
}
