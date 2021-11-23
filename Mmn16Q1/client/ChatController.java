package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ChatController {

    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    @FXML
    private TextField host;
    @FXML
    private TextField port;
    @FXML
    private TextField username;
    @FXML
    private TextField message;
    @FXML
    private Text chatText;
    @FXML
    private Button connectBtn;
    @FXML
    private Button disconnectBtn;
    @FXML
    private Button msgBtn;

    @FXML
    // connect to the server and start listening for messages
    private void connect() {
        try {
            Socket socket = new Socket(host.getText(), Integer.parseInt(port.getText()));
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writeMsg(username.getText());
            uiStateConnected();
            listenForMessage();
        } catch (IOException | NumberFormatException e) {
            addText("An error occured, could not establish connection");
            uiStateDisconnected();
        }
    }

    @FXML
    // disconnect from the server / chat buddy
    private void disconnect() {
        try {
            writeMsg("disconnect");
        } catch (IOException e) {
            addText("An error occured, failed to send disconnect message to the other client");
        }
        closeResources();
        // msgBtn is disabled when we try to disconnect while still waiting for someone
        // to chat with
        if (msgBtn.isDisabled()) {
            wakeAndKillServerThread();
        }
        uiStateDisconnected();
        addText("Disconnected");
    }

    @FXML
    // sends the message to the chat buddy
    private void sendMessage() {
        addText("You: " + message.getText());
        try {
            writeMsg(username.getText() + ": " + message.getText());
        } catch (Exception e) {
            addText("An error occured, failed to send message");
        }
        message.setText("");
    }

    @FXML
    // clears the chat text
    private void clearChatText() {
        chatText.setText("");
    }

    // adds the text to the chat's text in a new line
    private void addText(String text) {
        chatText.setText(chatText.getText() + text + "\n");
    }

    // sends a message to the chat buddy
    private void writeMsg(String msg) throws IOException {
        bufferedWriter.write(msg);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    // listens for new messages from the server and appends them to the chat text
    private void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromChat;
                while (socket.isConnected() && !socket.isClosed()) {
                    try {
                        msgFromChat = bufferedReader.readLine();
                        if (msgFromChat != null && msgFromChat.equals("disconnect")) {
                            closeResources();
                            uiStateDisconnected();
                            addText("The connection has ended by the other client");
                        } else if (msgFromChat != null && msgFromChat.startsWith("Connection established")) {
                            msgBtn.setDisable(false);
                            addText(msgFromChat);
                        } else {
                            addText(msgFromChat);
                        }
                    } catch (IOException e) {
                        closeResources();
                    }
                }
            }
        }).start();
    }

    // if we disconnect before starting to chat with someone, the server thread is
    // still waiting for a second client to connect. we need to manually wake it so
    // it can be closed.
    private void wakeAndKillServerThread() {
        try {
            Socket s = new Socket(host.getText(), Integer.parseInt(port.getText()));
            s.close();
        } catch (NumberFormatException | IOException e) {
            addText("Error - failed to wake and kill the server thread, now it will keep waiting");
        }
    }

    // enables/disables the buttons and text fields accordingly to the fact that we
    // are now connected
    private void uiStateConnected() {
        connectBtn.setDisable(true);
        host.setDisable(true);
        port.setDisable(true);
        username.setDisable(true);
        disconnectBtn.setDisable(false);

    }

    // enables/disables the buttons and text fields accordingly to the fact that we
    // are now disconnected
    private void uiStateDisconnected() {
        connectBtn.setDisable(false);
        host.setDisable(false);
        port.setDisable(false);
        username.setDisable(false);
        msgBtn.setDisable(true);
        disconnectBtn.setDisable(true);
    }

    // closes all resources
    private void closeResources() {
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
        } catch (Exception e) {
            addText("An error occured, could not close resources");
        }
    }

}