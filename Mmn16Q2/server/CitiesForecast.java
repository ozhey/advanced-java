package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

// Forecast contains the weather forecast of many cities
public class CitiesForecast {

    private HashMap<String, String> citiesForecast;
    private static final String DELIMITER = ":";
    private static final String PATH = "./server/";
    // constructor, reads the weather forecast from a file
    public CitiesForecast() {
        citiesForecast = new HashMap<String, String>();
        readWeatherForecast();
    }

    // re-read the weather from the file, allows updating the data.
    public void readWeatherForecast() {
        try (Scanner input = new Scanner(new File(PATH + "weatherForecast.txt"))) {
            while (input.hasNext()) {
                String st = input.nextLine();
                int delIndex = st.indexOf(DELIMITER);
                String cityName = st.substring(0, delIndex);
                String forecast = st.substring(delIndex + 1, st.length() - 1);
                citiesForecast.put(cityName, forecast);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
    
    // return the weather forecast for the named city.
    // if there's no information for the named city, null will be returned.
    public String getForecastForCity(String cityName) {
        return citiesForecast.get(cityName);
    }

}
