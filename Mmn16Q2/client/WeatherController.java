package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

// the controller for the weather app
public class WeatherController {

    private static final String[] cities = new String[] { "Tel Aviv", "London", "Paris", "Prague" };

    @FXML
    private ChoiceBox<String> select;
    @FXML
    private Text weatherText;
    @FXML
    private TextField compName;
    private final int PORT = 8080;

    // initializes the choicebox's options
    @FXML
    private void initialize() {
        select.getItems().addAll(cities);
        select.setValue("");
    }

    // requests the weather for the selected city from the server
    @FXML
    private void showWeather() {
        executeRequest("show");
    }

    // asks the server to reload the weather forecast
    @FXML
    private void reloadWeather() {
        executeRequest("reload");
    }

    // executes the client's request - this can be a weather forecast request or a
    // reload request
    private void executeRequest(String action) {
        DatagramSocket clientSocket = null;
        String msg = "";
        if (action.equals("reload")) {
            msg = "reload";
        } else {
            msg = select.getValue();
        }
        try {
            clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(compName.getText());
            sendToServer(clientSocket, IPAddress, msg);
            getFromServer(clientSocket);
            clientSocket.close();
        } catch (Exception e) {
            weatherText.setText("Unable to connect to the server");
        }
    }

    // sends the request to the server
    private void sendToServer(DatagramSocket socket, InetAddress ip, String msg) {
        byte[] data = new byte[1024];
        data = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, ip, PORT);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // gets the response from the server and loads the payload to the UI
    private void getFromServer(DatagramSocket socket) {
        byte[] receiveData = new byte[1024];
        DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] data = packet.getData();
        int len = packet.getLength();
        String modifiedSentence = new String(data).substring(0, len);
        weatherText.setText(modifiedSentence);
    }

}
