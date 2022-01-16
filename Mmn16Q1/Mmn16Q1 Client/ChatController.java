import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ChatController {

    private Connection connection;
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
            connection = new Connection(host.getText(), Integer.parseInt(port.getText()), username.getText());
            connection.listenForMessage(chatText, msgBtn, host, port, username, connectBtn, disconnectBtn);
            uiStateConnected();
        } catch (IOException | NumberFormatException e) {
            addText("An error occured, could not establish connection");
            uiStateDisconnected();
        }
    }

    @FXML
    // disconnect from the server / chat buddy
    private void disconnect() {
        try {
            connection.writeMsg("disconnect");
            connection.closeConnection();
        } catch (IOException e) {
            addText("An error occured, failed to send disconnection message to the other client or to close resources");
        }
        // msgBtn is disabled when we try to disconnect while still waiting for someone
        // to chat with
        if (msgBtn.isDisabled()) {
            try {
                connection.wakeAndKillServerThread(host.getText(), Integer.parseInt(port.getText()));
            } catch (NumberFormatException | IOException e) {
                addText("failed to kill the server's thread that listens to this socket");
            }
        }
        uiStateDisconnected();
        addText("Disconnected");
    }

    @FXML
    // sends the message to the chat buddy
    private void sendMessage() {
        addText("You: " + message.getText());
        try {
            connection.writeMsg(username.getText() + ": " + message.getText());
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

}