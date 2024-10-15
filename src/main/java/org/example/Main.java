package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 8001;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Mock CBS Server is running...");
            while (true) {
                try (Socket socket = serverSocket.accept();
                     InputStream inputStream = socket.getInputStream();
                     OutputStream outputStream = socket.getOutputStream()) {

                    // Read the ISO message from the client
                    byte[] buffer = new byte[1024];
                    int read = inputStream.read(buffer);
                    String receivedMessage = new String(buffer, 0, read);
                    System.out.println("Received ISO Message: " + receivedMessage);

                    String responseMessage = "Response to Balance Inquiry";
                    outputStream.write(responseMessage.getBytes());
                    outputStream.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}