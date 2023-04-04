import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.neovisionaries.i18n.CountryCode;
import org.json.JSONObject;

import static jdk.nashorn.internal.objects.NativeMath.round;


public class Service {
    private static final String API_KEY = "e2cdb03aec582b3e57b0a56cdb06df3a";
    private String kraj;

    public Service(String kraj) {
        this.kraj = kraj;
    }

    // Metoda pobierająca informacje o pogodzie z serwisu OpenWeatherMap
    public String getWeather(String miasto) {
        //Otrzymanie kodu kraju
        List<CountryCode> countryCodeList = CountryCode.findByName(kraj);
        String kod_kraju = countryCodeList.get(0).toString();

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + miasto + "," +
                kod_kraju + "&appid=" + API_KEY;
        StringBuilder weather = new StringBuilder();

        //Łączenie z pogodowym API
        try {
            URLConnection connection = new URL(url).openConnection();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                weather.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Serializowanie JSONa
        JSONObject json = new JSONObject(weather.toString());
        String description = json.getJSONArray("weather").
                getJSONObject(0).getString("description");
        double temperature = json.getJSONObject("main").getDouble("temp");
        //Z kelvina na celcius
        double celsius = temperature / 273;
        celsius = round(celsius, 2);

        return "Pogoda w " + miasto + ", " + kraj +
                ": " + description + ", temperatura: " + celsius + "°C";
    }

    // Metoda pobierająca kurs wymiany waluty z serwisu NBP
    public double getRateFor(String kod_waluty) {
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
    public double getNBPRate() {
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
