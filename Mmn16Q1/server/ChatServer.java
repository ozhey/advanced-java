package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    private ServerSocket serverSocket;
    private Socket socket;
    private WaitingRoom waitingRoom;

    public ChatServer() {
        waitingRoom = new WaitingRoom();
        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("Server listening...");
            while (!serverSocket.isClosed()) {
                socket = serverSocket.accept();
                new ChatThread(socket, waitingRoom).start();;
            }
        } catch (IOException e) {
            System.out.println("An error occured: " + e);
        }
        try {
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Failed to close resource");
        }
    }

    public static void main(String[] args) {
        new ChatServer();
    }

}
