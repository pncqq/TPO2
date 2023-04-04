import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;


public class Service {
    public Service(String kraj) {
    }

    // Metoda pobierająca informacje o pogodzie z serwisu OpenWeatherMap
    private static String getWeather(String city) {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=API_KEY&units=metric";
        String weather = "";

        try {
            URLConnection connection = new URL(url).openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                weather += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject json = new JSONObject(weather);
        String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
        double temperature = json.getJSONObject("main").getDouble("temp");

        return "Pogoda w " + city + ": " + description + ", temperatura: " + temperature + "°C";
    }

    // Metoda pobierająca kurs wymiany waluty z serwisu NBP
    private static double getRateFor(String kod_waluty) {
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/" + kod_waluty + "/?format=json";
        String exchangeRate = "";

        try {
            URLConnection connection = new URL(url).openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                exchangeRate += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject json = new JSONObject(exchangeRate);
        double rate = json.getDouble("mid");

        return rate;
    }

    // Metoda pobierająca kurs złotego wobec danej waluty z serwisu NBP
    private static double getNBPRate() {
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/" + "PLN" + "/?format=json";
        String exchangeRate = "";

        try {
            URLConnection connection = new URL(url).openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                exchangeRate += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject json = new JSONObject(exchangeRate);
        double rate = json.getDouble("mid");

        String plnUrl = "http://api.nbp.pl/api/exchangerates/rates/b/" + "PLN" + "/?format=json";
        String plnRate = "";

        try {
            URLConnection connection = new URL(plnUrl).openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                plnRate += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject plnJson = new JSONObject(plnRate);
        double plnRateValue = plnJson.getDouble("mid");

        double plnRateForOneUnit = plnRateValue / rate;

        return plnRateForOneUnit;
    }

}
