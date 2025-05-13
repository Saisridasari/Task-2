import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.JSONObject;

public class WeatherFetcher {

    // Method to send HTTP request and fetch weather details
    public static void getWeatherData(String cityName) {
        String apiKey = "f4a547194e608c06774c55deaabcfd0d"; // Replace with your actual OpenWeatherMap API key
        String apiEndpoint = "https://api.openweathermap.org/data/2.5/weather?q=London" 
                             +"&appid=" + apiKey + "&units=metric";

        try {
            // Establish connection
            URL url = new URL(apiEndpoint);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod("GET");

            // Read incoming data
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(jsonBuilder.toString());
            System.out.println(jsonResponse);
            // Extract specific details
            String city = jsonResponse.getString("name");
            JSONObject mainInfo = jsonResponse.getJSONObject("main");
            double temperature = mainInfo.getDouble("temp");
            int pressure = mainInfo.getInt("pressure");
            int humidity = mainInfo.getInt("humidity");

            JSONObject weatherDetails = jsonResponse.getJSONArray("weather").getJSONObject(0);
            String condition = weatherDetails.getString("description");

            // Display the weather data neatly
            System.out.println("\n=== Weather Report ===");
            System.out.println("City        : " + city);
            System.out.println("Condition   : " + condition);
            System.out.println("Temperature : " + temperature + " °C");
            System.out.println("Humidity    : " + humidity + "%");
            System.out.println("Pressure    : " + pressure + " hPa");
            System.out.println("======================");

        } catch (Exception ex) {
            System.out.println("Error occurred while fetching weather data.");
            System.out.println("Reason: " + ex.getMessage());
        }
    }

    // Main method
    public static void main(String[] args) {
        String city = "Chennai"; // You can change the city name here
        getWeatherData(city);
    }
}



