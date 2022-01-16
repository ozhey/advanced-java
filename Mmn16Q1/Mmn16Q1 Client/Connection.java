import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Connection {
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    public Connection(String host, int port, String username) throws UnknownHostException, IOException {
        Socket socket = new Socket(host, port);
        this.socket = socket;
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writeMsg(username);
    }

    // sends a message to the chat buddy
    public void writeMsg(String msg) throws IOException {
        bufferedWriter.write(msg);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    // closes all resources
    public void closeConnection() throws IOException {
        if (socket != null) {
            socket.close();
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        if (bufferedWriter != null) {
            bufferedWriter.close();
        }
    }

    // listens for new messages from the server and appends them to the chat text
    public void listenForMessage(Text chatText, Button msgBtn, TextField host, TextField port, TextField username,
            Button connectBtn, Button disconnectBtn) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromChat;
                while (socket.isConnected() && !socket.isClosed()) {
                    try {
                        msgFromChat = bufferedReader.readLine();
                        if (msgFromChat != null && msgFromChat.equals("disconnect")) {
                            closeConnection();
                            connectBtn.setDisable(false);
                            host.setDisable(false);
                            port.setDisable(false);
                            username.setDisable(false);
                            msgBtn.setDisable(true);
                            disconnectBtn.setDisable(true);
                            chatText.setText(chatText.getText() + "The connection has ended by the other client" + "\n");
                        } else if (msgFromChat != null && msgFromChat.startsWith("Connection established")) {
                            msgBtn.setDisable(false);
                            chatText.setText(chatText.getText() + msgFromChat + "\n");
                        } else {
                            chatText.setText(chatText.getText() + msgFromChat + "\n");
                        }
                    } catch (IOException e) {
                        try {
                            closeConnection();
                        } catch (IOException e1) {
                            System.out.println("failed to close resources");
                        }
                    }
                }
            }
        }).start();
    }

    // if we disconnect before starting to chat with someone, the server thread is
    // still waiting for a second client to connect. we need to manually wake it so
    // it can be closed.
    public void wakeAndKillServerThread(String host, int port) throws IOException {
            Socket s = new Socket(host, port);
            s.close();
    }

}