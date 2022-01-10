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
    private UDPConnection udpConnection = new UDPConnection();

    // initializes the choicebox's options
    @FXML
    private void initialize() {
        select.getItems().addAll(cities);
        select.setValue("");
    }

    // requests the weather for the selected city from the server
    @FXML
    private void showWeather() {
        weatherText.setText(udpConnection.getForecastForCity(compName.getText(), select.getValue()));
    }

    // asks the server to reload the weather forecast
    @FXML
    private void reloadWeather() {
        weatherText.setText(udpConnection.reloadForecast(compName.getText()));
    }

}
