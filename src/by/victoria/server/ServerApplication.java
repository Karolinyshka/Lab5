package by.victoria.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApplication {
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(6666)) {
            System.out.println("The multi-threaded server has started");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection established\n" +
                        "IP:" + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                new Thread(new RequestHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
   