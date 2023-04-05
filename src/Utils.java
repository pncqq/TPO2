import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.util.Currency;
import java.util.Locale;
import java.util.stream.Collectors;

public class Utils {

    public static String getCurrencyCode(String countryName) {
        String curr = getCurrencyFromCountry(countryName);

        Currency currency = Currency.getInstance(curr);
        return currency.getCurrencyCode();
    }

    private static String getCurrencyFromCountry(String countryName) {
        String countryCode = null;

        for (Locale locale : Locale.getAvailableLocales()) {
            if (countryName.equals(locale.getDisplayCountry(Locale.ENGLISH))) {
                countryCode = locale.getCountry();
                break;
            }
        }

        assert countryCode != null;
        return Currency.getInstance(new Locale("", countryCode)).getCurrencyCode();
    }

    public static String readAllHttp(String url) throws IOException {
        String allText = null;

        try {
            URLConnection connection = new URL(url).openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            allText = reader.lines().collect(Collectors.joining());

        } catch (IOException e) {
            throw new IOException();
        }
        return allText;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
