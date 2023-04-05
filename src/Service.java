import java.io.IOException;
import java.util.List;
import com.neovisionaries.i18n.CountryCode;
import org.json.JSONObject;

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
        if (countryCodeList.isEmpty()) {
            throw new IllegalArgumentException("Nieprawidłowy kraj");
        }
        String countryCode = countryCodeList.get(0).toString();

        //Czytanie zawartości żądania HTTP GET
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s,%s&appid=%s",
                city, countryCode, API_KEY);
        String weather = null;
        try {
            weather = Utils.readAllHttp(url);
        } catch (Exception e) {
            throw new IllegalArgumentException("Nie znaleziono miasta");
        }

        //Serializowanie JSONa
        JSONObject json = new JSONObject(weather);
        String description = json.getJSONArray("weather").
                getJSONObject(0).getString("description");
        double temperature = json.getJSONObject("main").getDouble("temp");

        //Z Kelvina na Celsjusza
        double celsius = temperature - 273;
        celsius = Utils.round(celsius, 2);

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
        String allHttp = null;
        try {
            allHttp = Utils.readAllHttp(url);
        } catch (IOException e) {
            throw new IllegalArgumentException("Nie znaleziono miasta");
        }


        //Serializowanie JSONa
        JSONObject json = new JSONObject(allHttp);

        return json.getJSONObject("info").getDouble("rate");
    }

    /**
     * Metoda pobierająca kurs złotego wobec danej waluty z serwisu NBP
     */
    public double getNBPRate() {
        String baseCurrencyCode = Utils.getCurrencyCode(this.country);
        String table = "a";

        //Czytanie zawartości żądania HTTP GET
        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/%s/%s/?format=json", table, baseCurrencyCode);
        String allHttp = null;
        try {
            allHttp = Utils.readAllHttp(url);
        } catch (IOException e) {
            throw new IllegalArgumentException("Nie znaleziono miasta");
        }

        //Serialozwanie JSONa
        JSONObject json = new JSONObject(allHttp);

        return json.getJSONArray("rates").getJSONObject(0).getDouble("mid");
    }

}
