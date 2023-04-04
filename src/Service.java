import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.stream.Collectors;

import com.neovisionaries.i18n.CountryCode;
import org.json.JSONObject;

import javax.rmi.CORBA.Util;

import static jdk.nashorn.internal.objects.NativeMath.round;


public class Service {
    private static final String API_KEY = "e2cdb03aec582b3e57b0a56cdb06df3a";
    private final String country;

    public Service(String country) {
        this.country = country;
    }

    /**
     * Metoda pobierająca informacje o pogodzie z serwisu OpenWeatherMap
     */
    public String getWeather(String city) {
        //Otrzymanie kodu kraju
        List<CountryCode> countryCodeList = CountryCode.findByName(country);
        String countryCode = countryCodeList.get(0).toString();

        //Czytanie zawartości żądania HTTP GET
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s,%s&appid=%s",
                city, countryCode, API_KEY);
        String weather = Utils.readAllHttp(url);

        //Serializowanie JSONa
        JSONObject json = new JSONObject(weather);
        String description = json.getJSONArray("weather").
                getJSONObject(0).getString("description");
        double temperature = json.getJSONObject("main").getDouble("temp");

        //Z Kelvina na Celsjusza
        double celsius = temperature / 273;
        celsius = round(celsius, 2);

        return String.format("Pogoda w %s, %s: %s, temperatura: %s°C", city, country, description, celsius);
    }

    /**
     * Metoda pobierająca kurs wymiany waluty z serwisu NBP
     */
    public double getRateFor(String currencyCode) {
        String baseCurrencyCode = Utils.getCurrencyCode(this.country);

        //Czytanie zawartości żądania HTTP GET
        String url = String.format("https://api.exchangerate.host/convert?from=%s&to=%s",
                currencyCode, baseCurrencyCode);
        String allHttp = Utils.readAllHttp(url);


        //Serializowanie JSONa
        JSONObject json = new JSONObject(allHttp);

        return json.getJSONObject("info").getDouble("rate");
    }

    /**
     * Metoda pobierająca kurs złotego wobec danej waluty z serwisu NBP
     */
    public double getNBPRate() {
        return 3.14;
    }

}
