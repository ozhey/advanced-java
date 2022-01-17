import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class WeatherServer {

    private CitiesForecast citiesForecast;
    private static final int PORT = 8080;

    // constructor. starts listening to requests
    public WeatherServer() {
        citiesForecast = new CitiesForecast();
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(PORT);
            System.out.println("Server listening...");
        } catch (SocketException e) {
            System.out.println(e);
        }
        while (true) {
            try {
                sendAndRecieve(serverSocket);
            } catch (Exception e) {
                System.out.println(e);
                serverSocket.close();
            }
        }
    }

    public static void main(String[] args) {
        new WeatherServer();
    }

    public void sendAndRecieve(DatagramSocket serverSocket) {
        try {
            // receive from client
            byte[] receive = new byte[1024];
            DatagramPacket packet = new DatagramPacket(receive, receive.length);
            serverSocket.receive(packet);
            // construct response
            String request = new String(packet.getData()).substring(0, packet.getLength());
            String response = constructResponse(request);
            // send to client
            int port = packet.getPort();
            InetAddress address = packet.getAddress();
            packet = new DatagramPacket(response.getBytes(), response.getBytes().length, address, port);
            serverSocket.send(packet);
        } catch (IOException e) {
            System.out.println(e);
            serverSocket.close();
        }
    }

    // recieves the message from the client and constructs a proper response
    private String constructResponse(String request) {
        System.out.println(request);
        if (request.equals("reload")) {
            citiesForecast.readWeatherForecast();
            return "Weather forecast updated from file";
        }
        String forecast = citiesForecast.getForecastForCity(request);
        if (forecast == null) {
            return "No weather forecast for the specified city was found";
        }
        return forecast;
    }
}