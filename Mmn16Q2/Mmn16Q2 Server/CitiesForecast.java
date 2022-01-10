import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

// Forecast contains the weather forecast of many cities
public class CitiesForecast {

    private HashMap<String, String> forecast;
    private static final String DELIMITER = ":";
    // constructor, reads the weather forecast from a file
    public CitiesForecast() {
        forecast = new HashMap<String, String>();
        readWeatherForecast();
    }

    // re-read the weather from the file, allows updating the data.
    public void readWeatherForecast() {
        try (Scanner input = new Scanner(new File("weatherForecast.txt"))) {
            while (input.hasNext()) {
                String st = input.nextLine();
                int delimiterIndex = st.indexOf(DELIMITER);
                String city = st.substring(0, delimiterIndex);
                String weather = st.substring(delimiterIndex + 1, st.length() - 1);
                forecast.put(city, weather);
            }
        } catch (FileNotFoundException e) {
            System.out.println("weather forecast file not found" + e);
            forecast.clear();
        }
    }
    
    // return the weather forecast for the named city.
    // if there's no information for the named city, null will be returned.
    public String getForecastForCity(String cityName) {
        return forecast.get(cityName);
    }

}
