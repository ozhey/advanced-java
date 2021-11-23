package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

// a chat client handler.
public class ChatThread extends Thread {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private ChatThread target; // the person we're chatting with
    private WaitingRoom waitingRoom;
    private String username;

    // constructor, inits a new chat client handler
    public ChatThread(Socket s, WaitingRoom wt) {
        try {
            socket = s;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            waitingRoom = wt;
            username = bufferedReader.readLine();
        } catch (IOException e) {
            closeResources();
        }
    }

    // starts the socket
    // reads messages from the client and sends them
    @Override
    public void run() {
        if (socketIsAlive()) {
            sendMessage(this, "Waiting for someone to chat with...");
            target = waitingRoom.getChatBuddy(this);
            if (target != null) {
                sendMessage(this, "Connection established, you are now chatting with " + target.username);
            }
            while (socketIsAlive() && target != null) {
                sendMessage(target, readMessage());
            }
        }
    }

    // sends a message to the chat buddy
    private void sendMessage(ChatThread to, String message) {
        // message equals null if the buffered reader is closed. in this case we will terminate
        if (message == null) {
            closeResources();
            return;
        }
        try {
            to.bufferedWriter.write(message);
            to.bufferedWriter.newLine();
            to.bufferedWriter.flush();

        } catch (IOException e) {
            closeResources();
        }
    }

    // closes all resources
    public void closeResources() {
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
            System.out.println("Failed to close resources");
        }
    }

    // tries to read a message from the socket
    public String readMessage() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            closeResources();
            return null;
        }
    }

    // returns true if the socket has been connected, and still hasn't closed
    public boolean socketIsAlive() {
        return socket.isConnected() && !socket.isClosed();
    }
}
