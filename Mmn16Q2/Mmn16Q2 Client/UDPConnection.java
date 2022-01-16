import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPConnection {

    private final int PORT = 8080;

    // returns the weather forecast for the given city
    public String getForecastForCity(String compName, String city) {
        return executeRequest(compName, city);
    }

    // reloads the weather forecast
    public String reloadForecast(String compName) {
        return executeRequest(compName, "reload");
    }

    // gets the target's address and a message and sends it to the server
    private String executeRequest(String compName, String msg) {
        DatagramSocket clientSocket = null;
        try {
            clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(compName);
            sendToServer(clientSocket, IPAddress, msg);
            String forecast = getFromServer(clientSocket);
            clientSocket.close();
            return forecast;
        } catch (Exception e) {
            return "Unable to connect to the server";
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
    private String getFromServer(DatagramSocket socket) {
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
        return modifiedSentence;
    }

}
